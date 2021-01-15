package javax.comm;

/**
 * <p>Thrown when a driver can't find the specified port.</p>
 */
public class NoSuchPortException extends Exception {
	private static final long serialVersionUID = 8484565213793918147L;

	public NoSuchPortException( String portName ) {
        super( portName );
    }
}

