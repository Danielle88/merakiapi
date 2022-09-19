package br.com.fiap.merakiapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MerakiapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerakiapiApplication.class, args);
	}

}
