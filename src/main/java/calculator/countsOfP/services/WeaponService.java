package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.StatsWeaponNBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.response.StatsWeaponNResponse;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.models.dao.POrganDAO;
import calculator.countsOfP.models.weapon.*;
import calculator.countsOfP.models.weapon.dao.*;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeaponService {

    private StatsWeaponSDAO statsWeaponSDAO;
    private WeaponUpgradeSDAO weaponUpgradeSDAO;
    private WeaponUpgradeNDAO weaponUpgradeNDAO;
    private HandleDAO handleDAO;
    private BladeDAO bladeDAO;
    private POrganDAO pOrganDAO;


    public WeaponService(StatsWeaponSDAO statsWeaponSDAO, WeaponUpgradeSDAO weaponUpgradeSDAO, WeaponUpgradeNDAO weaponUpgradeNDAO, HandleDAO handleDAO, BladeDAO bladeDAO, POrganDAO pOrganDAO) {
        this.statsWeaponSDAO = statsWeaponSDAO;
        this.weaponUpgradeSDAO = weaponUpgradeSDAO;
        this.weaponUpgradeNDAO = weaponUpgradeNDAO;
        this.handleDAO = handleDAO;
        this.bladeDAO = bladeDAO;
        this.pOrganDAO = pOrganDAO;
    }

    public StatsWeaponSResponse upgradeLevelS(StatsWeaponSBody body){
        Long initialLevel = body.getCurrentLevel();
        Long finalLevel = body.getFinalLevel();
        StatsWeaponS stats = statsWeaponSDAO.findByNameAndCurrentLevel(body.getWeaponName(),
                weaponUpgradeSDAO.findById(finalLevel).get());
        Long ergoCost = 0L;
        Map<String, Integer> materials = new LinkedHashMap<>();
        for (Long level = initialLevel + 1; level <= finalLevel; level++){
            WeaponUpgradeS upgradeLevel = weaponUpgradeSDAO.findById(level).get();
            ergoCost += upgradeLevel.getErgo();
            String materialName = upgradeLevel.getMaterial();
            Integer quantity = upgradeLevel.getQuantity();
            if (materials.containsKey(materialName)){
                Integer result = quantity + materials.get(materialName);
                materials.replace(materialName, materials.get(materialName), result);
            }else {
                materials.put(materialName, quantity);
            }
        }
        return new StatsWeaponSResponse(ergoCost, materials, stats);
    }

    public StatsWeaponNResponse upgradeLevelN(StatsWeaponNBody body){
        Long initialLevel = body.getCurrentLevel();
        Long finalLevel = body.getFinalLevel();
        Blade blade = bladeDAO.findByNameAndCurrentLevel(body.getBladeName(),
                weaponUpgradeNDAO.findById(finalLevel).get());
        Long ergoCost = 0L;
        Map<String, Integer> materials = new LinkedHashMap<>();
        for (Long level = initialLevel + 1; level <= finalLevel; level++){
            WeaponUpgradeN upgradeLevel = weaponUpgradeNDAO.findById(level).get();
            ergoCost += upgradeLevel.getErgo();
            String materialName = upgradeLevel.getMaterial();
            Integer quantity = upgradeLevel.getQuantity();
            if (materials.containsKey(materialName)){
                Integer result = quantity + materials.get(materialName);
                materials.replace(materialName, materials.get(materialName), result);
            }else {
                materials.put(materialName, quantity);
            }

        }
        Handle handle = handleDAO.findById(body.getHandleId()).get();
        Double weight = blade.getWeight() + handle.getWeight();
        String weaponName = body.getBladeName() + " | " + handle.getName();
        StatsWeaponNResponse response = new StatsWeaponNResponse(weaponName, ergoCost, materials, weight, blade.getTotalAttack(), handle.getMotivity(),
                handle.getTechnique(), handle.getAdvance(), blade.getPhysicalAttack(), blade.getElementalAttack(), blade, handle);
        return response;
    }

    public List<StatsWeaponS> getAllWeaponsSWithLevels(){
        return statsWeaponSDAO.findAll();
    }

    public List<StatsWeaponS> getAllWeaponsS(){
        WeaponUpgradeS currentLevel = weaponUpgradeSDAO.findById(1L).get();
        return statsWeaponSDAO.findByCurrentLevel(currentLevel);
    }

    public StatsWeaponS getWeaponS(Long id){
        return statsWeaponSDAO.findById(id).get();
    }

    public List<StatsWeaponS> searchWeaponS(String keyword){
        return statsWeaponSDAO.search(keyword);
    }

    public List<Blade> getAllBladesWithLevels(){
        return bladeDAO.findAll();
    }

    public List<Blade> getAllBlades(){
        WeaponUpgradeN currenteLevel = weaponUpgradeNDAO.findById(1L).get();
        return bladeDAO.findByCurrentLevel(currenteLevel);
    }

    public Blade getBlade(Long id){
        return bladeDAO.findById(id).get();
    }

    public List<Blade> searchBlade(String keyword){
        return bladeDAO.search(keyword);
    }

    public List<Handle> getAllHandles(){
        return handleDAO.findAll();
    }

    public Handle getHandle(Long id){
        return handleDAO.findById(id).get();
    }

    public List<Handle> searchHandle(String keyword){
        return handleDAO.search(keyword);
    }
}
