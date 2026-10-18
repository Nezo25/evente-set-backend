package tfs.evente_set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventeSetApplication {

	public static void main(String[] args) {
		System.setProperty("spring.jpa.hibernate.ddl-auto", "update");
		SpringApplication.run(EventeSetApplication.class, args);
	}

}
