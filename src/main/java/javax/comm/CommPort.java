package javax.comm;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>A communications port.</p>
 * <p>CommPort is an abstract class that describes a communications port made available by the underlying system.</p>
 *
 * <p>It includes high-level methods for controlling I/O that are common to different kinds of communications ports.
 * {@linkplain SerialPort} and {@linkplain ParallelPort} are subclasses of CommPort that include additional methods for low-level control of
 * physical communications ports.There are no public constructors for CommPort. Instead an application should use
 * the static method {@linkplain CommPortIdentifier#getPortIdentifiers()} to generate a list of available ports. It then chooses
 * a port from this list and calls {@linkplain CommPortIdentifier#open(String, int)} to create a CommPort object. Finally, it casts the
 * CommPort object to a physical communications device class like {@linkplain SerialPort} or {@linkplain ParallelPort}. After a communications
 * port has been identified and opened it can be configured with the methods in the low-level classes like {@linkplain SerialPort}
 * and {@linkplain ParallelPort}. Then an IO stream can be opened for reading and writing data. Once the application is done with
 * the port, it must call the {@linkplain CommPort#close()} method. Thereafter the application must not call any methods in the port object.
 * Doing so will cause a {@linkplain java.lang.IllegalStateException} to be thrown.</p>
 */
public abstract class CommPort implements Closeable {
    protected String name;

    /**
     * <p>Enables receive framing, if this feature is supported by the driver.</p>
     * <p>When the receive framing condition becomes true, a read from the input stream for this port will
     * return immediately.</p>
     * <p>enableReceiveFraming is an advisory method which the driver may not implement. By default, receive
     * framing is not enabled.</p>
     * <p>An application can determine whether the driver supports this feature by first calling the
     * enableReceiveFraming method and then calling the {@linkplain #isReceiveFramingEnabled()} method. If
     * {@linkplain #isReceiveFramingEnabled()} still returns false, then receive framing is not supported by the driver.</p>
     * <p>Note: As implemented in this method, framing is not related to bit-level framing at the hardware level,
     * and is not associated with data errors.</p>
     * @param framingByte - this byte in the input stream suggests the end of the received frame. Blocked reads will return 
     * immediately. Only the low 8 bits of framingByte are used while the upper 24 bits are masked off. A value outside the range of 0-255 
     * will be converted to the value of its lowest 8 bits.
     * @throws UnsupportedCommOperationException is thrown if receive timeout is not supported by the underlying driver.
     */
    public abstract void enableReceiveFraming( int framingByte ) throws UnsupportedCommOperationException;

    /**
     * <p>Disables receive framing.</p>
     */
    public abstract void disableReceiveFraming();

    /**
     * <p>Checks if receive framing is enabled.</p>
     * @return true if the driver supports receive framing.
     */
    public abstract boolean isReceiveFramingEnabled();

    /**
     * <p>Gets the current byte used for receive framing.</p>
     * <p>If the receive framing is disabled or not supported by the driver, then the value returned is meaningless.</p>
     * <p>The return value of getReceiveFramingByte is an integer, the low 8 bits of which represent the current byte
     * used for receive framing.</p>
     * <p>Note: As implemented in this method, framing is not related to bit-level framing at the hardware level, and
     * is not associated with data errors.</p>
     * @return integer current byte used for receive framing
     */
    public abstract int getReceiveFramingByte();

    /**
     * <p>Disables receive timeout.</p>
     */
    public abstract void disableReceiveTimeout();

    /**
     * <p>Enables receive timeout, if this feature is supported by the driver.</p>
     * <p>When the receive timeout condition becomes true, a read from the input stream for this port will
     * return immediately.</p>
     * <p>enableReceiveTimeout is an advisory method which the driver may not implement. By default, receive
     * timeout is not enabled.<p>
     * <p>An application can determine whether the driver supports this feature by first calling the
     * enableReceiveTimeout method and then calling the {@linkplain #isReceiveTimeoutEnabled()} method. If 
     * {@linkplain #isReceiveTimeoutEnabled()} still returns false, then receive timeout is not supported by the driver.</p>
     * @param time - when this many milliseconds have elapsed, return immediately from read, regardless of bytes in input buffer.
     * @throws UnsupportedCommOperationException is thrown if receive timeout is not supported by the underlying driver.
     * @see #getInputStream() for description of exact behaviour.
     */
    public abstract void enableReceiveTimeout( int time ) throws UnsupportedCommOperationException;

    /**
     * <p>Checks if receive timeout is enabled.</p>
     * @return true if the driver supports receive timeout.
     */
    public abstract boolean isReceiveTimeoutEnabled();

    /**
     * <p>Gets the integer value of the receive timeout.</p>
     * <p>If the receive timeout is disabled or not supported by the driver, then the value returned is meaningless.</p>
     * @return number of milliseconds in receive timeout
     */
    public abstract int getReceiveTimeout();

    /**
     * <p>Enables receive threshold, if this feature is supported by the driver.</p>
     * <p>When the receive threshold condition becomes true, a read from the input stream for this port will
     * return immediately.</p>
     * <p>enableReceiveThreshold is an advisory method which the driver may not implement.
     * By default, receive threshold is not enabled.</p>
     * <p>An application can determine whether the driver supports this feature by first calling the
     * enableReceiveThreshold method and then calling the {@linkplain #isReceiveThresholdEnabled()} method.
     * If {@linkplain #isReceiveThresholdEnabled()} still returns false, then receive threshold is not supported by the driver.</p>
     * <p>If the driver does not implement this feature, it will return from blocking reads at an appropriate time.</p>
     * @param thresh - when this many bytes are in the input buffer, return immediately from read.
     * @throws UnsupportedCommOperationException is thrown if receive threshold is not supported by the underlying driver.
     * @see #getInputStream() for description of exact behaviour.
     */
    public abstract void enableReceiveThreshold( int thresh ) throws UnsupportedCommOperationException;

    /**
     * <p>Disables receive threshold.</p>
     */
    public abstract void disableReceiveThreshold();

    /**
     * <p>Gets the integer value of the receive threshold.</p>
     * <p>If the receive threshold is disabled or not supported by the driver, then the value returned is meaningless.</p>
     * @return number of bytes for receive threshold
     */
    public abstract int getReceiveThreshold();

    /**
     * <p>Checks if receive threshold is enabled.</p>
     * @return true if the driver supports receive threshold.
     */
    public abstract boolean isReceiveThresholdEnabled();

    /**
     * <p>Sets the input buffer size.</p>
     * <p>Note that this is advisory and memory availability may determine the ultimate buffer size used by the driver.</p>
     * @param size - size of the input buffer
     */
    public abstract void setInputBufferSize( int size );

    /**
     * <p>Gets the input buffer size. Note that this method is advisory and the underlying OS may choose not to
     * report correct values for the buffer size.<p>
     * @return input buffer size currently in use
     */
    public abstract int getInputBufferSize();

    /**
     * <p>Sets the output buffer size.</p>
     * <p>Note that this is advisory and memory availability may determine the ultimate buffer size used by the driver.</p>
     * @param size - size of the output buffer
     */
    public abstract void setOutputBufferSize( int size );

    /**
     * <p>Gets the output buffer size.</p>
     * <p>Note that this method is advisory and the underlying OS may choose not to report correct values for
     * the buffer size.</p>
     * @return output buffer size currently in use
     */
    public abstract int getOutputBufferSize();

    /**
     * <p>Closes the communications port<p>.
     */
    public abstract void close();

    /**
     * <p>Returns an input stream.</p>
     *
     * <p>This is the only way to receive data from the communications port.</p>
     * <p>If the port is unidirectional and doesn't support receiving data, then getInputStream returns null.</p>
     * <p>The read behaviour of the input stream returned by getInputStream depends on combination of the
     * threshold and timeout values. The possible behaviours are described in the table below:<p>
     * <table border="1">
     * <tr><th colspan="2">Threshold</th><th colspan="2">Timeout</th><th rowspan="2">Read Buffer Size</th><th rowspan="2">Read Behaviour</th></tr>
     * <tr><th>State</th><th>Value</th><th>State</th><th>Value</th></tr>
     * <tr><td>disabled</td><td>-</td><td>disabled</td><td>-</td><td>n bytes</td><td>block until any data is available</td></tr>
     * <tr><td>enabled</td><td> m bytes</td><td> disabled</td><td> -</td><td> n bytes</td><td> block until min(m,n) bytes are available</td></tr>
     * <tr><td>disabled</td><td> -</td><td> enabled</td><td> x ms</td><td> n bytes</td><td> block for x ms or until any data is available</td></tr>
     * <tr><td>enabled</td><td> m bytes</td><td> enabled</td><td> x ms</td><td> n bytes</td><td> block for x ms or until min(m,n) bytes are available</td></tr>
     * </table>
     * <p>Note, however, that framing errors may cause the Timeout and Threshold values to complete prematurely
     * without raising an exception.<p>
     *
     * <p>Enabling the Timeout OR Threshold with a value a zero is a special case. This causes the underlying
     * driver to poll for incoming data instead being event driven. Otherwise, the behaviour is identical to
     * having both the Timeout and Threshold disabled.</p>
     *
     * @return InputStream object that can be used to read from the port
     * @throws java.io.IOException - if an I/O error occurred
     */
    public abstract InputStream getInputStream() throws IOException;

    /**
     * <p>Returns an output stream.</p>
     * <p>This is the only way to send data to the communications port.</p>
     * <p>If the port is unidirectional and doesn't support sending data, then getOutputStream returns null.</p>
     * @return OutputStream object that can be used to write to the port
     * @throws java.io.IOException - if an I/O error occurred
     */
    public abstract OutputStream getOutputStream() throws IOException;

    /**
     * <p>Gets the name of the communications port.</p>
     * <p>This name should correspond to something the user can identify, like the label on the hardware.</p>
     * @return name of the communication port. Can't be null or empty
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Returns a String representation of this communications port.</p>
     * @return String representation of the port
     */
    public String toString() {
        return name;
    }
}
