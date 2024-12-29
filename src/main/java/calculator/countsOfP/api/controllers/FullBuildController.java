package calculator.countsOfP.api.controllers;

import calculator.countsOfP.api.models.body.FullBuildBody;
import calculator.countsOfP.api.models.response.FullBuildResponse;
import calculator.countsOfP.exceptions.ErrorResponse;
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
    public ResponseEntity<Object> costQuartz(@RequestBody Map<Integer, Integer> body) {

        try{
            return ResponseEntity.ok(buildService.costQuartzTotal(body));
        } catch(NotEnoughModulesException e){
            int totalModules = 0;
            String errorResponse = "";
            for (Integer level:body.keySet()){
                int moduleQuantity = body.get(level);
                int minimumTotalModules = 2*(level-1);
                if (totalModules<minimumTotalModules){
                    errorResponse = "It is needed at least 2 modules per each P Organ level. You need at least " + minimumTotalModules +
                            " modules to unlock level " + level + ".";
                    break;
                }
                totalModules += moduleQuantity;
            }
            String path = "/build/p_organ";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, errorResponse, path));
        }
    }

    @PostMapping("/build")
    public ResponseEntity<FullBuildResponse> build(FullBuildBody body){
        return ResponseEntity.ok(buildService.build(body));
    }
}
