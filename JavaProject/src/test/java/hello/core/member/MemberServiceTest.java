package hello.core.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;

class MemberServiceTest {

//	MemberService memberService = new MemberServiceImpl();
	
	/**
	 * 회원을 생성하고 해당 회원을 조회 하였을 떄
	 * 두개의 인스턴스가 같은지를 확인하는 테스트 케이스
	 */
	
	/**
	 * 의존관계를 주입(DI)
	 */
	MemberService memberService;
	
	@BeforeEach
	public void each() {
		memberService = new AppConfig().memberService();
	}
	
	@Test
	void Join() {
		//given
		Member member = new Member(1L, "memberA", Grade.VIP);
		//when
		memberService.join(member);
		Member findMember = memberService.findById(1L);
		//then
		assertEquals(member, findMember);
	}
}
