package gr.tsitoumis.geasemi.semi;

import gr.tsitoumis.geasemi.semi.schemas.SemiRunRequestBody;
import gr.tsitoumis.geasemi.semi.schemas.SemiRunResponseBody;
import gr.tsitoumis.geasemi.services.CommandService;
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
    public ResponseEntity<SemiRunResponseBody> run(@RequestBody SemiRunRequestBody request) {
        try {
            CommandService.gitClone(request.getUrl());
            CommandService.semiRun(request);

            return ResponseEntity.status(HttpStatus.OK).body(new SemiRunResponseBody("Semi analysis COMPLETED"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SemiRunResponseBody("Semi analysis FAILED: " + e.getMessage()));
        }
    }
}
