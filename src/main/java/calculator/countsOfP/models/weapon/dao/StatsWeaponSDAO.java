package calculator.countsOfP.models.weapon.dao;

import calculator.countsOfP.models.weapon.StatsWeaponS;
import calculator.countsOfP.models.weapon.WeaponUpgradeS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsWeaponSDAO extends JpaRepository<StatsWeaponS, Long> {
    StatsWeaponS findByNameAndCurrentLevel(String name, WeaponUpgradeS currentLevel);
    List<StatsWeaponS> findByCurrentLevel(WeaponUpgradeS currentLevel);
    List<StatsWeaponS> findByname(String name);

    @Query(name = "StatWeaponS.findAllNamed")
    List<StatsWeaponS> findAllNamed();

    @Query("SELECT u FROM StatsWeaponS u WHERE u.name LIKE %?1%")
    List<StatsWeaponS> search(String keyword);
}
