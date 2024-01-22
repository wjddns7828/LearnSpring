package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//public class NetworkClient implements InitializingBean, DisposableBean	{
//	
//	private String url; 
//	
//	public NetworkClient() {
//		System.out.println("생성자 호출, URL : "+url);
//	}
//	
//	public void setUrl(String url) {
//		this.url = url;
//	}
//	
//	public void connect() {
//		System.out.println("connect : " + url);
//	}
//
//	public void call(String message) {
//		System.out.println("call : " + url + " message = " + message);
//	}
//	
//	public void disconnect() {
//		System.out.println("close : " + url);
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		connect();
//		call("초기화 연결 메세지");
//	}
//
//	@Override
//	public void destroy() throws Exception {
//		connect();
//	}
//	

//Bean 등록으로 초기화
	public class NetworkClient {
		
		private String url; 
		
		public NetworkClient() {
			System.out.println("생성자 호출, URL : "+url);
		}
		
		public void setUrl(String url) {
			this.url = url;
		}
		
		public void connect() {
			System.out.println("connect : " + url);
		}
		
		public void call(String message) {
			System.out.println("call : " + url + " message = " + message);
		}
		
		public void disconnect() {
			System.out.println("close : " + url);
		}
		
		@PostConstruct
		public void init(){
			connect();
			call("초기화 연결 메세지");
		}
		
		@PreDestroy
		public void close(){
			connect();
		}

}
