package calculator.countsOfP.models.weapon.dao;

import calculator.countsOfP.models.weapon.StatsWeaponS;
import calculator.countsOfP.models.weapon.WeaponUpgradeS;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface StatsWeaponSDAO extends ListCrudRepository<StatsWeaponS, Long> {
    StatsWeaponS findByNameAndCurrentLevel(String name, WeaponUpgradeS currentLevel);
    List<StatsWeaponS> findByCurrentLevel(WeaponUpgradeS currentLevel);
    List<StatsWeaponS> findByname(String name);

    @Query("SELECT u FROM StatsWeaponS u WHERE u.name LIKE %?1%")
    List<StatsWeaponS> search(String keyword);
}
