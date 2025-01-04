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
        Map<Long, Double> listOfStats = playerService.increaseStatsByAttribute(1L, 10, 20);
        List<Long> statIds = new ArrayList<>();
        List<Double> increases = new ArrayList<>();
        for(Long key: listOfStats.keySet()){
            statIds.add(key);
            increases.add(listOfStats.get(key));
        }
        Assertions.assertEquals(statIds, List.of(1L, 4L, 5L, 8L, 11L, 14L));
        Assertions.assertEquals(increases, List.of(168.0, 44.0, 16.0, 9.0, 9.0, 9.0));
    }

    @Test
    @Transactional
    public void testSimulateStats(){
        List<Long> amuletIds = List.of(2L, 4L, 15L, 21L, 24L);
        AttributesBody initialBody = new AttributesBody(10L, 12, 15, 15, 15, 15, 10, null);
        AttributesBody finalBody = new AttributesBody(38L, 15, 20, 20, 20, 20, 15, amuletIds);


        StatsResponse response = playerService.simulateStats(initialBody, finalBody);
        Map<String, Double> stats = Map.ofEntries(
                entry("Health", 447.48), entry("Stamina", 181.7),
                entry("Legion", 252.0), entry("Guard Regain", 84.0),
                entry("Physical Def", 110.0), entry("Fire Def", 95.0),
                entry("Fire Res", 117.0), entry("Electric Def", 95.0),
                entry("Electric Res", 117.0), entry("Acid Def", 95.0),
                entry("Acid Res", 117.0), entry("Disruption", 251.0),
                entry("Shock", 75.0), entry("Break", 75.0),
                entry("Weight", 102.7));
        StatsResponse reference = new StatsResponse(38L, 15, 20, 20, 27, 23, 19, stats, 39449L);
        Assertions.assertEquals(response.getLevel(), reference.getLevel());
        Assertions.assertEquals(response.getErgoCost(), reference.getErgoCost());
        Assertions.assertEquals(response.getMotivity(), reference.getMotivity());
        Assertions.assertEquals(response.getStats(), reference.getStats());
    }
}
