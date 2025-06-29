package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.POrgan;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface POrganDAO extends ListCrudRepository<POrgan, Long> {
}
