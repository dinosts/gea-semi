package gr.tsitoumis.geasemi.health;

import gr.tsitoumis.geasemi.utils.MessageResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HealthController {
    @RequestMapping(value = {"health", "healthy", "healthz", "",})
    @ResponseBody
    public ResponseEntity<MessageResponseBody> health() {
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponseBody("Ok"));
    }
}
