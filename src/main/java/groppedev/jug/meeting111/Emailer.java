package groppedev.jug.meeting111;

public class Emailer {
	
	private final SpellChecker spellChecker;
	private final Transport transport;

	public Emailer(SpellChecker spellChecker, Transport transport) {
		this.spellChecker = spellChecker;
		this.transport = transport;
	}
	
	public void send(String text) {
		if(spellChecker.check(text)){
			this.transport.send(text);
		}
	}
	
	public static void main(String[] args) {
		Emailer emailer = new Emailer(new ItalianSpellChecker(), new SMTPTransport());
		emailer.send("Testo messaggio");
	}
}
