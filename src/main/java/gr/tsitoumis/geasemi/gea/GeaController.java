package gr.tsitoumis.geasemi.gea;

import gr.tsitoumis.geasemi.gea.entities.GeaRunResponseBody;
import gr.tsitoumis.geasemi.utils.Commands;
import gr.tsitoumis.geasemi.utils.GitTools;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gea")
public class GeaController {
    @RequestMapping(value = "run", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GeaRunResponseBody> run(@RequestParam String url, @RequestParam String language) throws Exception {
        String name = GitTools.getProjectName(url);
        Commands.gitClone(url);
        Commands.geaRun(name, language);

        return ResponseEntity.status(HttpStatus.OK).body(new GeaRunResponseBody("Gea analysis COMPLETED"));
    }
}
