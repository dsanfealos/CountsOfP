package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.Armor;
import calculator.countsOfP.models.build.StatIncreaseArmor;
import calculator.countsOfP.models.player.Stat;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface StatIncreaseArmorDAO extends ListCrudRepository<StatIncreaseArmor, Long> {
    Optional<StatIncreaseArmor> findByArmorAndStat(Armor armor, Stat stat);
}
