package uk.ac.ebi.pride.archive.redirections.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author Jose A. Dianes
 * @version $Id$
 */
public class AssayNotFoundException extends NestedRuntimeException {

    public AssayNotFoundException(String message) {
        super(message);
    }

    public AssayNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}