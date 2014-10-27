package uk.ac.ebi.pride.archive.redirections.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        accessLog.info("FROM " + target + " TO: " + target);
        return new ModelAndView("redirect:" + target);
    }

    @RequestMapping(value = "/experimentLink.do", method = RequestMethod.GET)
    public org.springframework.web.servlet.ModelAndView prideExperimentLinkToPrideLegacy(@RequestParam("experimentAccessionNumber") String accession) {
//        String target = "/experimentLink.do?experimentAccessionNumber=" + accession;
        String target = "/archive/assays/" + accession;
        accessLog.info("FROM " + target + " TO: " + target);
        return new ModelAndView("redirect:" + target);
    }

    @RequestMapping(value = "/experiment.do", method = RequestMethod.GET)
    public org.springframework.web.servlet.ModelAndView prideExperimentToPrideLegacy(@RequestParam("experimentAccessionNumber") String accession) {
//        String target = "/experiment.do?experimentAccessionNumber=" + accession;
        String target = "/archive/assays/" + accession;
        accessLog.info("FROM " + target + " TO: " + target);
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
        accessLog.info("FROM " + target + " TO: " + target);
        return new ModelAndView("redirect:" + target);
    }

}
