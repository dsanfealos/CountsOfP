package calculator.countsOfP.models.player.dao;

import calculator.countsOfP.models.player.Attribute;
import calculator.countsOfP.models.player.Stat;
import calculator.countsOfP.models.player.StatIncreaseAtt;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface StatIncreaseDAO extends ListCrudRepository<StatIncreaseAtt, Long> {
    StatIncreaseAtt findByAttributeAndAttributeValueAndStat(Attribute attribute, Integer value, Stat stat);
    List<StatIncreaseAtt> findByAttributeAndAttributeValueBetween(Attribute attribute, Integer initialValue, Integer finalValue);
}
