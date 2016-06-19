package crossover.backup;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
public interface ConfigurationRepository extends CrudRepository<ConfigurationEntity, Integer> {
	
	/**
	 * find configuration list by source ip
	 * 
	 * @param sourceIP
	 * @return configuation entities list with run time stamp past (>=) current time (not expired)
	 */
	@Query("SELECT c FROM ConfigurationEntity c where c.sourceIP = ?1 and c.runStartTimestamp >= now()")
	public List<ConfigurationEntity> findBySourceIP(String sourceIP);
	
}
