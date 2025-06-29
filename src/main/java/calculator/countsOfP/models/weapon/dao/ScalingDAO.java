package calculator.countsOfP.models.weapon.dao;

import calculator.countsOfP.models.player.Attribute;
import calculator.countsOfP.models.weapon.Scaling;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScalingDAO extends ListCrudRepository<Scaling, Long> {
    Scaling findByAttributeAndLetterAndLevel(Attribute attribute, Character letter, Integer level);
}
