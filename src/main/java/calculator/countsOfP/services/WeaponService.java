package calculator.countsOfP.services;

import calculator.countsOfP.models.weapon.StatsWeaponS;
import calculator.countsOfP.models.weapon.dao.StatsWeaponSDAO;
import calculator.countsOfP.models.weapon.dao.WeaponUpgradeSDAO;
import org.springframework.stereotype.Service;

@Service
public class WeaponService {

    private StatsWeaponSDAO statsWeaponSDAO;
    private WeaponUpgradeSDAO weaponUpgradeSDAO;


    public WeaponService(StatsWeaponSDAO statsWeaponSDAO, WeaponUpgradeSDAO weaponUpgradeSDAO) {
        this.statsWeaponSDAO = statsWeaponSDAO;
        this.weaponUpgradeSDAO = weaponUpgradeSDAO;
    }

    public StatsWeaponS upgradeLevel(){

        return null;
    }
}
