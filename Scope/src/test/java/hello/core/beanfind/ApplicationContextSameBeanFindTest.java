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

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

class ApplicationContextSameBeanFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
	
	//static을 통해 이 클래스 안에서만 사용하는 스코프 지정
	@Configuration
	static class SameBeanConfig{
		@Bean
		MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}
		@Bean
		MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
	}
	
	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있을 경우, 중복 오류가 발생한다")
	void findBeanByTypeDuplicate() {
//		MemberRepository bean = ac.getBean(MemberRepository.class);
		assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
	}
	
	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있을 경우 이름을 지정해야한다")
	void findBeanByName() {
		MemberRepository bean =  ac.getBean("memberRepository1",MemberRepository.class);
		assertThat(bean).isInstanceOf(MemberRepository.class);
	}
	
	@Test
	@DisplayName("특정 타입 모두 조회하기")
	void findAllBeanByType() {
		Map<String,MemberRepository> beanOfType = ac.getBeansOfType(MemberRepository.class);
		for (String key : beanOfType.keySet()) {
			System.out.println("key : " + key + " / " + "Value" + beanOfType.get(key));
		}
		System.out.println("BeanOfType = " + beanOfType);
		assertThat(beanOfType.size()).isEqualTo(2);
	}
}
