package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.api.models.response.StatsResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    @Transactional
    public void testIncreaseAttribute(){
        Map<Long, Double> listOfStats = playerService.increaseAttribute(1L, 10, 20);
        List<Long> statIds = new ArrayList<>();
        List<Double> increases = new ArrayList<>();
        for(Long key: listOfStats.keySet()){
            statIds.add(key);
            increases.add(listOfStats.get(key));
        }
        Assertions.assertEquals(statIds, List.of(1L, 4L, 5L, 7L));
        Assertions.assertEquals(increases, List.of(168.0, 44.0, 16.0, 9.0));
    }

    @Test
    @Transactional
    public void testSimulateStats(){
        AttributesBody initialBody = new AttributesBody(10L, 12, 15, 15, 15, 15, 10);
        AttributesBody finalBody = new AttributesBody(38L, 15, 20, 20, 20, 20, 15);

        StatsResponse response = playerService.simulateStats(initialBody, finalBody);
        Assertions.assertEquals(response, 0);
    }
}
