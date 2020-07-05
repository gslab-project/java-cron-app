package application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

@Service
public class ApiClient {


	final RestTemplate apiClient = new RestTemplate();
	@Scheduled(cron = "0 0 22 * * *")
	public ResponseEntity<String> callApi(){
		System.out.println(LocalTime.now());
		return apiClient.exchange("http://localhost:8080/", HttpMethod.GET,null,String.class);
	}
}
