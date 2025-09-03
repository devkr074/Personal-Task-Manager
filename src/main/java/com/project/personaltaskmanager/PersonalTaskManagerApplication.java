package com.project.personaltaskmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class PersonalTaskManagerApplication {
	private static final Logger logger = LoggerFactory.getLogger(PersonalTaskManagerApplication.class);
	private final Environment environment;

	public PersonalTaskManagerApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		try {
			SpringApplication.run(PersonalTaskManagerApplication.class, args);
		} catch (Exception e) {
			logger.error("Failed to start Personal Task Manager Application", e);
			System.exit(1);
		}
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		String port = environment.getProperty("server.port", "8080");
		String contextPath = environment.getProperty("server.servlet.context-path", "");
		String profiles = String.join(", ", environment.getActiveProfiles());
		logger.info("=================================================================");
		logger.info("    Personal Task Manager Application Started Successfully");
		logger.info("=================================================================");
		logger.info("    Application URL: http://localhost:{}{}", port, contextPath);
		logger.info("    H2 Console URL:  http://localhost:{}{}/h2-console", port, contextPath);
		logger.info("    Active Profiles: {}", profiles.isEmpty() ? "default" : profiles);
		logger.info("    Java Version:    {}", System.getProperty("java.version"));
		logger.info("    Spring Boot:     {}",
				org.springframework.boot.SpringBootVersion.getVersion());
		logger.info("=================================================================");
		logImportantConfiguration();
	}

	private void logImportantConfiguration() {
		logger.debug("Database URL: {}", environment.getProperty("spring.datasource.url"));
		logger.debug("H2 Console Enabled: {}", environment.getProperty("spring.h2.console.enabled"));
		logger.debug("JPA DDL Auto: {}", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		logger.debug("Thymeleaf Cache: {}", environment.getProperty("spring.thymeleaf.cache"));
		logger.debug("DevTools Enabled: {}", environment.getProperty("spring.devtools.restart.enabled"));
	}
}