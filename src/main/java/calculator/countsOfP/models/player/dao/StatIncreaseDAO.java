package calculator.countsOfP.models.player.dao;

import calculator.countsOfP.models.player.Attribute;
import calculator.countsOfP.models.player.Stat;
import calculator.countsOfP.models.player.StatIncrease;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface StatIncreaseDAO extends ListCrudRepository<StatIncrease, Long> {
    List<StatIncrease> findByAttribute(Attribute attribute);
    List<StatIncrease> findByAttributeAndStat (Attribute attribute, Stat stat);

    List<StatIncrease> findByAttributeAndAttributeValueBetween(Attribute attribute, Integer initialValue, Integer finalValue);
}
