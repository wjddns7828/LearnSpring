package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 구현체 주입을 위한 AppConfig
 */
public class AppConfig {
//	@Override
//	public MemberService memberService() {
//		return new MemberServiceImpl(new MemoryMemberRepository());
//	}
//	
//	@Override
//	public OrderService orderService() {
//		return new OrderServiceImpl(new MemoryMemberRepository(), new FixedDiscountPolicy());
//				
//	}
	
/**
 * 중복 및 가독성을 위한 AppConfig 리팩토리
 */
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
				
	}
	public DiscountPolicy discountPolicy() {
//		return new FixedDiscountPolicy();
		return new RateDiscountPolicy();
	}
}
