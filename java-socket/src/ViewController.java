public class ViewController implements MateSocketObserver {
	public void notify(String message) {
		System.out.println(message);
	}
}