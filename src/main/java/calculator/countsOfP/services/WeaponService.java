package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.StatsWeaponNBody;
import calculator.countsOfP.api.models.body.StatsWeaponSBody;
import calculator.countsOfP.api.models.body.TotalAttackBody;
import calculator.countsOfP.api.models.response.StatsWeaponNResponse;
import calculator.countsOfP.api.models.response.StatsWeaponSResponse;
import calculator.countsOfP.api.models.response.TotalAttackResponse;
import calculator.countsOfP.models.player.dao.AttributeDAO;
import calculator.countsOfP.models.weapon.*;
import calculator.countsOfP.models.weapon.dao.*;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeaponService {

    private final StatsWeaponSDAO statsWeaponSDAO;
    private final WeaponUpgradeSDAO weaponUpgradeSDAO;
    private final WeaponUpgradeNDAO weaponUpgradeNDAO;
    private final HandleDAO handleDAO;
    private final BladeDAO bladeDAO;
    private final ScalingDAO scalingDAO;
    private final AttributeDAO attributeDAO;


    public WeaponService(StatsWeaponSDAO statsWeaponSDAO, WeaponUpgradeSDAO weaponUpgradeSDAO, WeaponUpgradeNDAO weaponUpgradeNDAO, HandleDAO handleDAO, BladeDAO bladeDAO, ScalingDAO scalingDAO, AttributeDAO attributeDAO) {
        this.statsWeaponSDAO = statsWeaponSDAO;
        this.weaponUpgradeSDAO = weaponUpgradeSDAO;
        this.weaponUpgradeNDAO = weaponUpgradeNDAO;
        this.handleDAO = handleDAO;
        this.bladeDAO = bladeDAO;
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
        if (body.getModifier() != null) modifyHandle(stats, body.getModifier());
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
        if (body.getModifier() != null) modifyHandle(handle, body.getModifier());

        return new StatsWeaponNResponse(weaponName, ergoCost, materials, weight, blade.getTotalAttack(), handle.getMotivity(),
                handle.getTechnique(), handle.getAdvance(), blade.getPhysicalAttack(), blade.getElementalAttack(), blade, handle);
    }

    public List<Handle> searchHandle(String keyword){
        return handleDAO.search(keyword);
    }

    public TotalAttackResponse calculateAttack(TotalAttackBody body){
        String name;
        StatsWeaponS weaponS;
        Blade blade;
        Handle handle;
        Integer baseLevelOnePhysicalAttack, baseLevelOneElementalAttack;
        Integer basePhysicalAttack, baseElementalAttack;
        Character motivityScaling, techniqueScaling, advanceScaling;

        if (body.getIsWeaponS()){
            weaponS = body.getWeaponS();
            name = weaponS.getName();
            baseElementalAttack = weaponS.getElementalAttack();
            basePhysicalAttack = weaponS.getPhysicalAttack();
            motivityScaling = weaponS.getMotivity();
            techniqueScaling = weaponS.getTechnique();
            advanceScaling = weaponS.getAdvance();
            baseLevelOnePhysicalAttack = statsWeaponSDAO.findByNameAndCurrentLevel(weaponS.getName(), weaponUpgradeSDAO.findById(1L).get()).getPhysicalAttack();
            baseLevelOneElementalAttack = statsWeaponSDAO.findByNameAndCurrentLevel(weaponS.getName(), weaponUpgradeSDAO.findById(1L).get()).getElementalAttack();
        }else{
            blade = body.getBlade();
            handle = body.getHandle();
            name = blade.getName() + " | " + handle.getName();
            baseElementalAttack = blade.getElementalAttack();
            basePhysicalAttack = blade.getPhysicalAttack();
            motivityScaling = handle.getMotivity();
            techniqueScaling = handle.getTechnique();
            advanceScaling = handle.getAdvance();
            baseLevelOnePhysicalAttack = bladeDAO.findByNameAndCurrentLevel(blade.getName(), weaponUpgradeNDAO.findById(1L).get()).getPhysicalAttack();
            baseLevelOneElementalAttack = bladeDAO.findByNameAndCurrentLevel(blade.getName(), weaponUpgradeNDAO.findById(1L).get()).getElementalAttack();
        }

        Integer bonusPhysicalAttack =  calculateBonusAttack(4L, motivityScaling, body.getMotivity(), baseLevelOnePhysicalAttack) +
                calculateBonusAttack(5L, techniqueScaling, body.getTechnique(), baseLevelOnePhysicalAttack);
        Integer bonusElementalAttack =  calculateBonusAttack(6L, advanceScaling, body.getAdvance(), baseLevelOneElementalAttack);
        Integer totalAttack = baseElementalAttack + basePhysicalAttack + bonusPhysicalAttack + bonusElementalAttack;

        return new TotalAttackResponse(name,basePhysicalAttack, baseElementalAttack, bonusPhysicalAttack, bonusElementalAttack, totalAttack);
    }

    public Integer calculateBonusAttack(Long attributeId, Character scalingLetter, Integer attributeValue, Integer baseLevelOneAttack){
        double modifier = 0.00;
        if (scalingLetter != '-'){
            modifier = scalingDAO.findByAttributeAndLetterAndLevel(
                    attributeDAO.findById(attributeId).get(), scalingLetter, attributeValue).getBonusAtk();
        }
        double bonusAttack = baseLevelOneAttack * modifier;
        return (int) bonusAttack;
    }

    public void modifyHandle(Handle handle, String type){
        char[] options = {'-','D','C','B','A','S'};
        int length = options.length;
        int indexM = 0;
        int indexT = 0;
        int indexA = 0;

        for (int i = 0; i<length; i++){
            if (options[i] == handle.getMotivity()) indexM = i;
            if (options[i] == handle.getTechnique()) indexT = i;
            if (options[i] == handle.getAdvance()) indexA = i;
        }

        switch (type){
            case "motivity":
                if (indexM != length-1) indexM++;
                if (indexT != 0) indexT--;
                handle.setMotivity(options[indexM]);
                handle.setTechnique(options[indexT]);
                break;
            case "technique":
                if (indexM != 0) indexM--;
                if (indexT != length-1) indexT++;
                handle.setMotivity(options[indexM]);
                handle.setTechnique(options[indexT]);
                break;
            case "advance":
                if (indexA != length-1) indexA++;
                handle.setAdvance(options[indexA]);
                break;
            default:
                System.err.println("Wrong type. It needs to be motivity, technique or advance");
                break;
        }
    }

    public void modifyHandle(StatsWeaponS weaponS, String type){
        char[] options = {'-','D','C','B','A','S'};
        int length = options.length;
        int indexM = 0;
        int indexT = 0;
        int indexA = 0;

        for (int i = 0; i<length; i++){
            if (options[i] == weaponS.getMotivity()) indexM = i;
            if (options[i] == weaponS.getTechnique()) indexT = i;
            if (options[i] == weaponS.getAdvance()) indexA = i;
        }

        switch (type){
            case "motivity":
                if (indexM != length-1) indexM++;
                if (indexT != 0) indexT--;
                weaponS.setMotivity(options[indexM]);
                weaponS.setTechnique(options[indexT]);
                break;
            case "technique":
                if (indexM != 0) indexM--;
                if (indexT != length-1) indexT++;
                weaponS.setMotivity(options[indexM]);
                weaponS.setTechnique(options[indexT]);
                break;
            case "advance":
                if (indexA != length-1) indexA++;
                weaponS.setAdvance(options[indexA]);
                break;
        }
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
        WeaponUpgradeN currentLevel = weaponUpgradeNDAO.findById(1L).get();
        return bladeDAO.findByCurrentLevel(currentLevel);
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

}
