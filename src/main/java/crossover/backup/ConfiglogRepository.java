package crossover.backup;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
public interface ConfiglogRepository extends CrudRepository<ConfiglogEntity, Integer> {
	@Query("SELECT c FROM ConfiglogEntity c where c.configurationEntity.id = ?1")
    public List<ConfiglogEntity> findByBackupID(int backupID);
	
}
