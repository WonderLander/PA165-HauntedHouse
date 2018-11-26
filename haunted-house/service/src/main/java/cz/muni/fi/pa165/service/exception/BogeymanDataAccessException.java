package cz.muni.fi.pa165.service.exception;

import org.springframework.dao.DataAccessException;

/**
 * @author Ondrej Krcma 451363
 */
public class BogeymanDataAccessException extends DataAccessException {

    public BogeymanDataAccessException(String msg) {
        super(msg);
    }

    public BogeymanDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BogeymanDataAccessException(Throwable cause) {
        super("Caused by DAO exception.", cause);
    }
}
