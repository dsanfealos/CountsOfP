package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.FullBuildBody;
import calculator.countsOfP.api.models.body.TotalAttackBody;
import calculator.countsOfP.api.models.response.*;
import calculator.countsOfP.exceptions.NotEnoughModulesException;
import calculator.countsOfP.models.build.dao.POrganDAO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FullBuildService {

    private POrganDAO pOrganDAO;

    private PlayerService playerService;

    private WeaponService weaponService;

    public FullBuildService(POrganDAO pOrganDAO, PlayerService playerService, WeaponService weaponService) {
        this.pOrganDAO = pOrganDAO;
        this.playerService = playerService;
        this.weaponService = weaponService;
    }

    public Integer costUpgradeArm(Integer initialLevel, Integer finalLevel){
        Map<Integer, Integer> costs = Map.of(0,0,1,1,2,2,3,3);
        Integer components = 0;
        for (int index = initialLevel + 1; index <= finalLevel; index++){
            components += costs.get(index);
        }
        return components;
    }

    public POrganResponse costQuartzTotal(Map<Integer, Integer> modules){
        Integer cost = 0;
        Integer totalModules = 0;
        for (Integer level:modules.keySet()){
            Integer moduleQuantity = modules.get(level);
            Integer minimumTotalModules = 2*(level-1);
            int nextLevel = level + 1;
            if (totalModules<minimumTotalModules){
                throw new NotEnoughModulesException();
            }
            totalModules += moduleQuantity;
            cost += moduleQuantity * pOrganDAO.findById(Long.valueOf(level)).get().getQuartzs();
        }
        return new POrganResponse(cost);
    }

    public FullBuildResponse build(FullBuildBody body){
        StatsResponse stats = playerService.simulateStats(body.getInitialAttributesBody(), body.getFinalAttributesBody());
        StatsWeaponNResponse weaponN = null;
        StatsWeaponSResponse weaponS = null;
        TotalAttackBody attackBody;
        TotalAttackResponse attack;
        if (body.getIsWeaponS()){
            weaponS = weaponService.upgradeLevelS(body.getStatsWeaponSBody());
            attackBody = new TotalAttackBody(true, weaponS.getStats().getId(),
                    null, null, stats.getMotivity(), stats.getTechnique(), stats.getAdvance());
        } else{
            weaponN = weaponService.upgradeLevelN(body.getStatsWeaponNBody());
            attackBody = new TotalAttackBody(false, null,
                    weaponN.getBlade().getId(), weaponN.getHandle().getId(), stats.getMotivity(), stats.getTechnique(), stats.getAdvance());

        }
        attack = weaponService.calculateAttack(attackBody);
        return new FullBuildResponse(stats, attack, body.getIsWeaponS(), weaponN, weaponS);
    }
}
