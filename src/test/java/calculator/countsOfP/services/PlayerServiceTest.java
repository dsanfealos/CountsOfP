package calculator.countsOfP.services;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    @Transactional
    public void testCostUpgradeLevelP(){
        Assertions.assertEquals(5162704, playerService.costUpgradeLevelP(10L, 300L),
                "Result should be 5162704 ergo to reach lvl 300 from lvl 10.");
    }

//    @Test
//    @Transactional
//    public void testIncreaseAttribute(){
//        Map<Long, Double> listOfStats = playerService.increaseAttribute(1L, 10, 20);
//        Assertions.assertTrue(listOfStats.containsValue(1));
//    }
}
