public class ViewController implements MateSocketObserver {
	public void notify(String message) {
		System.out.println("ViewController!!!");
		System.out.println(message);
	}
}