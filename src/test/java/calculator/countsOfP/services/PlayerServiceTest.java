package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.api.models.response.StatsResponse;
import calculator.countsOfP.models.build.Amulet;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

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
    public void testIncreaseStatsByAttribute(){
        Map<Long, Double> listOfStats = playerService.increaseStatsByAttribute(1L, 20);
        List<Long> statIds = new ArrayList<>();
        List<Double> increases = new ArrayList<>();
        for(Long key: listOfStats.keySet()){
            statIds.add(key);
            increases.add(listOfStats.get(key));
        }
        Assertions.assertEquals(statIds, List.of(1L, 4L, 5L, 8L, 11L, 14L));
        Assertions.assertEquals(increases, List.of(210.0, 54.0, 19.0, 11.0, 11.0, 11.0));
    }

    @Test
    @Transactional
    public void testSimulateStats(){
        List<Long> amuletIds = List.of(1L, 4L, 15L, 21L, 24L);
        AttributesBody initialBody = new AttributesBody(10L, 12, 15, 15, 15, 15, 10, null);
        AttributesBody finalBody = new AttributesBody(38L, 15, 20, 20, 20, 20, 15, amuletIds);


        StatsResponse response = playerService.simulateStats(initialBody, finalBody);
        Map<String, Double> stats = new LinkedHashMap<>();
        stats.put("Health", 423.72);
        stats.put("Stamina", 185.15);
        stats.put("Legion", 258.0);
        stats.put("Guard Regain", 84.0);
        stats.put("Physical Def", 166.0);
        stats.put("Physical Red", 0.0);
        stats.put("Physical Res", 0.00);
        stats.put("Fire Def", 129.0);
        stats.put("Fire Red", 0.0);
        stats.put("Fire Res", 117.0);
        stats.put("Electric Def", 129.0);
        stats.put("Electric Red", 0.0);
        stats.put("Electric Res", 117.0);
        stats.put("Acid Def", 129.0);
        stats.put("Acid Red", 0.0);
        stats.put("Acid Res", 117.0);
        stats.put("Disruption", 251.0);
        stats.put("Shock", 117.0);
        stats.put("Break", 117.0);
        stats.put("Slash Red", 0.00);
        stats.put("Strike Red", 0.00);
        stats.put("Pierce Red", 0.00);
        stats.put("Weight", 110.6);

        StatsResponse reference = new StatsResponse(38L, 15, 20, 20, 27, 23, 19, stats, 39449L);
        Assertions.assertEquals(response.getLevel(), reference.getLevel());
        Assertions.assertEquals(response.getErgoCost(), reference.getErgoCost());
        Assertions.assertEquals(response.getMotivity(), reference.getMotivity());
        Assertions.assertEquals(response.getStats(), reference.getStats());
    }
}
