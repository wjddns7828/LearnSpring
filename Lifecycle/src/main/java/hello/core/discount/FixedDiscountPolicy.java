package hello.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

@Component
//@Qualifier("fixedDiscountPolicy")
//@MainDiscountPolicy
public class FixedDiscountPolicy implements DiscountPolicy{
	private int discountFixAmount = 1000;
	@Override
	public int discount(Member member, int price) {
		if(member.getGrade() == Grade.VIP) {
			return discountFixAmount;
		}else {
			return 0;
		}
	}
}
