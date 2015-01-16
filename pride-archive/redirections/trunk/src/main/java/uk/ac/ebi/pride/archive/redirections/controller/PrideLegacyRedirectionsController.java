package uk.ac.ebi.pride.archive.redirections.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.ebi.pride.archive.redirections.exception.BiomartException;

/**
 * @author Florian Reisinger
 * @since 1.0
 */
@Controller
public class PrideLegacyRedirectionsController {

    private static Logger accessLog = LoggerFactory.getLogger("uk.ac.ebi.pride.archive.redirections.AccessLogger");

    public final static String PRIDE_LEGACY_URL = "/legacy";


    @RequestMapping(value = "/directLink.do", method = RequestMethod.GET)
    public org.springframework.web.servlet.ModelAndView prideDirectExperimentLinkToPrideLegacy(@RequestParam("experimentAccessionNumber") String accession) {
//        String target = "/directLink.do?experimentAccessionNumber=" + accession;
        String target = "/archive/assays/" + accession;
        accessLog.info("FROM legacy /directLink.do TO: " + target);
        return new ModelAndView("redirect:" + target);
    }

    @RequestMapping(value = "/experimentLink.do", method = RequestMethod.GET)
    public org.springframework.web.servlet.ModelAndView prideExperimentLinkToPrideLegacy(@RequestParam("experimentAccessionNumber") String accession) {
//        String target = "/experimentLink.do?experimentAccessionNumber=" + accession;
        String target = "/archive/assays/" + accession;
        accessLog.info("FROM legacy /experimentLink.do TO: " + target);
        return new ModelAndView("redirect:" + target);
    }

    @RequestMapping(value = "/experiment.do", method = RequestMethod.GET)
    public org.springframework.web.servlet.ModelAndView prideExperimentToPrideLegacy(@RequestParam("experimentAccessionNumber") String accession) {
//        String target = "/experiment.do?experimentAccessionNumber=" + accession;
        String target = "/archive/assays/" + accession;
        accessLog.info("FROM legacy /experiment.do TO: " + target);
        return new ModelAndView("redirect:" + target);
    }

    @RequestMapping(value = "/prideMart.do", method = RequestMethod.GET)
    public org.springframework.web.servlet.ModelAndView prideBioMartLinkToPrideLegacy() {
        String target = "/prideMart.do";
        accessLog.info("FROM " + target + " TO: " + PRIDE_LEGACY_URL + target);
        return new ModelAndView("redirect:" + PRIDE_LEGACY_URL + target);
    }

    @RequestMapping( value = "/searchSummary.do", method = RequestMethod.GET)
    public org.springframework.web.servlet.ModelAndView prideSearchToPrideLegacy(@RequestParam("queryTypeSelected") String type, @RequestParam("identificationAccessionNumber") String accession) {
//        String target = "/searchSummary.do?queryTypeSelected=" + type + "&identificationAccessionNumber=" + accession;
        String target = "/archive/simpleSearch?q=" + accession;
        accessLog.info("FROM legacy /searchSummary.do TO: " + target);
        return new ModelAndView("redirect:" + target);
    }

    @RequestMapping( value = "/biomart/**", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String prideBioMartView() {
        return  "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
                "    <title>BioMart discontinued</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1>BioMart Error</h1>" +
                "    <div>The PRIDE BioMart service has been discontinued.</br>" +
                "    Please consider using the " +
                "      <a href=\"http://www.ebi.ac.uk/pride/ws/archive\">" +
                "        PRIDE Archive web service" +
                "      </a>" +
                "    instead. Or contact the " +
                "      <a href=\"mailto:pride-supprot@ebi.ac.uk\">" +
                "        PRIDE helpdesk" +
                "      </a>" +
                "    if you have any questions.</div>" +
                "  </body>\n" +
                "</html>";
    }

    @RequestMapping( value = "/biomart/martservice/**")
    @ResponseBody
    public String prideBioMartService() {
        throw new BiomartException("The Biomart service has been discontinued");
    }


}
