package com.docker.container;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class SpringbootDockerize1ApplicationTests {

	@Test
	public void whenTokenDontContainIssuer_thenSuccess() {

		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 30; i++) {
			Runnable tokenRunner = new TokenRunnable();
			executor.execute(tokenRunner);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");

	}

}
