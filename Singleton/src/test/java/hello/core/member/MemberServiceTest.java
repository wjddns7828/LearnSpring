package hello.core.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

class MemberServiceTest {

	@Test
	void Join() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
		//given
		Member member = new Member(1L, "memberA", Grade.VIP);
		//when
		memberService.join(member);
		Member findMember = memberService.findById(1L);
		//then
		System.out.println(findMember.getName());
		assertEquals(member, findMember);
	}
}
