package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class Scheduler {
	public static void main(String[] args) {
		SpringApplication.run(Scheduler.class,args);
	}

	// @PostConstruct
	// void init() {
	// 	TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	// }
}
