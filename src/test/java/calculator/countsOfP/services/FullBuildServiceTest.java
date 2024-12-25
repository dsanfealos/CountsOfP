package calculator.countsOfP.services;

import calculator.countsOfP.exceptions.NotEnoughModulesException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class FullBuildServiceTest {

    @Autowired
    private FullBuildService buildService;


    @Test
    @Transactional
    public void testCostUpgradeArm(){
        Assertions.assertEquals(buildService.costUpgradeArm(0,3), 6);
        Assertions.assertEquals(buildService.costUpgradeArm(1,2), 2);
        Assertions.assertEquals(buildService.costUpgradeArm(1,3), 5);
    }

    @Test
    @Transactional
    public void testCostQuartzTotal(){
        Map<Integer, Integer> modules = new LinkedHashMap<>();
        modules.put(1,2);
        modules.put(2,2);
        modules.put(3,2);
        modules.put(4,1);
        Assertions.assertEquals(buildService.costQuartzTotal(modules).getQuartz(), 17);

        modules.put(5,3);
        Assertions.assertThrows(NotEnoughModulesException.class, () ->
                buildService.costQuartzTotal(modules), "Modules should be not enough to unlock all wanted P Organ levels.");

        modules.replace(4,3);
        Assertions.assertEquals(buildService.costQuartzTotal(modules).getQuartz(), 35);
    }
}
