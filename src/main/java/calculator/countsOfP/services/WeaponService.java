package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.StatsWeaponNBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.body.TotalAttackBody;
import calculator.countsOfP.api.models.response.StatsWeaponNResponse;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.api.models.response.TotalAttackResponse;
import calculator.countsOfP.models.build.dao.POrganDAO;
import calculator.countsOfP.models.player.dao.AttributeDAO;
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
    private ScalingDAO scalingDAO;
    private AttributeDAO attributeDAO;


    public WeaponService(StatsWeaponSDAO statsWeaponSDAO, WeaponUpgradeSDAO weaponUpgradeSDAO, WeaponUpgradeNDAO weaponUpgradeNDAO, HandleDAO handleDAO, BladeDAO bladeDAO, POrganDAO pOrganDAO, ScalingDAO scalingDAO, AttributeDAO attributeDAO) {
        this.statsWeaponSDAO = statsWeaponSDAO;
        this.weaponUpgradeSDAO = weaponUpgradeSDAO;
        this.weaponUpgradeNDAO = weaponUpgradeNDAO;
        this.handleDAO = handleDAO;
        this.bladeDAO = bladeDAO;
        this.pOrganDAO = pOrganDAO;
        this.scalingDAO = scalingDAO;
        this.attributeDAO = attributeDAO;
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

    public TotalAttackResponse calculateAttack(TotalAttackBody body){
        String name;
        StatsWeaponS weaponS ;
        Blade blade;
        Handle handle;
        Integer basePhysicalAttack;
        Integer baseElementalAttack;
        Character motivityScaling;
        Character techniqueScaling;
        Character advanceScaling;
        if (body.getIsWeaponS()){
            weaponS = statsWeaponSDAO.findById(body.getWeaponSId()).get();
            name = weaponS.getName();
            baseElementalAttack = weaponS.getElementalAttack();
            basePhysicalAttack = weaponS.getPhysicalAttack();
            motivityScaling = weaponS.getMotivity();
            techniqueScaling = weaponS.getTechnique();
            advanceScaling = weaponS.getAdvance();
        }else{
            blade = bladeDAO.findById(body.getBladeId()).get();
            handle = handleDAO.findById(body.getHandleId()).get();
            name = blade.getName() + " | " + handle.getName();
            baseElementalAttack = blade.getElementalAttack();
            basePhysicalAttack = blade.getPhysicalAttack();
            motivityScaling = handle.getMotivity();
            techniqueScaling = handle.getTechnique();
            advanceScaling = handle.getAdvance();
        }

        Integer bonusPhysicalAttack =  calculateBonusAttack(4L, motivityScaling, body.getMotivity(), basePhysicalAttack) +
                calculateBonusAttack(5L, techniqueScaling, body.getTechnique(), basePhysicalAttack);
        Integer bonusElementalAttack =  calculateBonusAttack(6L, advanceScaling, body.getAdvance(), baseElementalAttack);
        Integer totalAttack = baseElementalAttack + basePhysicalAttack + bonusPhysicalAttack + bonusElementalAttack;

        return new TotalAttackResponse(name,basePhysicalAttack, baseElementalAttack, bonusPhysicalAttack, bonusElementalAttack, totalAttack);
    }

    public Integer calculateBonusAttack(Long attributeId, Character scalingLetter, Integer attributeValue, Integer baseAttack){
        double modifier = 0.00;
        if (scalingLetter != '-'){
            modifier = scalingDAO.findByAttributeAndLetterAndLevel(
                    attributeDAO.findById(attributeId).get(), scalingLetter, attributeValue).getBonusAtk();
        }
        double bonusAttack = baseAttack * modifier;
        return (int) bonusAttack;
    }

}
