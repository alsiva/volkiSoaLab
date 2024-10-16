package volki.soalab;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import volki.soalab.entities.Dragon;



@Repository
public interface DragonRepository extends CrudRepository<Dragon, Long> {

}
