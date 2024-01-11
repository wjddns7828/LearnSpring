package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * 기획자의 의견 변경으로 할인 정책을 정액 할인제에서 정률 할인제로 변경 되었기에 해당 정률 변경에 대한 로직
 */
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
