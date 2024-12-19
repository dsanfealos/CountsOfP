package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.models.weapon.dao.StatsWeaponSDAO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class WeaponServiceTest {

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private StatsWeaponSDAO statsWeaponSDAO;

    @Test
    @Transactional
    public void testUpgradeLevelS(){
        StatsWeaponSResponse response = weaponService.upgradeLevelS(new StatsWeaponSBody("Etiquette", 1L, 3L));
        StatsWeaponSResponse reference = new StatsWeaponSResponse(430L, statsWeaponSDAO.findById(9L).get());
        Assertions.assertEquals(response.getErgoCost(), reference.getErgoCost());
        Assertions.assertEquals(response.getStats().getId(), reference.getStats().getId());
        Assertions.assertEquals(response.getStats().getPhysicalAttack(), reference.getStats().getPhysicalAttack());
    }
}
