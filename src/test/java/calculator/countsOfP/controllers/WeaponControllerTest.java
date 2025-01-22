package calculator.countsOfP.controllers;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.api.models.body.StatsWeaponNBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.models.weapon.StatsWeaponS;
import calculator.countsOfP.models.weapon.dao.StatsWeaponSDAO;
import calculator.countsOfP.services.WeaponService;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WeaponControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private StatsWeaponSDAO statsWeaponSDAO;

    @Test
    @Transactional
    public void testUpgradeWeaponS() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        StatsWeaponSBody body = new StatsWeaponSBody("Holy sword of the ark", 2L, 5L, null);
        Map<String, Integer> materials = new LinkedHashMap<>();
        materials.put("Dark moon moonstone of the covenant", 6);
        materials.put("Full moonstone of the covenant", 1);
        mvc.perform(post("/weapon/S/upgrade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ergoCost",
                        CoreMatchers.is(810)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.materials",
                        CoreMatchers.is(materials)));
    }

    @Test
    @Transactional
    public void testUpgradeWeaponN() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        StatsWeaponNBody body = new StatsWeaponNBody("Bramble curved sword blade",
                2L, 7L, 3L, null);
        Map<String, Integer> materials = new LinkedHashMap<>();
        materials.put("Hidden moonstone", 6);
        materials.put("Crescent moonstone", 7);
        mvc.perform(post("/weapon/N/upgrade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ergoCost",
                        CoreMatchers.is(1840)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.materials",
                        CoreMatchers.is(materials)));
    }

}
