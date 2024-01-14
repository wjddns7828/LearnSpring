package hello.core.discount;

import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {
	
	//10프로의 할인률 적용
	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		
		if(member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		}else {
			return 0;
		}
	}

}
