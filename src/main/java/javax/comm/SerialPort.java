package javax.comm;

import java.util.TooManyListenersException;

/**
 * <p>An RS-232 serial communications port.</p>
 *
 * <p>SerialPort describes the low-level interface to a serial communications port made available by the
 * underlying system. SerialPort defines the minimum required functionality for serial communications ports.</p>
 */
public abstract class SerialPort extends CommPort {
	/**
	 * <p>Data bits: 5 bit</p>
	 */
    public static final int DATABITS_5 = 5;
	/**
	 * <p>Data bits: 6 bit</p>
	 */
    public static final int DATABITS_6 = 6;
	/**
	 * <p>Data bits: 7 bit</p>
	 */
    public static final int DATABITS_7 = 7;
	/**
	 * <p>Data bits: 8 bit</p>
	 */
    public static final int DATABITS_8 = 8;
	/**
	 * <p>Parity: none</p>
	 */
    public static final int PARITY_NONE = 0;
	/**
	 * <p>Parity: odd</p>
	 */
    public static final int PARITY_ODD = 1;
	/**
	 * <p>Parity: event</p>
	 */
    public static final int PARITY_EVEN = 2;
	/**
	 * <p>Parity: mark</p>
	 */
    public static final int PARITY_MARK = 3;
	/**
	 * <p>Parity: space</p>
	 */
    public static final int PARITY_SPACE = 4;
	/**
	 * <p>Stop bits: 1</p>
	 */
    public static final int STOPBITS_1 = 1;
	/**
	 * <p>Stop bits: 2</p>
	 */
    public static final int STOPBITS_2 = 2;
	/**
	 * <p>Stop bits: 1.5</p>
	 */
    public static final int STOPBITS_1_5 = 3;
	/**
	 * <p>Flow control: none</p>
	 */
    public static final int FLOWCONTROL_NONE = 0;
	/**
	 * <p>Flow control: RTS/CTS in</p>
	 */
    public static final int FLOWCONTROL_RTSCTS_IN = 1;
	/**
	 * <p>Flow control: RTS/CTS out</p>
	 */
    public static final int FLOWCONTROL_RTSCTS_OUT = 2;
	/**
	 * <p>Flow control: XOn/XOff in</p>
	 */
    public static final int FLOWCONTROL_XONXOFF_IN = 4;
	/**
	 * <p>Flow control: XOn/XOff out</p>
	 */
    public static final int FLOWCONTROL_XONXOFF_OUT = 8;

    /**
     * <p>Sets serial port parameters.</p>
     *
     * <p>DEFAULTS: 9600 baud, 8 data bits, 1 stop bit, no parity</p>
     *
     * @param bitrate  If the baudrate passed in by the application is unsupported by the driver, the driver will
     *                 throw an UnsupportedCommOperationException
     * @param datasize <ul>
     *                 <li>DATABITS_5: 5 bits</li>
     *                 <li>DATABITS_6: 6 bits</li>
     *                 <li>DATABITS_7: 7 bits</li>
     *                 <li>DATABITS_8: 8 bits</li>
     *                 </ul>
     * @param stopbits <ul>
     *                 <li>STOPBITS_1: 1 stop bit</li>
     *                 <li>STOPBITS_2: 2 stop bits</li>
     *                 <li>STOPBITS_1_5: 1.5 stop bits</li>
     *                 </ul>
     * @param parity   <ul>
     *                 <li>PARITY_NONE: no parity</li>
     *                 <li>PARITY_ODD: odd parity</li>
     *                 <li>PARITY_EVEN: even parity</li>
     *                 <li>PARITY_MARK: mark parity</li>
     *                 <li>PARITY_SPACE: space parity</li>
     *                 </ul>
     *
     * @throws UnsupportedCommOperationException if any of the above parameters are specified incorrectly. All four of the parameters will revert to the values before the call was made.
     */
    public abstract void setSerialPortParams( int bitrate, int datasize, int stopbits, int parity ) throws UnsupportedCommOperationException;

    /**
     * <p>Gets the currently configured baud rate.</p>
     * @return integer value indicating the baud rate
     */
    public abstract int getBaudRate();

    /**
     * <p>Gets the currently configured number of data bits.</p>
     * @return integer that can be equal to DATABITS_5, DATABITS_6, DATABITS_7, or DATABITS_8
     */
    public abstract int getDataBits();

