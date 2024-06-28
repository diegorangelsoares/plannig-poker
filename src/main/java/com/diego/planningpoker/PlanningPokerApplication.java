package com.diego.planningpoker;

import com.diego.planningpoker.api.configuration.ApplicationProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperty.class)
public class PlanningPokerApplication {

	public static void main(String[] args) throws UnknownHostException {
		Logger logger = LoggerFactory.getLogger(PlanningPokerApplication.class);
		SpringApplication app = new SpringApplication(PlanningPokerApplication.class);
		Environment env = app.run(args).getEnvironment();
		if (logger.isInfoEnabled()) {
			String mensageLog = """
			
			----------------------------------------------------------
			Planning Poker Service está rodando! Acesse uma das URLs:
			Local Swagger: http://localhost:{0}/swagger-ui/index.html
			Local:   http://localhost:{0}
			Externa: http://{1}:{0}
			----------------------------------------------------------
			""";
			logger.info(MessageFormat.format(mensageLog,
					env.getProperty("server.port"),
					InetAddress.getLocalHost().getHostAddress()));
		}

		log.info("APP UP");
	}

}
