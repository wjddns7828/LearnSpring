package hello.core.singleton;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

class SingletonTest {

//	@Test
	@DisplayName("순수 자바객체 테스트")
	void PureContainer() {
		AppConfig appconfig = new AppConfig();
		//1. 조회 : 호출 할 떄 마다 객체 생성
		MemberService memberservice1 = appconfig.memberService();
		//2. 조회 : 호출 할 떄 마다 객체 생성
		MemberService memberservice2 = appconfig.memberService();
		
		//3. 객체의 참조값이 다름 즉 다른 객체를 생성함
		System.out.println("memberService1 : " + memberservice1);
		System.out.println("memberService2 : " + memberservice2);
		
		//4. memberservice1 과 memberservice2는 다름
		assertNotSame(memberservice1, memberservice2);
	}
	//즉 호출 할 때 마다 다른 객체가 생성되며 객체 트레픽이 초당 100개면 100번 생성됨. 메모리 낭비가 너무 심함
	//해결 방안으로는 객체를 딱 하나 생성하고 공유하게 설정 -> 싱글톤 패턴을 입히면 됨
	
	@Test
	@DisplayName("싱글톤 패턴 적용 객체 사용")
	void singletonServiceTest() {
		SingletonService sigletonSingletoneService1 = SingletonService.getInstance();
		SingletonService sigletonSingletoneService2 = SingletonService.getInstance();
		
		System.out.println("singletonService1 : " + sigletonSingletoneService1);
		System.out.println("singletonService2 : " + sigletonSingletoneService2);
		sigletonSingletoneService2.logic();
		
		assertSame(sigletonSingletoneService1, sigletonSingletoneService2);
	}
	
	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {
		
//		AppConfig appconfig = new AppConfig();
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		//1. 조회 : 호출 할 떄 마다 객체 생성
		MemberService memberservice1 = ac.getBean("memberService",MemberService.class);
		//2. 조회 : 호출 할 떄 마다 객체 생성
		MemberService memberservice2 = ac.getBean("memberService",MemberService.class);
		
		//3. 객체의 참조값이 다름 즉 다른 객체를 생성함
		System.out.println("memberService1 : " + memberservice1);
		System.out.println("memberService2 : " + memberservice2);
		
		//4. memberservice1 과 memberservice2는 다름
		assertSame(memberservice1, memberservice2);
	}
}
