package gr.tsitoumis.geasemi.semi;

import gr.tsitoumis.geasemi.semi.entities.SemiOpportunitiesResponseBody;
import gr.tsitoumis.geasemi.semi.entities.SemiRunResponseBody;
import gr.tsitoumis.geasemi.utils.Commands;
import gr.tsitoumis.geasemi.utils.GitTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/semi")
public class SemiController {
    @RequestMapping(value = "run", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SemiRunResponseBody> run(@RequestParam String url, @RequestParam String version, @RequestParam String language) throws Exception {
        String name = GitTools.getProjectName(url);

        Commands.gitClone(url);

        Commands.semiRun(name, language, version);

        ResponseEntity<SemiRunResponseBody> response = ResponseEntity.status(HttpStatus.OK).body(new SemiRunResponseBody("Semi analysis COMPLETED"));

        Commands.deleteProject(name);

        return response;
    }

    @Autowired
    SemiService service = new SemiService();

    @RequestMapping(value = "opportunities", method = RequestMethod.GET)
    @ResponseBody
    public SemiOpportunitiesResponseBody opportunities(@RequestParam String projectName, @RequestParam int page, @RequestParam int pageSize) throws Exception {
        return service.getProjectOpportunities(projectName, page, pageSize);
    }

}
