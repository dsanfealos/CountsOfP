package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.Armor;
import calculator.countsOfP.models.build.StatIncreaseArmor;
import calculator.countsOfP.models.player.Stat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatIncreaseArmorDAO extends ListCrudRepository<StatIncreaseArmor, Long> {
    @Query("SELECT sia FROM StatIncreaseArmor sia WHERE sia.armor IN :armors AND sia.stat IN :stats")
    List<StatIncreaseArmor> findByArmorsAndStats(@Param("armors") List<Armor> armors, @Param("stats") List<Stat> stats);
}
