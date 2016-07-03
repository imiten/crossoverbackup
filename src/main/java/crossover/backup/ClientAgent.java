package crossover.backup;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientAgent implements CommandLineRunner {
	private static final String host = "http://localhost:8080";
	private static final Logger log = LoggerFactory.getLogger(ClientAgent.class);

	public static void main(String args[]) {
		SpringApplication.run(ClientAgent.class);
	}

	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String api = host + "/agent/configuration";
		ResponseEntity<List<ConfigurationEntity>> ceResponse =
				restTemplate.exchange(api,
						HttpMethod.GET, null, 
						new ParameterizedTypeReference<List<ConfigurationEntity>>() {
				});
		List<ConfigurationEntity> configs = ceResponse.getBody();
		for(ConfigurationEntity ce: configs) {
			log.info(ce.toString());
		}
	}
}
