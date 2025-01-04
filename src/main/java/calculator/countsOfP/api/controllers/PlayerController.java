package calculator.countsOfP.api.controllers;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.models.build.Amulet;
import calculator.countsOfP.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        if (finalBody.getAmuletIds() != null) {
            if (finalBody.getAmuletIds().size() > 5) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Maximum number of amulets is 5");
            }
        }

        return ResponseEntity.ok(playerService.simulateStats(initialBody, finalBody));
    }

    @GetMapping("/amulet")
    public List<Amulet> getAllAmulets(){
        return playerService.getAllAmulets();
    }

    @GetMapping("/amulet/{amuletId}")
    public Amulet getAmulet(@PathVariable Long amuletId){
        return playerService.getAmulet(amuletId);
    }
}
