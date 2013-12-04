package beegstake.audio.exception;

public class AllChannelsReservedException extends RuntimeException {
	protected String message;
	public AllChannelsReservedException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage(){
		return this.message;
	}

	private static final long serialVersionUID = 7588245310416847371L;
}
