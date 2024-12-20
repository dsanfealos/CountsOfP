package calculator.countsOfP.models.weapon.dao;

import calculator.countsOfP.models.weapon.Blade;
import calculator.countsOfP.models.weapon.WeaponUpgradeN;
import org.springframework.data.repository.ListCrudRepository;

public interface BladeDAO extends ListCrudRepository<Blade, Long> {
    Blade findByNameAndCurrentLevel (String name, WeaponUpgradeN currentLevel);
}
