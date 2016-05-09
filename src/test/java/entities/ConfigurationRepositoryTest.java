package entities;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import crossover.backup.BackupController;
import crossover.backup.ConfigurationEntity;
import crossover.backup.ConfigurationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BackupController.class)
@WebIntegrationTest
public class ConfigurationRepositoryTest {
  @Autowired
  ConfigurationRepository repository;
  
    @Test
	public void insert() throws Exception {
		ConfigurationEntity ce = new ConfigurationEntity("local", 
				"10.135.140.11", "d\\software", "miten.mehta", "password1",
                "10.135.140.12", "d\\software\\backup", "miten.mehta", "password2", 
                new Date());
		ce.setCreateUser("admin");
		ce = repository.save(ce);
		ce = repository.findOne(ce.getId());
		Assert.assertNotNull(ce.getCreateTimestamp());
		ce.setName("local1");
		repository.save(ce);
		ce = repository.findOne(ce.getId());
		Assert.assertEquals("name not local1- update fails", "local1", ce.getName());

	}
    
    
}
