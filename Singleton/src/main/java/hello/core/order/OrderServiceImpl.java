package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.Order;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
	
	//DIP 위반 예제
//	private final MemberRepository mr = new MemoryMemberRepository();
	
//	private final DiscountPolicy dp = new FixedDiscountPolicy();
//	private final DiscountPolicy dp = new RateDiscountPolicy();
	/**
	 * [해당 서비스의 문제점]
	 * 인터페이스와 구현체의 분리는 하였고 다형성을 활용하여, 인터페이스와 구현 객체를 분리했다
	 * OCP,DIP 같은 객체지향 설계원칙에 준수 하였는가? ==> NO
	 * 
	 * DIP : OrderServiceImpl은 인터페이스 뿐만이 아니라 구현체 클래스에도 의존하고 있다
	 * 		인터페이스 : DiscountPolicy
	 * 		구현체 : FIxedDiscountPolicy, RateDiscountPolicy
	 * 
	 * OCP : 변경하지 않으면 확장할 수 없다.
	 * 
	 */
	
	//DIP를 위반하지 않고 인터페이스만 의존하게 변경
	private final MemberRepository mr;
	private DiscountPolicy dp;
	/**
	 * 문제점 : 구현체가 없기 떄문에 NullPointException 발생
	 * 
	 * 해결법 : DiscountPolicy에 구현체를 주입해주어야함
	 */
	//생성자 주입
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.mr = memberRepository;
		this.dp = discountPolicy;
	}
	
	@Override
	public Order createOrder(Long memberId, String itemName, int price) {
		
		Member member = mr.findById(memberId);
		int discountPrice = dp.discount(member, price);
		
		return new Order(memberId,itemName,price,discountPrice);
	}
	
	//테스트용도
	public MemberRepository getMemberRepository() {
		return mr;
	}

}
