package application;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.time.LocalTime;
import java.util.Arrays;

@Service
public class ApiClient {

	@Autowired
	ResponseService responseService;

	final RestTemplate apiClient=new RestTemplate();

	@Value("${api.url}")
	String url;

	@Scheduled(cron = "${cron.expression}")
	public ResponseEntity<String> callApi(){

		System.out.println(LocalTime.now());

		String latestEmployeeDetails = getLatestEmployeeDetails(getAuthToken());

		ApiResponse response = new ApiResponse();
		response.setResponse(latestEmployeeDetails);
		responseService.save(response);

		return ResponseEntity.ok(latestEmployeeDetails);
	}

	private String getLatestEmployeeDetails(String jwtToken) {
		HttpHeaders requestHeader = new HttpHeaders();
		requestHeader.set("Authorization",jwtToken);
		HttpEntity requestBody = new HttpEntity(requestHeader);
		ResponseEntity<String> data = apiClient.exchange(url+"api/v1/employees", HttpMethod.GET
				,requestBody,String.class);

		return data.getBody();
	}

	private String getAuthToken(){
		HttpHeaders contentType = new HttpHeaders();
		contentType.setContentType(MediaType.APPLICATION_JSON);
		String authenticationPayload = "{\n" +
				"    \"username\" : \"admin\",\n" +
				"    \"password\" : \"admin\"\n" +
				"}";
		HttpEntity<String> requestBody = new HttpEntity(authenticationPayload,contentType);
		ResponseEntity<String> token = apiClient.exchange(url+"authenticate", HttpMethod.POST
				,requestBody,String.class);

		String tokenString = token.getBody();
		tokenString=tokenString.replace("token","Bearer ");
		tokenString = tokenString.replace("{","");
		tokenString = tokenString.replace("}","");
		tokenString = tokenString.replace("\"","");
		tokenString = tokenString.replace(":","");
		System.out.println(tokenString);
		return tokenString;
	}
}
