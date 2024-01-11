package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

class ApplicationContextExtendsFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

	@Configuration
	static class TestConfig {
		@Bean
		DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}
		@Bean
		DiscountPolicy fixedDiscountPolicy() {
			return new FixedDiscountPolicy();
		}
	}
	
	@Test
	@DisplayName("부모타입으로 조회 했을 시 자식이 둘 이상 있을 경우 중복 에러가 발생함")
	void findBeanByParentType() {
//		DiscountPolicy discountPolicy =  ac.getBean(DiscountPolicy.class);
		assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
	}
	
	@Test
	@DisplayName("부모 타입으로 조회시, 자식이 둘 이상일 경우 이름을 지정하면 중복 에러가 제거된다")
	void findByName() {
		DiscountPolicy rateDiscoutPolicy =  ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(rateDiscoutPolicy).isInstanceOf(RateDiscountPolicy.class);
	}
	
	@Test
	@DisplayName("부모타입의 모든 자식 Bean 조회하기")
	void findAllChildBean() {
		Map<String,DiscountPolicy> discountPolicy = ac.getBeansOfType(DiscountPolicy.class);
		for (String key : discountPolicy.keySet()) {
			System.out.println("Key : " + key + " / " + "Value : " +discountPolicy.get(key));
		}
		assertThat(discountPolicy.size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("부모타입(Object)의 모든 자식 Bean 조회하기")
	void findAllChildBeanOfObject() {
		Map<String, Object> discountPolicy = ac.getBeansOfType(Object.class);
		for (String key : discountPolicy.keySet()) {
			System.out.println("Key : " + key + " / " + "Value : " +discountPolicy.get(key));
		}
		assertThat(discountPolicy).isNotNull();
	}
}
