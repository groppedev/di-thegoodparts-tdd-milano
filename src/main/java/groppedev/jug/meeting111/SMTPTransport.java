package groppedev.jug.meeting111;

public class SMTPTransport implements Transport {

	@Override
	public void send(String message) {
		System.out.println("Sending message -> " + message);
	}
}
