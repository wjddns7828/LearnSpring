package hello.core.scope;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//ProtoType과 Singleton을 같이 사용했을 떄 문제점
class SingletonWithPrototypeTest1 {

	@Test
	void protoTypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertSame(prototypeBean1.getCount(), 1);
		
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertSame(prototypeBean2.getCount(), 1);
	}
	
	@Test
	void singletonClientUseProtoType() {
		AnnotationConfigApplicationContext ac = 
				new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertSame(count1, 1);
		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertSame(count2, 2);
				
	}
	
	@Scope("singleton")
	static class ClientBean{
		private PrototypeBean prototypeBean;

		@Autowired
		public ClientBean(PrototypeBean prototypeBean) {
			this.prototypeBean = prototypeBean;
		}
		
		public int logic() {
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}
	}
	
	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;
		
		public void addCount(){
			count++;
		}
		
		public int getCount() {
			return count;
		}
		
		@PostConstruct
		public void init() {
			System.out.println("PorototypeBean.init" + this);
		}
		
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy"+this);
		}
	}
}
