package uk.ac.ebi.pride.archive.redirections.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jose A. Dianes
 * @version $Id$
 */
@Controller
public class PrideArchiveRedirectionsController {

    private static Logger accessLog = LoggerFactory.getLogger("uk.ac.ebi.pride.archive.redirections.AccessLogger");
    public final static String PRIDE_ARCHIVE_URL = "/archive";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView prideToPrideArchive() {
        accessLog.info("FROM: / TO: " + PRIDE_ARCHIVE_URL);
        return new ModelAndView("redirect:" + PRIDE_ARCHIVE_URL);
    }

    @RequestMapping(value = "/startRegistration.do", method = RequestMethod.GET)
    public ModelAndView prideStartRegistrationToPrideArchiveRegister() {
        accessLog.info("FROM: /startRegistration.do TO: " + PRIDE_ARCHIVE_URL + "/register");
        return new ModelAndView("redirect:" + PRIDE_ARCHIVE_URL + "/register");
    }



}
