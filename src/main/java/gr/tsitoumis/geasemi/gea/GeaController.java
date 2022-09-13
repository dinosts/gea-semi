package gr.tsitoumis.geasemi.gea;

import gr.tsitoumis.geasemi.gea.entities.GeaClassesResponseBody;
import gr.tsitoumis.geasemi.gea.entities.GeaPackagesResponse;
import gr.tsitoumis.geasemi.utils.Commands;
import gr.tsitoumis.geasemi.utils.GitTools;
import gr.tsitoumis.geasemi.utils.MessageResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gea")
public class GeaController {

    @Autowired
    private GeaService service;

    @RequestMapping(value = "run", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MessageResponseBody> run(@RequestParam String url, @RequestParam String language) throws Exception {
        String name = GitTools.getProjectName(url);
        Commands.gitClone(url);
        Commands.geaRun(name, language);
        Commands.deleteProject(name);


        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseBody("Gea analysis COMPLETED"));
    }

    @RequestMapping(value = "classes", method = RequestMethod.GET)
    @ResponseBody
    public GeaClassesResponseBody geaClasses(@RequestParam String projectName, @RequestParam int page, @RequestParam int pageSize) throws Exception {
        return service.getGeaClasses(projectName, page, pageSize);
    }

    @RequestMapping(value = "packages/movable-classes", method = RequestMethod.GET)
    @ResponseBody
    public GeaPackagesResponse geaPackages(@RequestParam String projectName, @RequestParam int page, @RequestParam int pageSize) throws Exception {
        return service.getGeaPackagesWithMovableClasses(projectName, page, pageSize);
    }
}
