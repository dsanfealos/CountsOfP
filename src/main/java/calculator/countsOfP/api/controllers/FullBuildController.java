package calculator.countsOfP.api.controllers;

import calculator.countsOfP.api.models.response.POrganResponse;
import calculator.countsOfP.exceptions.NotEnoughModulesException;
import calculator.countsOfP.services.FullBuildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/build")
public class FullBuildController {

    private FullBuildService buildService;

    public FullBuildController(FullBuildService buildService) {
        this.buildService = buildService;
    }

    @PostMapping("/arm")
    public ResponseEntity<Integer> costUpgradeArm(@RequestBody Map<String, Integer> body){
        Integer initialLevel = body.get("initialLevel");
        Integer finalLevel = body.get("finalLevel");
        return ResponseEntity.ok(buildService.costUpgradeArm(initialLevel, finalLevel));
    }

    @PostMapping("/p_organ")
    public POrganResponse costQuartz(@RequestBody Map<Integer, Integer> body) {
        //Todo Create exception if for n lvls, there are less than 2n modules.
        //Todo make it show at API response
        POrganResponse response = new POrganResponse(buildService.costQuartzTotal(body));
        return response;
    }
}
