package cz.muni.fi.pa165.service.exception;

import org.springframework.dao.DataAccessException;

/**
 * Class which is a subclass of DataAccessException
 */
public class HauntedHouseException extends DataAccessException {
    public HauntedHouseException(String msg) {
        super(msg);
    }

    public HauntedHouseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
