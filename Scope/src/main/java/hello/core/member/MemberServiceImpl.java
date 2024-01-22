package hello.core.member;

import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

	private final MemberRepository mr;
	
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
