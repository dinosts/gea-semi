package gr.tsitoumis.geasemi.gea;

import gr.tsitoumis.geasemi.gea.schemas.GeaRunRequestBody;
import gr.tsitoumis.geasemi.gea.schemas.GeaRunResponseBody;
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
@RequestMapping("/gea")
public class GeaController {
    @RequestMapping(value = "run", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GeaRunResponseBody> run(@RequestBody GeaRunRequestBody request) throws Exception {
        String url = request.getUrl();
        String name = GitTools.getProjectName(request.getUrl());
        String lang = request.getLanguage();
        ResponseEntity<GeaRunResponseBody> response;

        try {
            Commands.gitClone(url);
            Commands.geaRun(name, lang);
            response = ResponseEntity.status(HttpStatus.OK).body(new GeaRunResponseBody("Gea analysis COMPLETED"));
        } catch (GeaSemiException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeaRunResponseBody("Gea analysis FAILED: " + e.getMessage()));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeaRunResponseBody("Gea analysis FAILED: Internal Error"));
        }

        Commands.deleteProject(name);

        return response;
    }
}
