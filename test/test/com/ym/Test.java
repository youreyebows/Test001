package test.com.ym;

public class Test {

	public static void main(String[] args) {
		
		name();
	}
	
	private static void name() {
		for (int i = 0; i < 5; i++) {
			System.out.println("i___>"+i);
			if (i==2) {
				haha();
				return ;
			}
		}
		
		System.out.println("for之外");
	}
	
	private static void haha() {
		System.out.println("hahaah ");
	}
}
