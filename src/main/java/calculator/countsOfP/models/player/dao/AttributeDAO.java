package calculator.countsOfP.models.player.dao;

import calculator.countsOfP.models.player.Attribute;
import org.springframework.data.repository.ListCrudRepository;

public interface AttributeDAO extends ListCrudRepository<Attribute, Long> {
}
