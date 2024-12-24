package calculator.countsOfP.models.weapon.dao;

import calculator.countsOfP.models.weapon.Blade;
import calculator.countsOfP.models.weapon.WeaponUpgradeN;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface BladeDAO extends ListCrudRepository<Blade, Long> {
    Blade findByNameAndCurrentLevel (String name, WeaponUpgradeN currentLevel);
    List<Blade> findByCurrentLevel (WeaponUpgradeN currentLevel);

    @Query("SELECT u FROM Blade u WHERE u.name LIKE %?1%")
    List<Blade> search(String keyword);
}
