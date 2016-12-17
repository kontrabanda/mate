public class SendController {
	private MateSocket socket;

	public SendController(MateSocket socket) {
		this.socket = socket;
	}

	public void sendMessage(String message) {
		socket.sendMessage(message);
	}
};