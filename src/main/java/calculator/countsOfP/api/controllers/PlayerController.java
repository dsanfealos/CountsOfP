package calculator.countsOfP.api.controllers;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.api.models.response.StatsResponse;
import calculator.countsOfP.services.PlayerService;
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
    public ResponseEntity<StatsResponse> getSimulatedStats(@RequestBody Map<String,AttributesBody> body){
        AttributesBody initialBody = body.get("initial");
        AttributesBody finalBody = body.get("final");
        return ResponseEntity.ok(playerService.simulateStats(initialBody, finalBody));
    }
}
