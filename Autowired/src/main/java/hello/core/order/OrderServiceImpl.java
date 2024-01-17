package hello.core.order;

import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.Order;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	
//	//Lombok 사용시
//	private final MemberRepository mr;
//	private final DiscountPolicy dp;
	
	//생성자 주입
	private final MemberRepository mr;
	
	private final DiscountPolicy dp;

//	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.mr = memberRepository;
		this.dp = discountPolicy;
	}

	//수정자 주입(Set주입)
//	private MemberRepository mr;
//	private DiscountPolicy dp;
//	
//	@Autowired
//	public void setMr(MemberRepository mr) {
//		this.mr = mr;
//	}
//	
//	@Autowired
//	public void setDp(DiscountPolicy dp) {
//		this.dp = dp;
//	}	
	
	//일반 메소드 주입	
//	private MemberRepository mr;
//	private DiscountPolicy dp;
//	
//	@Autowired
//	public void init(MemberRepository mr, DiscountPolicy dp) {
//		this.mr = mr;
//		this.dp = dp;
//	}

	//Qulifier 사용 예시
//		private final MemberRepository mr;
//		private final DiscountPolicy dp;

//		public OrderServiceImpl(MemberRepository memberRepository, 
//								@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//			this.mr = memberRepository;
//			this.dp = discountPolicy;
//		}
		
//		Primary
//		public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//			this.mr = memberRepository;
//			this.dp = discountPolicy;
//		}
		
//		사용자 정의 annotation
//		public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//			this.mr = memberRepository;
//			this.dp = discountPolicy;
//		}
		
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
