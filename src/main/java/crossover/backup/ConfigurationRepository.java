package crossover.backup;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
public interface ConfigurationRepository extends CrudRepository<ConfigurationEntity, Integer> {
	
	/**
	 * find configuration list by source ip which is not complete (runEndTimeStamp is null)
	 * 
	 * @param sourceIP
	 * @return configuation entities list with run time stamp past (<=) current time (due) and not complete
	 */
	@Query("SELECT c FROM ConfigurationEntity c where c.sourceIP = ?1 and c.runStartTimestamp <=  now() and c.runEndTimestamp is null")
	public List<ConfigurationEntity> findBySourceIP(String sourceIP);
	
}
