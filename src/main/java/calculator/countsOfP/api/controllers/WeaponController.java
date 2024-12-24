package calculator.countsOfP.api.controllers;

import calculator.countsOfP.api.models.body.StatsWeaponNBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.response.StatsWeaponNResponse;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.models.weapon.Blade;
import calculator.countsOfP.models.weapon.Handle;
import calculator.countsOfP.models.weapon.StatsWeaponS;
import calculator.countsOfP.services.WeaponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weapon")
public class WeaponController {

    private WeaponService weaponService;

    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    //Weapon S

    @GetMapping("/S/{weaponId}")
    public StatsWeaponS getWeaponS (@PathVariable Long weaponId){
        return weaponService.getWeaponS(weaponId);
    }

    @GetMapping("/S/all")
    public List<StatsWeaponS> getWeaponSWithLevelsList (){
        return weaponService.getAllWeaponsSWithLevels();
    }

    @GetMapping("/S")
    public List<StatsWeaponS> getWeaponSList (){
        return weaponService.getAllWeaponsS();
    }

    @GetMapping("/S/search")
    public List<StatsWeaponS> searchWeaponS(@RequestParam String keyword){
        return weaponService.searchWeaponS(keyword);
    }

    @PostMapping("/S/upgrade")
    public ResponseEntity<StatsWeaponSResponse> upgradeWeaponS(@RequestBody StatsWeaponSBody body){
        return ResponseEntity.ok(weaponService.upgradeLevelS(body));
    }

    //Weapon N

    @GetMapping("/blade/{bladeId}")
    public Blade getBlade (@PathVariable Long bladeId){
        return weaponService.getBlade(bladeId);
    }

    @GetMapping("/handle/{handleId}")
    public Handle getHandle(@PathVariable Long handleId){
        return weaponService.getHandle(handleId);
    }

    @GetMapping("/blade/all")
    public List<Blade> getBladeWithLevelsList (){
        return weaponService.getAllBladesWithLevels();
    }

    @GetMapping("/blade")
    public List<Blade> getBladeList (){
        return weaponService.getAllBlades();
    }

    @GetMapping("/blade/search")
    public List<Blade> searchBlade(@RequestParam String keyword){
        return weaponService.searchBlade(keyword);
    }

    @GetMapping("/handle")
    public List<Handle> getHandleList (){
        return weaponService.getAllHandles();
    }

    @GetMapping("/handle/search")
    public List<Handle> searchHandle(@RequestParam String keyword){
        return weaponService.searchHandle(keyword);
    }

    @PostMapping("/N/upgrade")
    public ResponseEntity<StatsWeaponNResponse> upgradeWeaponN(@RequestBody StatsWeaponNBody body){
        return ResponseEntity.ok(weaponService.upgradeLevelN(body));
    }
}