    /**
     * <p>Gets the currently defined stop bits.</p>
     * @return integer that can be equal to STOPBITS_1, STOPBITS_2, or STOPBITS_1_5
     */
    public abstract int getStopBits();

    /**
     * <p>Get the currently configured parity setting.</p>
     * @return integer that can be equal to PARITY_NONE, PARITY_ODD, PARITY_EVEN, PARITY_MARK or PARITY_SPACE.
     */
    public abstract int getParity();

    /**
     * <p>Sets the flow control mode.</p>
     * @param flowcontrol Can be a bitmask combination of
     *                    <ul>
     *                    <li>FLOWCONTROL_NONE: no flow control</li>
     *                    <li>FLOWCONTROL_RTSCTS_IN: RTS/CTS (hardware) flow control for input</li>
     *                    <li>FLOWCONTROL_RTSCTS_OUT:	RTS/CTS (hardware) flow control for output</li>
     *                    <li>FLOWCONTROL_XONXOFF_IN:	XON/XOFF (software) flow control for input</li>
     *                    <li>FLOWCONTROL_XONXOFF_OUT:	XON/XOFF (software) flow control for output</li>
     *                    </ul>
     *
     * @throws UnsupportedCommOperationException
     *          if any of the flow control mode was not supported by the  underline OS, or if input and output flow
     *          control are set to different values, i.e. one hardware and one software. The flow control mode will
     *          revert to the value before the call was made.
     */
    public abstract void setFlowControlMode( int flowcontrol ) throws UnsupportedCommOperationException;

    /**
     * <p>Gets the currently configured flow control mode.</p>
     * @return an integer bitmask of the modes FLOWCONTROL_NONE, FLOWCONTROL_RTSCTS_IN, FLOWCONTROL_RTSCTS_OUT,
     *         FLOWCONTROL_XONXOFF_IN, and FLOWCONTROL_XONXOFF_OUT.
     */
    public abstract int getFlowControlMode();

    /**
     * <p>Sets or clears the DTR (Data Terminal Ready) bit in the UART, if supported by the underlying implementation.</p>
     * @param state <ul>
     *              <li>true: set DTR</li>
     *              <li>false: clear DTR</li>
     *              </ul>
     */
    public abstract void setDTR( boolean state );

    /**
     * <p>Sets or clears the RTS (Request To Send) bit in the UART, if supported by the underlying implementation.</p>
     * @param state <ul>
     *              <li>true: set RTS</li>
     *              <li>false: clear RTS</li>
     *              </ul>
     */
    public abstract void setRTS( boolean state );

    /**
     * <p>Gets the state of the RTS (Request To Send) bit in the UART, if supported by the underlying implementation.</p>
     * @return true if RTS is currently active. false otherwise.
     */
    public abstract boolean isRTS();

    /**
     * <p>Gets the state of the CTS (Clear To Send) bit in the UART, if supported by the underlying implementation.</p>
     * @return true if CTS is currently active. false otherwise.
     */
    public abstract boolean isCTS();

    /**
     * <p>Gets the state of the DTR (Data Terminal Ready) bit in the UART, if supported by the underlying implementation.</p>
     * @return true if DTR is currently active. false otherwise.
     */
    public abstract boolean isDTR();

    /**
     * <p>Gets the state of the DSR (Data Set Ready) bit in the UART, if supported by the underlying implementation.</p>
     * @return true if DSR is currently active. false otherwise.
     */
    public abstract boolean isDSR();

    /**
     * <p>Gets the state of the CD (Carrier Detect) bit in the UART, if supported by the underlying implementation.</p>
     * @return true if DCD is currently active. false otherwise.
     */
    public abstract boolean isCD();

    /**
     * <p>Gets the state of the RI (Ring Indicator) bit in the UART, if supported by the underlying implementation.</p>
     * @return true if RI is currently active. false otherwise.
     */
    public abstract boolean isRI();

    /**
     * <p>Sends a break of <b>millis</b> milliseconds duration.</p>
     * <p>Note that it may not be possible to time the duration of the break under certain Operating Systems.
     * Hence this parameter is advisory.</p>
     * @param duration duration of break to send
     */
    public abstract void sendBreak( int duration );

