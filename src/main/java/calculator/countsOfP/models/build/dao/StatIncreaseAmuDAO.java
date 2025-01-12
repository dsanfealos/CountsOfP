package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.Amulet;
import calculator.countsOfP.models.build.StatIncreaseAmu;
import calculator.countsOfP.models.player.Stat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatIncreaseAmuDAO extends ListCrudRepository<StatIncreaseAmu, Long> {
    Optional<StatIncreaseAmu> findByAmuletAndStat(Amulet amulet, Stat stat);
    @Query("SELECT sia FROM StatIncreaseAmu sia WHERE sia.amulet IN :amulets AND sia.stat IN :stats")
    List<StatIncreaseAmu> findByAmuletsAndStats(@Param("amulets") List<Amulet> amulets, @Param("stats") List<Stat> stats);
}
