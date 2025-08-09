package calculator.countsOfP.models.player.dao;

import calculator.countsOfP.models.player.Attribute;
import calculator.countsOfP.models.player.Stat;
import calculator.countsOfP.models.player.StatIncreaseAtt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatIncreaseDAO extends JpaRepository<StatIncreaseAtt, Long> {
    List<StatIncreaseAtt> findByAttributeAndAttributeValue(Attribute attribute, Integer attributeValue);
}