    /**
     * <p>Registers a {@linkplain SerialPortEventListener} object to listen for {@linkplain SerialPortEvent}.</p>
     *
     * <p>Interest in specific events may be expressed using the notifyOnXXX calls. The {@linkplain SerialPortEventListener#serialEvent(SerialPortEvent)} method
     * will be called with a {@linkplain SerialPortEvent} object describing the event.
     * The current implementation only allows one listener per {@linkplain SerialPort}. Once a listener is registered,
     * subsequent call attempts to addEventListener will throw a {@linkplain java.util.TooManyListenersException} without effecting
     * the listener already registered.</p>
     * </p>All the events received by this listener are generated by one dedicated thread that belongs to the
     * {@linkplain SerialPort} object. After the port is closed, no more event will be generated. Another call to {@linkplain CommPortIdentifier#open(String, int)}
     * will return a new CommPort object, and the listener has to be added again to the new {@linkplain CommPort} object to receive event from this port.</p>
     * @param listener The {@linkplain SerialPortEventListener} object whose {@linkplain SerialPortEventListener#serialEvent(SerialPortEvent)} method will be called with a {@linkplain SerialPortEvent} describing the event.
     * @throws java.util.TooManyListenersException If an initial attempt to attach a listener succeeds, subsequent attempts will throw {@linkplain java.util.TooManyListenersException} without effecting the first listener.
     */
    public abstract void addEventListener( SerialPortEventListener listener )
        throws TooManyListenersException;

    /**
     * <p>Deregisters event listener registered using {@linkplain #addEventListener(SerialPortEventListener)}</p>
     * <p>This is done automatically at port close.</p>
     */
    public abstract void removeEventListener();

    /**
     * <p>Expresses interest in receiving notification when input data is available.</p>
     *
     * <p>This may be used to drive asynchronous input. When data is available in the input buffer, this
     * event is propagated to the listener registered using {@linkplain #addEventListener(SerialPortEventListener)}.
     * The event will be generated once when new data arrive at the serial port. Even if the user doesn't
     * read the data, it won't be generated again until next time new data arrive.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnDataAvailable( boolean enable );

    /**
     * <p>Expresses interest in receiving notification when the output buffer is empty.</p>
     *
     * <p>This may be used to drive asynchronous output. When the output buffer becomes empty, this event is
     * propagated to the listener registered using {@linkplain #addEventListener(SerialPortEventListener)}. The event will be generated after a
     * write is completed, when the system buffer becomes empty again.</p>
     * <p>This notification is hardware dependent and may not be supported by all implementations.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnOutputEmpty( boolean enable );

    /**
     * <p>Expresses interest in receiving notification when the CTS (Clear To Send) bit changes.</p>
     * <p>This notification is hardware dependent and may not be supported by all implementations.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnCTS( boolean enable );

    /**
     * <p>Expresses interest in receiving notification when the DSR (Data Set Ready) bit changes.</p>
     * <p>This notification is hardware dependent and may not be supported by all implementations.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnDSR( boolean enable );

    /**
     * <p>Expresses interest in receiving notification when the RI (Ring Indicator) bit changes.</p>
     * <p>This notification is hardware dependent and may not be supported by all implementations.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnRingIndicator( boolean enable );

    /**
     * <p>Expresses interest in receiving notification when the CD (Carrier Detect) bit changes.</p>
     * <p>This notification is hardware dependent and may not be supported by all implementations.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnCarrierDetect( boolean enable );

    /**
     * <p>Expresses interest in receiving notification when there is an overrun error.</p>
     * <p>This notification is hardware dependent and may not be supported by all implementations.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnOverrunError( boolean enable );

    /**
     * <p>Expresses interest in receiving notification when there is a parity error.</p>
     * <p>This notification is hardware dependent and may not be supported by all implementations.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnParityError( boolean enable );

    /**
     * <p>Expresses interest in receiving notification when there is a framing error.</p>
     * <p>This notification is hardware dependent and may not be supported by all implementations.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnFramingError( boolean enable );

    /**
     * <p>Expresses interest in receiving notification when there is a break interrupt on the line.</p>
     * <p>This notification is hardware dependent and may not be supported by all implementations.</p>
     * @param enable <ul>
     *               <li>true: enable notification</li>
     *               <li>false: disable notification</li>
     *               </ul>
     */
    public abstract void notifyOnBreakInterrupt( boolean enable );
}
