package com.kkukielka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsApplication {



	public static void main(String[] args) throws Exception {

//		ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//				.setPersistenceEnabled(false)
//				.setJournalDirectory("target/data/journal")
//				.setSecurityEnabled(false)
//				.addAcceptorConfiguration("invm", "vm://0"));
//
//		server.start();


		SpringApplication.run(JmsApplication.class, args);
	}

}
