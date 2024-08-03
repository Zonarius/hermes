package party.zonarius.hermes;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import party.zonarius.hermes.sender.HermesSender;

@RestController
public class HermesController {

    private final HermesSender sender;

    public HermesController(HermesSender sender) {
        this.sender = sender;
    }

    @CrossOrigin
    @PostMapping(value = "/message")
    public void message(@RequestBody String message) {
        sender.sendMessage(message);
    }
}
