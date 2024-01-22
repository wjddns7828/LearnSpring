package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Member;
import io.micrometer.common.lang.Nullable;

class AutowiredTest {


	@Test
	void AutowiredTest(){
		ApplicationContext ac = 
				new AnnotationConfigApplicationContext(TestBean.class);
	}
	
	static class TestBean{
		@Autowired(required = false)
		public void setNoBean1(Member member) {
			System.out.println("setNoBean1 = "+member);
		}
		@Autowired
		public void setNoBean2(@Nullable Member member) {
			System.out.println("setNoBean2 = "+member);
		}
		@Autowired
		public void setNoBean3(Optional<Member> member) {
			System.out.println("setNoBean3 = "+member);
		}
	}
}
