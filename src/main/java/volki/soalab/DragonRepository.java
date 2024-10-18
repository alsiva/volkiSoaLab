package volki.soalab;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import volki.soalab.entities.DragonEntity;



@Repository
public interface DragonRepository extends CrudRepository<DragonEntity, Long> {

}
