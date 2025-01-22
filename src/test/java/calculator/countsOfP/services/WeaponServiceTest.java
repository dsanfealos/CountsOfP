package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.StatsWeaponNBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.body.TotalAttackBody;
import calculator.countsOfP.api.models.response.StatsWeaponNResponse;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.api.models.response.TotalAttackResponse;
import calculator.countsOfP.models.weapon.Blade;
import calculator.countsOfP.models.weapon.Handle;
import calculator.countsOfP.models.weapon.StatsWeaponS;
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
        StatsWeaponSResponse response = weaponService.upgradeLevelS(new StatsWeaponSBody("Etiquette", 1L, 3L, null));
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
        StatsWeaponNResponse response = weaponService.upgradeLevelN(new StatsWeaponNBody("Puppet's saber blade", 2L, 6L, 4L, null));
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

    @Test
    @Transactional
    public void testTotalAttack(){
        StatsWeaponS weaponS = statsWeaponSDAO.findById(1L).get();
        TotalAttackBody body = new TotalAttackBody(true,weaponS,null,null, 15,15,6);
        TotalAttackResponse reference = new TotalAttackResponse("Seven-coil spring sword", 130, 0, 59, 0, 189);
        TotalAttackResponse response = weaponService.calculateAttack(body);
        Assertions.assertEquals(response.getWeaponName(), reference.getWeaponName());
        Assertions.assertEquals(response.getBonusElementalAttack(), reference.getBonusElementalAttack());
        Assertions.assertEquals(response.getBonusPhysicalAttack(), reference.getBonusPhysicalAttack());
        Assertions.assertEquals(response.getTotalAttack(), reference.getTotalAttack());
    }

    @Test
    @Transactional
    public void testModifyHandle_Normal(){
        Handle handle1 = handleDAO.findById(1L).get();
        Handle handle2 = handleDAO.findById(3L).get();
        Handle handle3 = handleDAO.findById(5L).get();
        weaponService.modifyHandle(handle1, "motivity");
        weaponService.modifyHandle(handle2, "technique");
        weaponService.modifyHandle(handle3, "advance");
        Assertions.assertEquals(handle1.getMotivity(), 'B');
        Assertions.assertEquals(handle1.getTechnique(), 'D');
        Assertions.assertEquals(handle2.getMotivity(), 'C');
        Assertions.assertEquals(handle2.getTechnique(), 'C');
        Assertions.assertEquals(handle3.getAdvance(), 'S');
    }

    @Test
    @Transactional
    public void testModifyHandle_Special(){
        StatsWeaponS weapon1 = statsWeaponSDAO.findById(1L).get();
        StatsWeaponS weapon2 = statsWeaponSDAO.findById(7L).get();
        StatsWeaponS weapon3 = statsWeaponSDAO.findById(45L).get();
        weaponService.modifyHandle(weapon1, "motivity");
        weaponService.modifyHandle(weapon2, "technique");
        weaponService.modifyHandle(weapon3, "advance");
        Assertions.assertEquals(weapon1.getMotivity(), 'A');
        Assertions.assertEquals(weapon1.getTechnique(), 'D');
        Assertions.assertEquals(weapon2.getMotivity(), '-');
        Assertions.assertEquals(weapon2.getTechnique(), 'S');
        Assertions.assertEquals(weapon3.getAdvance(), 'D');
    }
}
