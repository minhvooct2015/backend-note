package axonactive.trainning.exception;

import javax.ws.rs.core.Response.Status;

public class NoteException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Status code of response
     */
    private final Status status;

    /**
     * Default constructor.
     */
    public NoteException() {
        super();
        this.status = Status.INTERNAL_SERVER_ERROR;
    }

    /**
     * Constructor.
     *
     * @param msg The message.
     */
    public NoteException(String msg) {
        super(msg);
        this.status = Status.INTERNAL_SERVER_ERROR;
    }

    /**
     * Constructor.
     *
     * @param status the status of response
     * @param msg    The message.
     */
    public NoteException(Status status, String msg) {
        super(msg);
        this.status = status;
    }

    /**
     * Constructor.
     *
     * @param msg   The message.
     * @param cause the cause
     */
    public NoteException(String msg, Throwable cause) {
        super(msg, cause);
        this.status = Status.INTERNAL_SERVER_ERROR;
    }

    /**
     * Constructor.
     *
     * @param status the status of response
     * @param msg    The message
     * @param cause  the cause
     */
    public NoteException(Status status, String msg, Throwable cause) {
        super(msg, cause);
        this.status = status;
    }

    /**
     * Constructor.
     *
     * @param cause the cause
     */
    public NoteException(Throwable cause) {
        super(cause);
        this.status = Status.INTERNAL_SERVER_ERROR;
    }

    /**
     * get status
     *
     * @return reponse status
     */
    public Status getStatus() {
        return this.status;
    }
}
