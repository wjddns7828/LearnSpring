package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class BeanLifecycleTest {

	@Test
	public void lifeCycle() {
		ConfigurableApplicationContext  ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient networkClient = ac.getBean(NetworkClient.class);
		ac.close();
	}
	
	@Configuration
	static class LifeCycleConfig{
		
		@Bean
//		@Bean(initMethod = "init", destroyMethod = "close")
		public NetworkClient networkClient() {
			NetworkClient networkClient =  new NetworkClient();
			networkClient.setUrl("www.naver.com");
			return networkClient;
		}
	}
	
}
