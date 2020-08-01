package cn.xgg.robbe.exception;

public class RobbeException extends Exception {

	private static final long serialVersionUID = 1L;

	public RobbeException() {
		super();
	}

	public RobbeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RobbeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RobbeException(String message) {
		super(message);
	}

	public RobbeException(Throwable cause) {
		super(cause);
	}
	
	

}
