package javax.comm;

/**
 * <p>Part of the loadable device driver interface. CommDriver should not be used by application-level programs.</p>
 */
public interface CommDriver {
    /**
     * <p>This method will be called by {@linkplain CommPortIdentifier#open(String, int)}, portName is a string that was registered earlier
     * using the {@linkplain CommPortIdentifier#addPortName(String, int, CommDriver)} method. This method returns an object that extends either
     * {@linkplain SerialPort} or {@linkplain ParallelPort}.</p>
     * @param portName port name to get. Can't be null or empty
     * @param portType port type to get (can be {@linkplain CommPortIdentifier#PORT_PARALLEL} and {@linkplain CommPortIdentifier#PORT_SERIAL}}
     * @return communication port
     */
    CommPort getCommPort( String portName, int portType );

    /**
     * <p>This method will be called by the CommPortIdentifier's static initializer.</p>
     * </p>The responsibility of this method is:</p>
     * <ol>
     * <li>Ensure that the hardware required is present</li>
     * <li>Load any required native libraries</li>
     * <li>Register the port names with the {@linkplain CommPortIdentifier}.</li>
     * </ol>
     */
    void initialize();
}

