package gr.tsitoumis.geasemi.semi;

import gr.tsitoumis.geasemi.semi.schemas.SemiRunRequestBody;
import gr.tsitoumis.geasemi.semi.schemas.SemiRunResponseBody;
import gr.tsitoumis.geasemi.utils.Commands;
import gr.tsitoumis.geasemi.utils.GeaSemiException;
import gr.tsitoumis.geasemi.utils.GitTools;
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

        ResponseEntity<SemiRunResponseBody> response;

        try {
            Commands.gitClone(url);

            Commands.semiRun(name, lang, version);

            response = ResponseEntity.status(HttpStatus.OK).body(new SemiRunResponseBody("Semi analysis COMPLETED"));
        } catch (GeaSemiException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SemiRunResponseBody("Semi analysis FAILED: " + e.getMessage()));
        } catch (Exception e) {

            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SemiRunResponseBody("Semi analysis FAILED"));
        }

        Commands.deleteProject(name);

        return response;
    }
}
