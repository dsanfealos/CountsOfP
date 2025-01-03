package calculator.countsOfP.api.controllers;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.api.models.response.StatsResponse;
import calculator.countsOfP.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<Object> getSimulatedStats(@RequestBody Map<String,AttributesBody> body){
        AttributesBody initialBody = body.get("initial");
        AttributesBody finalBody = body.get("final");
        if (initialBody.getAmulets().size() > 5 || finalBody.getAmulets().size() > 5){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Maximum number of amulets is 5");
        }

        return ResponseEntity.ok(playerService.simulateStats(initialBody, finalBody));
    }
}
