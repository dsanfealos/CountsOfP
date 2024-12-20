package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.StatsWeaponNBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.response.StatsWeaponNResponse;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.models.dao.POrganDAO;
import calculator.countsOfP.models.weapon.Blade;
import calculator.countsOfP.models.weapon.Handle;
import calculator.countsOfP.models.weapon.StatsWeaponS;
import calculator.countsOfP.models.weapon.dao.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeaponService {

    private StatsWeaponSDAO statsWeaponSDAO;
    private WeaponUpgradeSDAO weaponUpgradeSDAO;
    private WeaponUpgradeNDAO weaponUpgradeNDAO;
    private HandleDAO handleDAO;
    private BladeDAO bladeDAO;
    private POrganDAO pOrganDAO;


    public WeaponService(StatsWeaponSDAO statsWeaponSDAO, WeaponUpgradeSDAO weaponUpgradeSDAO, WeaponUpgradeNDAO weaponUpgradeNDAO, HandleDAO handleDAO, BladeDAO bladeDAO, POrganDAO pOrganDAO) {
        this.statsWeaponSDAO = statsWeaponSDAO;
        this.weaponUpgradeSDAO = weaponUpgradeSDAO;
        this.weaponUpgradeNDAO = weaponUpgradeNDAO;
        this.handleDAO = handleDAO;
        this.bladeDAO = bladeDAO;
        this.pOrganDAO = pOrganDAO;
    }

    public StatsWeaponSResponse upgradeLevelS(StatsWeaponSBody body){
        Long initialLevel = body.getCurrentLevel();
        Long finalLevel = body.getFinalLevel();
        StatsWeaponS stats = statsWeaponSDAO.findByNameAndCurrentLevel(body.getWeaponName(),
                weaponUpgradeSDAO.findById(finalLevel).get());
        Long ergoCost = 0L;
        for (Long level = initialLevel + 1; level <= finalLevel; level++){
            ergoCost += weaponUpgradeSDAO.findById(level).get().getErgo();
        }
        return new StatsWeaponSResponse(ergoCost, stats);
    }

    public StatsWeaponNResponse upgradeLevelN(StatsWeaponNBody body){
        Long initialLevel = body.getCurrentLevel();
        Long finalLevel = body.getFinalLevel();
        Blade blade = bladeDAO.findByNameAndCurrentLevel(body.getBladeName(),
                weaponUpgradeNDAO.findById(finalLevel).get());
        Long ergoCost = 0L;
        for (Long level = initialLevel + 1; level <= finalLevel; level++){
            ergoCost += weaponUpgradeNDAO.findById(level).get().getErgo();
        }
        Handle handle = body.getHandle();
        Double weight = blade.getWeight() + handle.getWeight();
        String weaponName = body.getBladeName() + " | " + handle.getName();
        StatsWeaponNResponse response = new StatsWeaponNResponse(weaponName, ergoCost,weight, blade.getTotalAttack(), handle.getMotivity(),
                handle.getTechnique(), handle.getAdvance(), blade.getPhysicalAttack(), blade.getElementalAttack(), blade, handle);
        return response;
    }

    public Integer costUpgradeArm(Integer initialLevel, Integer finalLevel){
        Map<Integer, Integer> costs = Map.of(0,0,1,1,2,2,3,3);
        Integer components = 0;
        for (int index = initialLevel + 1; index <= finalLevel; index++){
            components += costs.get(index);
        }
        return components;
    }

    public Integer costQuartzTotal(Map<Integer, Integer> modules){
        Integer cost = 0;
        for (Integer level:modules.keySet()){
            Integer moduleQuantity = modules.get(level);
            cost += moduleQuantity * pOrganDAO.findById(Long.valueOf(level)).get().getQuartzs();
        }
        return cost;
    }
}
