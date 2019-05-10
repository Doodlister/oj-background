package cn.doodlister.onlinejudge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class OnlinejudgeApplication {
	private static final Logger log = LoggerFactory.getLogger(OnlinejudgeApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OnlinejudgeApplication.class, args);

	}



}

