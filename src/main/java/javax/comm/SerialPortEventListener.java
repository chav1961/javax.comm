package javax.comm;

import java.util.EventListener;

/**
 * <p>Listener callback interface for serial port events.</p>
 */
public interface SerialPortEventListener extends EventListener {
    /**
     * @param event the event that has occurred. Can't be null
     */
    void serialEvent( SerialPortEvent event );
}
