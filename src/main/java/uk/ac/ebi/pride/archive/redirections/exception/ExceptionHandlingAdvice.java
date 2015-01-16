package uk.ac.ebi.pride.archive.redirections.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.ac.ebi.pride.web.util.exception.RestError;

/**
 * @author florian@ebi.ac.uk
 */
@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(BiomartException.class)
    private
    @ResponseBody
    RestError handleBioMartException(BiomartException bx) {
        return new RestError(
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                "Please use the PRIDE Archive web service or contact the PRIDE support team.",
                "http://www.ebi.ac.uk/pride/ws/archive",
                bx.getMessage(),
                null);
    }
}
