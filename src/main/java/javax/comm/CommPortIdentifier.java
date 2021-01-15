package javax.comm;

import java.io.FileDescriptor;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Communications port management.</p>
 *
 * <p>CommPortIdentifier is the central class for controlling access to communications ports. It includes methods for:</p>
 * <ol>
 * <li>Determining the communications ports made available by the driver.</li>
 * <li>Opening communications ports for I/O operations.</li>
 * <li>Determining port ownership.</li>
 * <li>Resolving port ownership contention.</li>
 * <li>Managing events that indicate changes in port ownership status.</li>
 * </ol>
 *
 * <p>An application first uses methods in CommPortIdentifier to negotiate with the driver to discover which
 * communication ports are available and then select a port for opening. It then uses methods in other
 * classes like {@linkplain CommPort}, {@linkplain ParallelPort} and {@linkplain SerialPort} to communicate through the port.</p>
 *
 * @see CommPort, CommPortOwnershipListener, ParallelPort, SerialPort
 */
public class CommPortIdentifier {
	/**
	 * <p>Serial port type descriptor</p>
	 */
    public static final int PORT_SERIAL = 1;
    
	/**
	 * <p>Parallel port type descriptor</p>
	 */
    public static final int PORT_PARALLEL = 2;

    private static ConcurrentHashMap<String,CommPortIdentifier> ports = new ConcurrentHashMap<String, CommPortIdentifier>();

    private String name;
    private final int portType;
    private final CommDriver driver;

    static {
    	for (CommDriver drv : ServiceLoader.load(CommDriver.class)) {
    		drv.initialize();
    	}
    }

    /**
     * <p>COnstrictyor of the class</p>
     * @param portName port name. Can't be null or empty
     * @param portType port type (see {@linkplain #PORT_SERIAL} and {@linkplain #PORT_PARALLEL})
     * @param driver communication driver. Can't be null
     */
    public CommPortIdentifier( final String portName, final int portType, final CommDriver driver ) {
        this.name = portName;
        this.portType = portType;
        this.driver = driver;
    }

    /**
     * <p>Adds portName to the list of ports.</p>
     * @param portName the name of the port being added. Can't be null orempty
     * @param portType the type of the port being added (see {@linkplain #PORT_SERIAL} and {@linkplain #PORT_PARALLEL})
     * @param driver   the driver representing the port being added. Can't be null
     */
    public static void addPortName( final String portName, final int portType, final CommDriver driver ) {
        CommPortIdentifier cpi = new CommPortIdentifier(portName, portType, driver);
        ports.put(portName, cpi);
    }

    /**
     * <p>Returns the name of the port.</p>
     * @return the name of the port. Can't be null or empty
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Obtains a CommPortIdentifier object by using a port name.</p>
     * <p>The port name may have been stored in persistent storage by the application.</p>
     * @param portName - name of the port to open. Can't be null or empty
     * @return CommPortIdentifier object. Can't be null
     * @throws NoSuchPortException if the port does not exist
     */
    public static CommPortIdentifier getPortIdentifier( String portName ) throws NoSuchPortException {
        return ports.get( portName );
    }

    /**
     * <p>Obtains the CommPortIdentifier object corresponding to a port that has already been opened by the application.</p>
     *
     * @param port - a CommPort object obtained from a previous open. Can't be null
     * @return CommPortIdentifier object. Can't be null
     * @throws NoSuchPortException if the port object is invalid
     */
    public static CommPortIdentifier getPortIdentifier( CommPort port ) throws NoSuchPortException {
        return ports.get( port.getName() );
    }

    /**
     * <p>Obtains an enumeration object that contains a CommPortIdentifier object for each port in the system.</p>
     * @return Enumeration that can be used to enumerate all the ports known to the system. Can't be null
     */
    public static Enumeration<CommPortIdentifier> getPortIdentifiers() {
        final Iterator<CommPortIdentifier> it = ports.values().iterator();
        
        return new Enumeration<CommPortIdentifier>() {
			public boolean hasMoreElements() {
				return it.hasNext();
			}

			public CommPortIdentifier nextElement() {
				return it.next();
			}
        };
    }

    /**
     * <p>Returns the port type.</p>
     * @return portType - {@linkplain #PORT_SERIAL} or {@linkplain #PORT_PARALLEL}
     */
    public int getPortType() {
        return portType;
    }

    /**
     * <p>Registers an interested application so that it can receive notification of changes in port ownership.</p>
     * <p>This includes notification of the following events:</p>
     * <ol>
     * <li>{@linkplain CommPortOwnershipListener#PORT_OWNED}: Port became owned</li>
     * <li>{@linkplain CommPortOwnershipListener#PORT_UNOWNED}: Port became unowned</li>
     * <li>{@linkplain CommPortOwnershipListener#PORT_OWNERSHIP_REQUESTED} If the application owns this port and is willing to give up ownership,
     * then it should call close now.</li>
     * </ol>
     * <p>The ownershipChange method of the listener registered using addPortOwnershipListener will be called with one
     * of the above events.</p>
     * @param listener CommPortOwnershipListener callback object. Can't be null
     */
    public void addPortOwnershipListener( CommPortOwnershipListener listener ) {
    }

    /**
     * <p>Deregisters a {@linkplain CommPortOwnershipListener} registered using {@linkplain #addPortOwnershipListener(CommPortOwnershipListener)}</p>
     * @param listener The {@linkplain CommPortOwnershipListener} object that was previously registered using {@linkplain #addPortOwnershipListener(CommPortOwnershipListener)}. Can't be null
     */
    public void removePortOwnershipListener( CommPortOwnershipListener listener ) {
    }

    /**
     * <p>Returns the owner of the port.</p>
     * @return current owner of the port. Can't be null or empty
     */
    public String getCurrentOwner() {
        return "<unknown>";
    }

    /**
     * <p>Checks whether the port is owned.</p>
     * @return true if port is owned by an application, false if port is not owned.
     */
    public synchronized boolean isCurrentlyOwned() {
        return false;
    }

    /**
     * <p>Opens the communications port using a FileDescriptor object on platforms that support this technique.</p>
     * @param fd - The FileDescriptor associated with this CommPort. Can't be null
     * @return CommPort object. Can't be null
     * @throws UnsupportedCommOperationException on platforms which do not support this functionality.
     */
    public synchronized CommPort open( FileDescriptor fd ) throws UnsupportedCommOperationException {
        throw new UnsupportedCommOperationException();
    }

    /**
     * <p>Opens the communications port.</p>
     *
     * <p>open obtains exclusive ownership of the port.</p>
     * <p>If the port is owned by some other application, a {@linkplain CommPortOwnershipListener#PORT_OWNERSHIP_REQUESTED} event is propagated using
     * the {@linkplain CommPortOwnershipListener} event mechanism. If the application that owns the port calls {@linkplain CommPort#close()} during
     * the event processing, then this open will succeed. There is one {@linkplain java.io.InputStream} and one {@linkplain java.io.OutputStream} associated
     * with each port. After a port is opened with open, then all calls to {@linkplain CommPort#getInputStream()} will return the same
     * stream object until close is called.</p>
     *
     * @param appname Name of application making this call. This name will become the owner of the port. Useful when resolving ownership contention. Can't be null or empty
     * @param timeout Time in milliseconds to block waiting for port open.
     * @return CommPort object. Can't be null
     * @throws PortInUseException if the port is in use by some other application that is not willing to relinquish ownership
     */
    public CommPort open( String appname, int timeout ) throws PortInUseException {
        CommPort port = driver.getCommPort( name, portType );
        return port;
    }
}

