package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.Armor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorDAO extends ListCrudRepository<Armor, Long> {
}
