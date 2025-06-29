package calculator.countsOfP.models.player.dao;

import calculator.countsOfP.models.player.Attribute;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeDAO extends ListCrudRepository<Attribute, Long> {
}
