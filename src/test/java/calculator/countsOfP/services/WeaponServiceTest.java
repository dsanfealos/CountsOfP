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

import java.util.LinkedHashMap;
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
        Map<String, Integer> materials = new LinkedHashMap<>();
        materials.put("Dark moon moonstone of the covenant", 3);
        StatsWeaponSResponse reference = new StatsWeaponSResponse(430L, materials, statsWeaponSDAO.findById(9L).get());
        Assertions.assertEquals(response.getErgoCost(), reference.getErgoCost());
        Assertions.assertEquals(response.getStats().getId(), reference.getStats().getId());
        Assertions.assertEquals(response.getStats().getPhysicalAttack(), reference.getStats().getPhysicalAttack());
        Assertions.assertEquals(response.getMaterials(), reference.getMaterials());
    }

    @Test
    @Transactional
    public void testUpgradeLevelN(){
        Handle handle = handleDAO.findById(4L).get();
        Blade blade = bladeDAO.findById(6L).get();
        StatsWeaponNResponse response = weaponService.upgradeLevelN(new StatsWeaponNBody("Puppet's saber blade", 2L, 6L, 4L));
        String weaponName = "Puppet's saber blade" + " | " + "Fire axe handle";
        Map<String, Integer> materials = new LinkedHashMap<>();
        materials.put("Hidden moonstone", 6);
        materials.put("Crescent moonstone", 3);
        StatsWeaponNResponse reference = new StatsWeaponNResponse(weaponName, 1240L, materials, 9.9, 168, 'C',
                'C', '-', 168, 0, blade, handle);
        Assertions.assertEquals(response.getName(), reference.getName());
        Assertions.assertEquals(response.getErgoCost(), reference.getErgoCost());
        Assertions.assertEquals(response.getTotalAttack(), reference.getTotalAttack());
        Assertions.assertEquals(response.getHandle().getId(), reference.getHandle().getId());
        Assertions.assertEquals(response.getBlade().getId(), reference.getBlade().getId());
        Assertions.assertEquals(response.getWeight(), reference.getWeight());
        Assertions.assertEquals(response.getMaterials(), reference.getMaterials());
        Assertions.assertEquals(response.getPhysicalAttack(), reference.getPhysicalAttack());
        Assertions.assertEquals(response.getElementalAttack(), reference.getElementalAttack());
    }
}
