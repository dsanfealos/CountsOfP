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
        List<Long> amuletIds = List.of(1L, 15L, 24L);
        List<Long> armorPiecesIds = List.of(1L, 30L, 51L, 86L);
        AttributesBody initialAttBody = new AttributesBody(10L, 12, 15, 15, 15, 15, 10, null);
        AttributesBody finalAttBody = new AttributesBody(38L, 15, 20, 20, 20, 20, 15, amuletIds);
        StatsWeaponSBody weaponSBody = new StatsWeaponSBody("Seven-coil spring sword", 1L, 6L, "motivity");
        FullBuildBody body = new FullBuildBody(initialAttBody, finalAttBody, true, null, weaponSBody, armorPiecesIds);

        Map<String, Double> stats = new LinkedHashMap<>();
        stats.put("Health", 423.72);
        stats.put("Stamina", 161.0);
        stats.put("Legion", 252.0);
        stats.put("Guard Regain", 84.0);
        stats.put("Physical Def", 166.0);
        stats.put("Physical Red", 9.41);
        stats.put("Physical Res", 0.00);
        stats.put("Fire Def", 127.0);
        stats.put("Fire Red", 3.80);
        stats.put("Fire Res", 156.0);
        stats.put("Electric Def", 127.0);
        stats.put("Electric Red", 3.61);
        stats.put("Electric Res", 148.0);
        stats.put("Acid Def", 127.0);
        stats.put("Acid Red", 8.86);
        stats.put("Acid Res", 209.0);
        stats.put("Disruption", 276.0);
        stats.put("Shock", 141.0);
        stats.put("Break", 142.0);
        stats.put("Slash Red", 3.96);
        stats.put("Strike Red", 6.60);
        stats.put("Pierce Red", 4.95);
        stats.put("Weight", 110.6);

        Map<String, Integer> materials = new LinkedHashMap<>();
        materials.put("Dark moon moonstone of the covenant", 3);

        List<Armor> armorPieces = List.of(buildService.getArmor(1L),buildService.getArmor(30L),buildService.getArmor(51L),buildService.getArmor(86L));
        StatsResponse statsResponse =new StatsResponse(38L, 15, 20, 20, 27, 23, 15, stats, 39449L);
        StatsWeaponSResponse weaponSResponse = new StatsWeaponSResponse(430L, materials, statsWeaponSDAO.findById(6L).get());
        TotalAttackResponse attackResponse = new TotalAttackResponse("Seven-coil spring sword", 273, 0, 132, 0, 387);
        FullBuildResponse reference = new FullBuildResponse(statsResponse, attackResponse, true, null, weaponSResponse, armorPieces, 42.3);

        FullBuildResponse response = buildService.build(body);
        Assertions.assertEquals(reference.getCurrentWeight(), response.getCurrentWeight());
        Assertions.assertEquals(reference.getAttackResponse().getTotalAttack(), response.getAttackResponse().getTotalAttack());
        Assertions.assertEquals(reference.getArmorPieces().size(), response.getArmorPieces().size());
        for (int i = 0; i< reference.getArmorPieces().size(); i++){
            Assertions.assertEquals(reference.getArmorPieces().get(i), response.getArmorPieces().get(i));
        }
        if (reference.getIsWeaponS()){
            Assertions.assertEquals(reference.getStatsWeaponS().getStats().getTotalAttack(), response.getStatsWeaponS().getStats().getTotalAttack());
        }else {
            Assertions.assertEquals(reference.getStatsWeaponN().getTotalAttack(), response.getStatsWeaponN().getTotalAttack());
        }
        for (String statName: reference.getStats().getStats().keySet()){
            Assertions.assertEquals(reference.getStats().getStats().get(statName), response.getStats().getStats().get(statName));
        }
    }
}
