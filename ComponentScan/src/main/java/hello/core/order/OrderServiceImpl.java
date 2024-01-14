package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.Order;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService {
	
	
	private final MemberRepository mr;
	private DiscountPolicy dp;

	@Autowired
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
