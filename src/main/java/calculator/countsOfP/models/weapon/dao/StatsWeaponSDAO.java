package calculator.countsOfP.models.weapon.dao;

import calculator.countsOfP.models.weapon.StatsWeaponS;
import calculator.countsOfP.models.weapon.WeaponUpgradeS;
import org.springframework.data.repository.ListCrudRepository;

public interface StatsWeaponSDAO extends ListCrudRepository<StatsWeaponS, Long> {
    StatsWeaponS findByNameAndCurrentLevel(String name, WeaponUpgradeS currentLevel);
}
