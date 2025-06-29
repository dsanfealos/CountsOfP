package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.Armor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorDAO extends JpaRepository<Armor, Long> {
}
