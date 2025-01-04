package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.api.models.body.FullBuildBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.response.FullBuildResponse;
import calculator.countsOfP.api.models.response.StatsResponse;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.api.models.response.TotalAttackResponse;
import calculator.countsOfP.exceptions.NotEnoughModulesException;
import calculator.countsOfP.models.build.Armor;
import calculator.countsOfP.models.weapon.dao.StatsWeaponSDAO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class FullBuildServiceTest {

    @Autowired
    private FullBuildService buildService;

    @Autowired
    private StatsWeaponSDAO statsWeaponSDAO;


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

    @Test
    @Transactional
    public void testBuild(){
        List<Long> amuletIds = List.of(2L, 4L, 15L, 21L, 24L);
        List<Long> armorPiecesIds = List.of(6L, 30L, 60L, 90L);
        AttributesBody initialAttBody = new AttributesBody(10L, 12, 15, 15, 15, 15, 10, null);
        AttributesBody finalAttBody = new AttributesBody(38L, 15, 20, 20, 20, 20, 15, amuletIds);
        StatsWeaponSBody weaponSBody = new StatsWeaponSBody("Etiquette", 1L, 3L);
        FullBuildBody body = new FullBuildBody(initialAttBody, finalAttBody, true, null, weaponSBody, armorPiecesIds);

        Map<String, Double> stats = Map.ofEntries(
                entry("Health", 447.48), entry("Stamina", 181.7),
                entry("Legion", 252.0), entry("Guard Regain", 84.0),
                entry("Physical Def", 110.0), entry("Fire Def", 95.0),
                entry("Fire Res", 169.0), entry("Electric Def", 95.0),
                entry("Electric Res", 161.0), entry("Acid Def", 95.0),
                entry("Acid Res", 222.0), entry("Disruption", 460.0),
                entry("Shock", 294.0), entry("Break", 494.0),
                entry("Weight", 102.7));
        Map<String, Integer> materials = new LinkedHashMap<>();
        materials.put("Dark moon moonstone of the covenant", 3);

        List<Armor> armorPieces = List.of(buildService.getArmor(6L),buildService.getArmor(30L),buildService.getArmor(60L),buildService.getArmor(90L));
        StatsResponse statsResponse =new StatsResponse(38L, 15, 20, 20, 27, 23, 19, stats, 39449L);
        StatsWeaponSResponse weaponSResponse = new StatsWeaponSResponse(430L, materials, statsWeaponSDAO.findById(9L).get());
        TotalAttackResponse attackResponse = new TotalAttackResponse("Etiquette", 149, 0, 138, 0, 287);
        FullBuildResponse reference = new FullBuildResponse(statsResponse, attackResponse, true, null, weaponSResponse, armorPieces, 70.00);

        FullBuildResponse response = buildService.build(body);
        Assertions.assertEquals(reference.getCurrentWeight(), response.getCurrentWeight());
        assertThat(reference.getAttackResponse()).usingRecursiveComparison()
                .isEqualTo(response.getAttackResponse());
        assertThat(reference.getArmorPieces()).usingRecursiveComparison()
                .isEqualTo(response.getArmorPieces());
        assertThat(reference.getStatsWeaponN()).usingRecursiveComparison()
                .isEqualTo(response.getStatsWeaponN());
        assertThat(reference.getStatsWeaponS()).usingRecursiveComparison()
                .isEqualTo(response.getStatsWeaponS());
        assertThat(reference.getStats()).usingRecursiveComparison()
                .isEqualTo(response.getStats());
    }
}
