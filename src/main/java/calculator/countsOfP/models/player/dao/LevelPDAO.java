package calculator.countsOfP.models.player.dao;

import calculator.countsOfP.models.player.LevelP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelPDAO extends JpaRepository<LevelP, Long> {
}
