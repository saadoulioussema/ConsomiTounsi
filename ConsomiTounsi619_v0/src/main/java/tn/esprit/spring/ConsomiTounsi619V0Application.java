package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import tn.esprit.spring.config.FileStorageProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({ FileStorageProperties.class })
public class ConsomiTounsi619V0Application  {

	public static void main(String[] args) {
		SpringApplication.run(ConsomiTounsi619V0Application.class, args);
		
	
	}

}
