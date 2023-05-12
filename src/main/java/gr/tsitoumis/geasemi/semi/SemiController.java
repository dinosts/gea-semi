package gr.tsitoumis.geasemi.semi;


import gr.tsitoumis.geasemi.semi.entities.SemiOpportunitiesResponseBody;
import gr.tsitoumis.geasemi.semi.tool.source.jarExecuteClass.Semitool;
import gr.tsitoumis.geasemi.utils.Commands;
import gr.tsitoumis.geasemi.utils.GitTools;
import gr.tsitoumis.geasemi.utils.MessageResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;


@Controller
@RequestMapping("/semi")
public class SemiController {
    @RequestMapping(value = "run", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MessageResponseBody> run(@RequestParam String url, @RequestParam String version, @RequestParam String language) throws Exception {
        String name = GitTools.getProjectName(url);

        Commands.gitClone(url);

        new Semitool().run(service, language, name, version, Paths.get("git-repositories/" + name).toAbsolutePath().toString());

        ResponseEntity<MessageResponseBody> response = ResponseEntity.status(HttpStatus.OK).body(new MessageResponseBody("Semi analysis COMPLETED"));

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
