package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.Amulet;
import calculator.countsOfP.models.build.AttributeIncreaseAmu;
import calculator.countsOfP.models.player.Attribute;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface AttributeIncreaseAmuDAO extends ListCrudRepository<AttributeIncreaseAmu, Long> {
    Optional<AttributeIncreaseAmu> findByAmuletAndAttribute(Amulet amulet, Attribute attribute);
}
