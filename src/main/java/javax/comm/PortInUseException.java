package javax.comm;

/**
 * <p>Thrown when the specified port is in use.</p>
 */
public class PortInUseException extends Exception {
	private static final long serialVersionUID = -2619871379515236575L;
	
	/**
     * <p>Describes the current owner of the communications port.</p>
     */
    public final String currentOwner;

    /**
     * <p>create a instance of the Exception and store the current owner</p>
     * @param owner detailed information about the current owner
     */
    public PortInUseException(final String owner) {
        super(owner);
        currentOwner = owner;
    }
}

