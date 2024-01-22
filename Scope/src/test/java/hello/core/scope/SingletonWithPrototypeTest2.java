package hello.core.scope;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;

//protoType과 singleton 같이 사용시 해결법1
//ApplicationContext를 자동주입시켜 사용하기 (DL) Dependency Lookup 의존관계 탐색(조회)
//class SingletonWithPrototypeTest2 {
//
//	@Test
//	void protoTypeFind() {
//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
//		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
//		prototypeBean1.addCount();
//		assertSame(prototypeBean1.getCount(), 1);
//		
//		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
//		prototypeBean2.addCount();
//		assertSame(prototypeBean2.getCount(), 1);
//	}
//	
//	@Test
//	void singletonClientUseProtoType() {
//		AnnotationConfigApplicationContext ac = 
//				new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
//		ClientBean clientBean1 = ac.getBean(ClientBean.class);
//		int count1 = clientBean1.logic();
//		assertSame(count1, 1);
//		ClientBean clientBean2 = ac.getBean(ClientBean.class);
//		int count2 = clientBean2.logic();
//		assertSame(count2, 1);
//				
//	}
//	
//	@Scope("singleton")
//	static class ClientBean{
//		@Autowired
//		private ApplicationContext ac;
//		
//		public int logic() {
//			PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
//			prototypeBean.addCount();
//			return prototypeBean.getCount();
//		}
//	}
//	
//	@Scope("prototype")
//	static class PrototypeBean {
//		private int count = 0;
//		
//		public void addCount(){
//			count++;
//		}
//		
//		public int getCount() {
//			return count;
//		}
//		
//		@PostConstruct
//		public void init() {
//			System.out.println("PorototypeBean.init" + this);
//		}
//		
//		@PreDestroy
//		public void destroy() {
//			System.out.println("PrototypeBean.destroy"+this);
//		}
//	}
//}

//Spring>>Provider 사용
//class SingletonWithPrototypeTest2 {
//	
//	@Test
//	void protoTypeFind() {
//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
//		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
//		prototypeBean1.addCount();
//		assertSame(prototypeBean1.getCount(), 1);
//		
//		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
//		prototypeBean2.addCount();
//		assertSame(prototypeBean2.getCount(), 1);
//	}
//	
//	@Test
//	void singletonClientUseProtoType() {
//		AnnotationConfigApplicationContext ac = 
//				new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
//		ClientBean clientBean1 = ac.getBean(ClientBean.class);
//		int count1 = clientBean1.logic();
//		assertSame(count1, 1);
//		ClientBean clientBean2 = ac.getBean(ClientBean.class);
//		int count2 = clientBean2.logic();
//		assertSame(count2, 1);
//		
//	}
//	
//	@Scope("singleton")
//	static class ClientBean {
//
//	    private final ObjectProvider<PrototypeBean> prototypeBeanProvider;
//
//	    @Autowired
//	    public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
//	        this.prototypeBeanProvider = prototypeBeanProvider;
//	    }
//
//	    public int logic() {
//	        PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
//	        prototypeBean.addCount();
//	        return prototypeBean.getCount();
//	    }
//	}
//	
//	@Scope("prototype")
//	static class PrototypeBean {
//		private int count = 0;
//		
//		public void addCount(){
//			count++;
//		}
//		
//		public int getCount() {
//			return count;
//		}
//		
//		@PostConstruct
//		public void init() {
//			System.out.println("PorototypeBean.init" + this);
//		}
//		
//		@PreDestroy
//		public void destroy() {
//			System.out.println("PrototypeBean.destroy"+this);
//		}
//	}
//}

//jakart>inject라이브러리 사용
class SingletonWithPrototypeTest2 {
	
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
		assertSame(count2, 1);
		
	}
	
	@Scope("singleton")
	static class ClientBean {
		
		private final Provider<PrototypeBean> prototypeBeanProvider;
		
		@Autowired
		public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
			this.prototypeBeanProvider = prototypeBeanProvider;
		}
		
		public int logic() {
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
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
