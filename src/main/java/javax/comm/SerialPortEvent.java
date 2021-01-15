package javax.comm;

import java.util.EventObject;

public class SerialPortEvent extends EventObject {
	private static final long serialVersionUID = 5397097008339912380L;

	/**
     * <p>Data available at the serial port.</p>
     * <p>This event will be generated once when new data arrive at the serial port. Even if the user doesn't
     * read the data, it won't be generated again until next time new data arrive.</p>
     */
    public static final int DATA_AVAILABLE = 1;

    /**
     * <p>Output buffer is empty.</p>
     * <p>The event will be generated after a write is completed, when the system buffer becomes empty again.</p>
     */
    public static final int OUTPUT_BUFFER_EMPTY = 2;

    /**
     * Clear to send.
     */
    public static final int CTS = 3;

    /**
     * Data set ready.
     */
    public static final int DSR = 4;

    /**
     * Ring indicator.
     */
    public static final int RI = 5;

    /**
     * Carrier detect.
     */
    public static final int CD = 6;

    /**
     * Overrun Error
     */
    public static final int OE = 7;

    /**
     * Parity Error
     */
    public static final int PE = 8;

    /**
     * Framing Error
     */
    public static final int FE = 9;

    /**
     * Break Interrupt
     */
    public static final int BI = 10;

    private boolean oldValue;
    private boolean newValue;
    private int eventType;

    /**
     * <p>Constructs a SerialPortEvent with the specified serial port, event type, old and new values. Application programs should not directly create SerialPortEvent objects.</p>
     * @param srcport   source port. Can't be null
     * @param eventtype event type (see {@linkplain #BI}, {@linkplain #CD}, {@linkplain #CTS}, {@linkplain #DATA_AVAILABLE},
     *         {@linkplain #DSR}, {@linkplain #FE}, {@linkplain #OE}, {@linkplain #OUTPUT_BUFFER_EMPTY},
     *         {@linkplain #PE} or {@linkplain #RI})
     * @param oldvalue  old value
     * @param newvalue  new value
     */
    public SerialPortEvent( SerialPort srcport, int eventtype, boolean oldvalue, boolean newvalue ) {
        super( srcport );
        oldValue = oldvalue;
        newValue = newvalue;
        eventType = eventtype;
    }

    /**
     * <p>Gets the type of this event.</p>
     * @return integer that can be equal to one of the following static variables:
     *         {@linkplain #BI}, {@linkplain #CD}, {@linkplain #CTS}, {@linkplain #DATA_AVAILABLE},
     *         {@linkplain #DSR}, {@linkplain #FE}, {@linkplain #OE}, {@linkplain #OUTPUT_BUFFER_EMPTY},
     *         {@linkplain #PE} or {@linkplain #RI}.
     */
    public int getEventType() {
        return (eventType);
    }

    /**
     * <p>Gets the new value of the state change that caused the SerialPortEvent to be propagated.</p>
     * <p>For example, when the CD bit changes, newValue reflects the new value of the CD bit.</p>
     */
    public boolean getNewValue() {
        return newValue;
    }

    /**
     * <p>Gets the old value of the state change that caused the SerialPortEvent to be propagated.</p>
     * <p>For example, when the CD bit changes, oldValue reflects the old value of the CD bit.</p>
     */
    public boolean getOldValue() {
        return oldValue;
    }
}
