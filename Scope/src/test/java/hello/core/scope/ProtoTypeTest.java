package hello.core.scope;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

class ProtoTypeTest {

	@Test
	void prototypeTest() {
		AnnotationConfigApplicationContext ac = 
				new AnnotationConfigApplicationContext(ProtoTypeBean.class);
		System.out.println("findProtoTypeBean1"); 
		ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class); //<<이 때 객체 생성됨 init이 print됨
		System.out.println("findProtoTypeBean2");
		ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);//<<이 때 객체 생성됨 init이 print됨 
		
		System.out.println("protoTypeBean1 : "+ protoTypeBean1);
		System.out.println("protoTypeBean2 : "+ protoTypeBean2);
		
		assertNotSame(protoTypeBean1, protoTypeBean2); //레퍼런스가 다름
		
		ac.close();
		
//		protoTypeBean1.destroy(); // <<이런식으로 닫아줘야함
	}
	
	
	@Scope("prototype")
	static class ProtoTypeBean{
		
		@PostConstruct
		public void init() {
			System.out.println("ProtoTypeBean.init");
		}
		
		@PreDestroy
		public void destroy() {
			System.out.println("ProtoTypeBean.destroy");
		}
	}
}
