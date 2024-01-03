package hello.core.discount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 할인 정책 변경으로 인하여 정률 할인정책 테스트케이스
 */
class RateDiscountTest {

	//DIP 위반
//	MemberService memberService = new MemberServiceImpl();
//	OrderService orderService = new OrderServiceImpl();
	
	//DIP 위반 X
	MemberService memberService = new AppConfig().memberService();
	OrderService orderService = new AppConfig().orderService();
	
	//해당 회원이 VIP인 경우
	@Test
	void VIP() {
		//given
		Long memberId = 1L;
		Member member = new Member(memberId, "memberVIP", Grade.VIP);
		memberService.join(member);
		//when
		Order vipResult = orderService.createOrder(memberId, "itemVIP", 20000);
		//then
		System.out.println(vipResult);
		assertEquals(vipResult.getDiscountPrice(), 2000);
	}
	
	//VIP 회원이 아닐 경우
	@Test
	void BASIC() {
		//given
		Long memberId2 = 2L;
		Member member2 = new Member(memberId2, "memberBASIC", Grade.BASIC);
		memberService.join(member2);
		//when
		Order vipResult = orderService.createOrder(memberId2, "itemVIP", 10000); 
		//then
		System.out.println(vipResult);
		assertEquals(vipResult.getDiscountPrice(), 0);
	}
	
	

}
