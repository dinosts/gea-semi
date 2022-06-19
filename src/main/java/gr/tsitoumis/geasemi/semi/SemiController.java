package gr.tsitoumis.geasemi.semi;

import gr.tsitoumis.geasemi.semi.schemas.SemiRunRequestBody;
import gr.tsitoumis.geasemi.semi.schemas.SemiRunResponseBody;
import gr.tsitoumis.geasemi.services.CommandService;
import gr.tsitoumis.geasemi.services.GeaSemiException;
import gr.tsitoumis.geasemi.services.GitTools;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/semi")
public class SemiController {
    @RequestMapping(value = "run", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SemiRunResponseBody> run(@RequestBody SemiRunRequestBody request) throws Exception {
        String url = request.getUrl();
        String name = GitTools.getProjectName(request.getUrl());
        String version = request.getVersion();
        String lang = request.getLanguage();
        
        try {
            CommandService.gitClone(url);

            CommandService.semiRun(name, lang, version);

            CommandService.deleteProject(name);

            return ResponseEntity.status(HttpStatus.OK).body(new SemiRunResponseBody("Semi analysis COMPLETED"));
        } catch (GeaSemiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SemiRunResponseBody("Semi analysis FAILED: " + e.getMessage()));
        } catch (Exception e) {
            CommandService.deleteProject(name);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SemiRunResponseBody("Semi analysis FAILED"));
        }
    }
}
