package javax.comm;

/**
 * <p>Thrown when a driver doesn't allow the specified operation.</p>
 */
public class UnsupportedCommOperationException extends Exception {
	private static final long serialVersionUID = -1653996043267525578L;

	/**
     * <p>create an instances with no message about why the Exception was thrown.</p>
     */
    public UnsupportedCommOperationException() {
        super();
    }

    /**
     * <p>create an instance with a message about why the Exception was thrown.</p>
     * @param message A detailed message explaining the reason for the Exception.
     */
    public UnsupportedCommOperationException(final String message) {
        super(message);
    }
}
