package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
	
	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac =  new AnnotationConfigApplicationContext(TestConfig.class);
		
		StatefulService service1 = ac.getBean("statefulService",StatefulService.class);
		StatefulService service2 = ac.getBean("statefulService",StatefulService.class);
		
		//ThreadA : 사용자A 가격 10000
		service1.order("memberA", 10000);
		//ThreadB : 사용자B 가격 20000
		service1.order("memberB", 20000);
		
		//ThreadA: 사용자A 주문 금액 조회
		 int price = service1.getPrice();
		 //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
		 System.out.println("price = " + price);
		 Assertions.assertThat(service1.getPrice()).isEqualTo(20000);
	
	}
	
	
	//테스트를 위한 컨테이너
	static class TestConfig{
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}
