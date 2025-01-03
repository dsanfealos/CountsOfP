package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.Amulet;
import calculator.countsOfP.models.build.StatIncreaseAmu;
import calculator.countsOfP.models.player.Stat;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface StatIncreaseAmuDAO extends ListCrudRepository<StatIncreaseAmu, Long> {
    Optional<StatIncreaseAmu> findByAmuletAndStat(Amulet amulet, Stat stat);
}
