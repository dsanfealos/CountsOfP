package calculator.countsOfP.models.weapon.dao;

import calculator.countsOfP.models.weapon.WeaponUpgradeN;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponUpgradeNDAO extends ListCrudRepository<WeaponUpgradeN, Long> {
}
