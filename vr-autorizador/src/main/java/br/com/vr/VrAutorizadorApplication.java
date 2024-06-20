package br.com.vr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.vr"})
@EnableJpaRepositories(basePackages = {"br.com.vr.infrastructure.repository"})
public class VrAutorizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VrAutorizadorApplication.class, args);
	}

}
