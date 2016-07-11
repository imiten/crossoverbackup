package crossover.backup;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

//@SpringBootApplication
public class ClientAgent implements CommandLineRunner {
	private static final String host = "http://localhost:8080/crossoverbackup";
	private static final Logger log = LoggerFactory.getLogger(ClientAgent.class);
	private RestTemplate restTemplate;
	private int min = 3;
	private int max = 6;

	public static void main(String args[]) {
		SpringApplication.run(ClientAgent.class);
	}

	@Override
	public void run(String... args) throws Exception {
		String srcip = "10.135.140.11";
		if(args.length == 1) {
			srcip = args[0];
		}
		restTemplate = new RestTemplate();
		String api = "/agent/configuration";
		URI targetUrl= UriComponentsBuilder.fromUriString(host)
				.path(api)
				.queryParam("sourceIP", srcip)
				.build()
				.toUri();
		ResponseEntity<List<ConfigurationEntity>> ceResponse =
				restTemplate.exchange(targetUrl,
						HttpMethod.GET, null, 
						new ParameterizedTypeReference<List<ConfigurationEntity>>() {
				});

		List<ConfigurationEntity> configs = ceResponse.getBody();

		for(ConfigurationEntity ce: configs) {
			log.info(ce.toString());
			log.info("Starting");
			doBackup(ce);
			log.info("Finished");
		}
	}

	private void doBackup(ConfigurationEntity ce) {
		// TODO Auto-generated method stub
		String api = host + "/agent/configlog";
		ConfiglogVO vo = new ConfiglogVO();

		vo.log = "Starting...";
		vo.status = ConfiglogVO.Status.STARTED.ordinal();
		vo.srcip = ce.getSourceIP();
		vo.configid = ce.getId();
		
		HttpEntity<ConfiglogVO> request = new HttpEntity<>(vo);
		ResponseEntity<ConfiglogEntity> response = restTemplate.
				exchange(api, HttpMethod.POST, request, ConfiglogEntity.class);
		log.debug("log insert resp:" + response.getBody().getId());
		for (int i=1; i<3; i++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(min, max + 1));
				vo.log = "Processing Stage: " + i;
				vo.status = ConfiglogVO.Status.PROGRESS.ordinal();
				request = new HttpEntity<>(vo);
				response = restTemplate.
						exchange(api, HttpMethod.POST, request, ConfiglogEntity.class);
				log.debug("log insert resp:" + response.getBody().getId());

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		vo.log = "Ending...";
		vo.status = ConfiglogVO.Status.DONE.ordinal();
		request = new HttpEntity<>(vo);
		response = restTemplate.
				exchange(api, HttpMethod.POST, request, ConfiglogEntity.class);
		log.debug("log insert resp:" + response.getBody().getId());

	}
}
