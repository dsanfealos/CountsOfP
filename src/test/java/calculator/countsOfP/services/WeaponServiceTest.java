package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.StatsWeaponNBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.response.StatsWeaponNResponse;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.models.weapon.Blade;
import calculator.countsOfP.models.weapon.Handle;
import calculator.countsOfP.models.weapon.dao.BladeDAO;
import calculator.countsOfP.models.weapon.dao.HandleDAO;
import calculator.countsOfP.models.weapon.dao.StatsWeaponSDAO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class WeaponServiceTest {

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private StatsWeaponSDAO statsWeaponSDAO;

    @Autowired
    private HandleDAO handleDAO;

    @Autowired
    private BladeDAO bladeDAO;

    @Test
    @Transactional
    public void testUpgradeLevelS(){
        StatsWeaponSResponse response = weaponService.upgradeLevelS(new StatsWeaponSBody("Etiquette", 1L, 3L));
        StatsWeaponSResponse reference = new StatsWeaponSResponse(430L, statsWeaponSDAO.findById(9L).get());
        Assertions.assertEquals(response.getErgoCost(), reference.getErgoCost());
        Assertions.assertEquals(response.getStats().getId(), reference.getStats().getId());
        Assertions.assertEquals(response.getStats().getPhysicalAttack(), reference.getStats().getPhysicalAttack());
    }

    @Test
    @Transactional
    public void testUpgradeLevelN(){
        Handle handle = handleDAO.findById(4L).get();
        Blade blade = bladeDAO.findById(6L).get();
        StatsWeaponNResponse response = weaponService.upgradeLevelN(new StatsWeaponNBody("Puppet's saber blade", 2L, 6L, handle));
        String weaponName = "Puppet's saber blade" + " | " + "Fire axe handle";
        StatsWeaponNResponse reference = new StatsWeaponNResponse(weaponName, 1240L, 9.9, 168, 'C',
                'C', '-', 168, 0, blade, handle);
        Assertions.assertEquals(response.getName(), reference.getName());
        Assertions.assertEquals(response.getErgoCost(), reference.getErgoCost());
        Assertions.assertEquals(response.getTotalAttack(), reference.getTotalAttack());
        Assertions.assertEquals(response.getHandle().getId(), reference.getHandle().getId());
        Assertions.assertEquals(response.getBlade().getId(), reference.getBlade().getId());
        Assertions.assertEquals(response.getWeight(), reference.getWeight());
        Assertions.assertEquals(response.getPhysicalAttack(), reference.getPhysicalAttack());
        Assertions.assertEquals(response.getElementalAttack(), reference.getElementalAttack());
    }

    @Test
    @Transactional
    public void testCostUpgradeArm(){
        Assertions.assertEquals(weaponService.costUpgradeArm(0,3), 6);
        Assertions.assertEquals(weaponService.costUpgradeArm(1,2), 2);
        Assertions.assertEquals(weaponService.costUpgradeArm(1,3), 5);
    }

    @Test
    @Transactional
    public void testCostQuartzTotal(){
        Map<Integer, Integer> modules = Map.of(1, 3, 2, 2, 3,2,4, 1, 5, 3, 6, 1);
        Assertions.assertEquals(weaponService.costQuartzTotal(modules), 35);

        modules = Map.of(1, 3, 2, 3, 4, 2);
        Assertions.assertEquals(weaponService.costQuartzTotal(modules), 18);
    }
}
