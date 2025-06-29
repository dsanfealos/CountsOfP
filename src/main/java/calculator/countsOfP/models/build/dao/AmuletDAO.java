package calculator.countsOfP.models.build.dao;

import calculator.countsOfP.models.build.Amulet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmuletDAO extends JpaRepository<Amulet, Long> {
    @Query(name= "Amulet.findAllNamed")
    List<Amulet> findAllNamed();
}
