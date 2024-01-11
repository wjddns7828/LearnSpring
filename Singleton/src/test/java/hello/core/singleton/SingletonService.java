package hello.core.singleton;

public class SingletonService {
	
	//static 영역에 객체 1개 생성
	private static final SingletonService instance = new SingletonService();
	
	//public으로 열어서 객체 인스턴스가 필요하면 이 static메소드를 통해서만 조회
	public static SingletonService getInstance() {
		return instance;
	}
	
	//private 생성자를 통해 외부에서 new를 막음
	private SingletonService() {
	}
	
	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}
}
