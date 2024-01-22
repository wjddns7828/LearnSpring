package hello.core.autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

class AllBeanTest {

	@Test
	void findAllBean() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
		DiscountService discountService = ac.getBean(DiscountService.class);
		Member member = new Member(1L,"memberA", Grade.VIP);
		int discoutPrice = discountService.discount(member,10000,"fixedDiscountPolicy");
//		int discoutPrice = discountService.discount(member,20000,"rateDiscountPolicy");
		
		assertEquals(discoutPrice, 1000);
		System.out.println(discoutPrice);
		
	}
	static class DiscountService{
		
		private final Map<String, DiscountPolicy> policyMap;
		private final List<DiscountPolicy> policyList;
		
		@Autowired
		public DiscountService(Map<String,DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
			this.policyMap = policyMap;
			this.policyList = policyList;
			System.out.println("Map = " + policyMap);
			System.out.println("List = " + policyList);
		}

		public int discount(Member member, int i, String discountCode) {
			DiscountPolicy discountPolicy = policyMap.get(discountCode);
			
			System.out.println("discountCode  : "+discountCode);
			System.out.println("discoutPolicy : " + discountPolicy );
			
			return discountPolicy.discount(member, i);
		}
	}
}
