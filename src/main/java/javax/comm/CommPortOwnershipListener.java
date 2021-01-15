package javax.comm;

import java.util.EventListener;

/**
 * <p>Propagates various communications port ownership events.<p>
 *
 * <p>When a port is opened, a {@linkplain CommPortOwnership} event of type PORT_OWNED will be propagated. When a port is closed,
 * a {@linkplain CommPortOwnership} event of type PORT_UNOWNED will be propagated.</p>
 *
 * <p>Multiple applications that are seeking ownership of a communications port can resolve their differences as follows:</p>
 * <ul>
 * <li>ABCapp calls open and takes ownership of port.</li>
 * <li>XYZapp calls open sometime later.</li>
 * <li>While processing XYZapp's open, CommPortIdentifier will propagate a CommPortOwnership event with
 * the event type PORT_OWNERSHIP_REQUESTED.</li>
 * <li>If ABCapp is registered to listen to these events and if it is willing to give up ownership, it calls
 * close from within the event callback.</li>
 * <li>After the event has been fired, CommPortIdentifier checks to see if ownership was given up, and if so,
 * turns over ownership of the port to XYZapp by returning success from open.</li>
 * </ul>
 *
 * <p>Note: When a close is called from within a CommPortOwnership event callback, a new CommPortOwnership event will
 * not be generated.<p>
 */
public interface CommPortOwnershipListener extends EventListener {
    /**
     * <p>The port just went from unowned to owned state, when an application successfully called {@linkplain CommPortIdentifier#open(String, int)}</p>
     */
    int PORT_OWNED = 1;

    /**
     * <p>The port just went from owned to unowned state, when the port's owner called {@linkplain CommPort#close()}</p>
     */
    int PORT_UNOWNED = 2;

    /**
     * <p>Ownership contention. The port is owned by one application and another application wants ownership.
     * If the owner of this port is listening to this event, it can call {@linkplain CommPort#close()} during the processing
     * of this event and thereby give up ownership of the port.</p>
     */
    int PORT_OWNERSHIP_REQUESTED = 3;

    /**
     * <p>Propagates a CommPortOwnership event.
     * This method will be called with the type set to one of the
     * variables PORT_OWNED, PORT_UNOWNED, or PORT_OWNERSHIP_REQUESTED.</p>
     */
    void ownershipChange(int type);
}
