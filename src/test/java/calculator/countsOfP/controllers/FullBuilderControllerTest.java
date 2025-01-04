package calculator.countsOfP.controllers;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.api.models.body.FullBuildBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.services.FullBuildService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FullBuilderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FullBuildService buildService;

    @Test
    @Transactional
    public void testCostUpgradeArm() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> body = new LinkedHashMap<>();
        body.put("initialLevel", 1);
        body.put("finalLevel", 3);
        mvc.perform(post("/build/arm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        CoreMatchers.is(5)));
    }

    @Test
    @Transactional
    public void testCostQuartz() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Integer> body = new LinkedHashMap<>();
        body.put(1,2);
        body.put(2,2);
        body.put(3,2);
        body.put(4,1);
        mvc.perform(post("/build/p_organ")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quartz",
                        CoreMatchers.is(17)));

        body.put(5,3);
        mvc.perform(post("/build/p_organ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        CoreMatchers.is("It is needed at least 2 modules per each P Organ level. You need at least 8 modules to unlock level 5.")));

        body.replace(4,3);
        mvc.perform(post("/build/p_organ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quartz",
                        CoreMatchers.is(35)));
    }

    @Test
    @Transactional
    public void testBuild() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Long> amuletIds = new java.util.ArrayList<>(List.of(2L, 4L, 15L, 21L, 24L, 25L));
        List<Long> armorPiecesIds = new java.util.ArrayList<>(List.of(6L, 30L, 60L, 90L));
        AttributesBody initialAttBody = new AttributesBody(10L, 12, 15, 15, 15, 15, 10, null);
        AttributesBody finalAttBody = new AttributesBody(38L, 15, 20, 20, 20, 20, 15, amuletIds);
        StatsWeaponSBody weaponSBody = new StatsWeaponSBody("Etiquette", 1L, 3L);
        FullBuildBody body = new FullBuildBody(initialAttBody, finalAttBody, true, null, weaponSBody, armorPiecesIds);

        mvc.perform(post("/build/build")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        CoreMatchers.is("Maximum number of amulets is 5.")));

        amuletIds.remove(5);
        armorPiecesIds.add(92L);
        mvc.perform(post("/build/build")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        CoreMatchers.is("Maximum number of armor pieces is 4.")));

        armorPiecesIds.remove(4);
        armorPiecesIds.remove(3);
        armorPiecesIds.add(62L);
        mvc.perform(post("/build/build")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        CoreMatchers.is("There are two or more armor pieces of the same type. They have to be four different types.")));

        armorPiecesIds.remove(3);
        armorPiecesIds.add(90L);
        mvc.perform(post("/build/build")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

}
