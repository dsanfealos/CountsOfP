package calculator.countsOfP.controllers;

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

}
