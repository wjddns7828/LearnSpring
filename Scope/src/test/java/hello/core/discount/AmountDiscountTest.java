package hello.core.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.discount.Order;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

class AmountDiscountTest {

	//DIP 위반
//	MemberService memberService = new MemberServiceImpl();
//	OrderService orderService = new OrderServiceImpl();
	
	//DIP 위반 X
	MemberService memberService;
	OrderService orderService;
	@BeforeEach
	public void each() {
		memberService = new AppConfig().memberService();
		orderService = new AppConfig().orderService();
	}
	
	/**
	 * VIP회원을 생성 후 해당 회원이 주문을 하였을 떄 해당 회원의 할인 금액이
	 * 1000원인지 확인하는 테스트 케이스
	 */
	@Test
	void createOrder() {
		//given
		Long memberId = 1L;
		Member member = new Member(memberId,"memberB" ,Grade.VIP);
		memberService.join(member);
		//when
		Order order = orderService.createOrder(memberId,"itemA", 20000);
		//then
		System.out.println(order);
		assertEquals(order.getDiscountPrice(), 2000);
	}
}
