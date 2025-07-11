package calculator.countsOfP.models.weapon.dao;

import calculator.countsOfP.models.weapon.Handle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandleDAO extends ListCrudRepository<Handle, Long> {
    @Query("SELECT u FROM Handle u WHERE u.name LIKE %?1%")
    List<Handle> search(String keyword);
}
