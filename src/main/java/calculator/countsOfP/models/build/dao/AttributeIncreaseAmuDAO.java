package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.Amulet;
import calculator.countsOfP.models.build.AttributeIncreaseAmu;
import calculator.countsOfP.models.player.Attribute;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeIncreaseAmuDAO extends ListCrudRepository<AttributeIncreaseAmu, Long> {
    Optional<AttributeIncreaseAmu> findByAmuletAndAttribute(Amulet amulet, Attribute attribute);
}
