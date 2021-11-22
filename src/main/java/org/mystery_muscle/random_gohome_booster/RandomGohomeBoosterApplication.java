package org.mystery_muscle.random_gohome_booster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RandomGohomeBoosterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomGohomeBoosterApplication.class, args);
	}

}
