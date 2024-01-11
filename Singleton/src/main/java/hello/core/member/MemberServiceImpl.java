package hello.core.member;

public class MemberServiceImpl implements MemberService {

	//DIP 위반
//	private final MemberRepository mr = new MemoryMemberRepository();
	
	//DIP를 위반하지 않았을 떄
	private final MemberRepository mr;
	
	//생성자 주입
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.mr = memberRepository;
	}

	@Override
	public void join(Member member) {
		mr.save(member);
	}
	
	@Override
	public Member findById(Long memberId) {
		return mr.findById(memberId);
	}
	
	//테스트 용도
	public MemberRepository memberRepository() {
		return mr;
	}
}
