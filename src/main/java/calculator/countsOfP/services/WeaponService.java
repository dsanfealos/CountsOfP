package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.models.weapon.StatsWeaponS;
import calculator.countsOfP.models.weapon.WeaponUpgradeS;
import calculator.countsOfP.models.weapon.dao.BladeDAO;
import calculator.countsOfP.models.weapon.dao.HandleDAO;
import calculator.countsOfP.models.weapon.dao.StatsWeaponSDAO;
import calculator.countsOfP.models.weapon.dao.WeaponUpgradeSDAO;
import org.springframework.stereotype.Service;

@Service
public class WeaponService {

    private StatsWeaponSDAO statsWeaponSDAO;
    private WeaponUpgradeSDAO weaponUpgradeSDAO;
    private HandleDAO handleDAO;
    private BladeDAO bladeDAO;


    public WeaponService(StatsWeaponSDAO statsWeaponSDAO, WeaponUpgradeSDAO weaponUpgradeSDAO, HandleDAO handleDAO, BladeDAO bladeDAO) {
        this.statsWeaponSDAO = statsWeaponSDAO;
        this.weaponUpgradeSDAO = weaponUpgradeSDAO;
        this.handleDAO = handleDAO;
        this.bladeDAO = bladeDAO;
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
}
