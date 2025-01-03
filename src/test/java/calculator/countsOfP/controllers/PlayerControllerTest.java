package calculator.countsOfP.controllers;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.models.player.Attribute;
import calculator.countsOfP.services.PlayerService;
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
public class PlayerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PlayerService playerService;

    @Test
    @Transactional
    public void testGetSimulatedStats() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, AttributesBody> body = new LinkedHashMap<>();
        AttributesBody initialBody = new AttributesBody(10L, 12, 15, 15, 15, 15, 10, null);
        AttributesBody finalBody = new AttributesBody(38L, 15, 20, 20, 20, 20, 15, null);
        body.put("initial", initialBody);
        body.put("final", finalBody);
        mvc.perform(post("/player/simulate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ergoCost",
                        CoreMatchers.is(39449)));
    }

}
