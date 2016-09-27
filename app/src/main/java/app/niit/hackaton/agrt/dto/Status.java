package app.niit.hackaton.agrt.dto;

/**
 * Created by ChandraSekharPonugot on 27-09-2016.
 */

public enum Status {
    IN(0),
    OUT(1);

    /** The status code. */
    private int statusCode;
    /**
     * Instantiates a new status code.
     *
     * @param statusCode
     *            the status code
     */
    Status(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Returns the value of the StatusCode.
     *
     * @return
     */
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}