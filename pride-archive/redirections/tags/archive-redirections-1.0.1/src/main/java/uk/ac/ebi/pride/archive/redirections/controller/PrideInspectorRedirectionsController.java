package uk.ac.ebi.pride.archive.redirections.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.ebi.pride.archive.redirections.exception.AssayNotFoundException;
import uk.ac.ebi.pride.archive.repo.assay.Assay;
import uk.ac.ebi.pride.archive.repo.assay.AssayRepository;
import uk.ac.ebi.pride.archive.repo.project.ProjectRepository;

/**
 * @author Jose A. Dianes
 * @version $Id$
 */
@Controller
public class PrideInspectorRedirectionsController {

    private static Logger accessLog = LoggerFactory.getLogger("uk.ac.ebi.pride.archive.redirections.AccessLogger");
    public final static String PRIDE_ARCHIVE_PROJECTS_URL = "/archive/projects/";

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AssayRepository assayRepository;

    @RequestMapping(value = "/q.do", method = RequestMethod.GET)
    public ModelAndView prideToPrideArchive(@RequestParam("accession")String accession) {
        accessLog.info("FROM /q.do?accession=" + accession + " TO: " + PRIDE_ARCHIVE_PROJECTS_URL + "#");

        // extract the accession
        String [] accessions = accession.split("#");
        String assayAccession = accessions[0];

        // get the project accession
        Assay assay = assayRepository.findByAccession(assayAccession);
        if (assay != null) {
            String projectAccession = projectRepository.findOne(assay.getProjectId()).getAccession();
            // redirect
            String redirectsTo = PRIDE_ARCHIVE_PROJECTS_URL + projectAccession;
            return new ModelAndView("redirect:" + redirectsTo + "#");
        } else {
            throw new AssayNotFoundException("Cannot find assay for accession: " + assayAccession);
        }
    }
}
