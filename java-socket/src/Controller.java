public class Controller {
	private SendController sendController;
	private ViewController viewController;

	public Controller() {
		MateSocket socket = new MateSocket("127.0.0.1", 1234);

		sendController = new SendController(socket);
		viewController = new ViewController();

		socket.registerObserver(viewController);
	}

	public void send(String msg) {
		sendController.sendMessage(msg);
	}

	public static void main(String[] args) {
		Controller controller = new Controller();

		controller.send("TESTTESTTESTTESTTEST");
		int i = 0;

		while(true){
			if(i == 1000000 || i == 2000000 || i == 3000000) {
				String message = "test" + i; 

				System.out.println("Sending message: " + message);
				controller.send(message);
			}

			++i;
		}
	}
};