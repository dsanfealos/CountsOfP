package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.FullBuildBody;
import calculator.countsOfP.api.models.body.TotalAttackBody;
import calculator.countsOfP.api.models.response.*;
import calculator.countsOfP.exceptions.NotEnoughModulesException;
import calculator.countsOfP.models.build.Armor;
import calculator.countsOfP.models.build.StatIncreaseArmor;
import calculator.countsOfP.models.build.dao.ArmorDAO;
import calculator.countsOfP.models.build.dao.POrganDAO;
import calculator.countsOfP.models.build.dao.StatIncreaseArmorDAO;
import calculator.countsOfP.models.player.Stat;
import calculator.countsOfP.models.player.dao.StatDAO;
import calculator.countsOfP.models.weapon.Handle;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FullBuildService {

    private POrganDAO pOrganDAO;
    private PlayerService playerService;
    private WeaponService weaponService;
    private StatDAO statDAO;
    private StatIncreaseArmorDAO statIncreaseArmorDAO;
    private ArmorDAO armorDAO;

    public FullBuildService(POrganDAO pOrganDAO, PlayerService playerService, WeaponService weaponService, StatDAO statDAO, StatIncreaseArmorDAO statIncreaseArmorDAO, ArmorDAO armorDAO) {
        this.pOrganDAO = pOrganDAO;
        this.playerService = playerService;
        this.weaponService = weaponService;
        this.statDAO = statDAO;
        this.statIncreaseArmorDAO = statIncreaseArmorDAO;
        this.armorDAO = armorDAO;
    }

    public Integer costUpgradeArm(Integer initialLevel, Integer finalLevel){
        Map<Integer, Integer> costs = Map.of(0,0,1,1,2,2,3,3);
        Integer components = 0;
        for (int index = initialLevel + 1; index <= finalLevel; index++){
            components += costs.get(index);
        }
        return components;
    }

    public POrganResponse costQuartzTotal(Map<Integer, Integer> modules){
        int cost = 0;
        int totalModules = 0;
        for (int level:modules.keySet()){
            int moduleQuantity = modules.get(level);
            int minimumTotalModules = 2*(level-1);
            if (totalModules<minimumTotalModules){
                throw new NotEnoughModulesException();
            }
            totalModules += moduleQuantity;
            cost += moduleQuantity * pOrganDAO.findById((long) level).get().getQuartzs();
        }
        return new POrganResponse(cost);
    }

    public FullBuildResponse build(FullBuildBody body){
        StatsResponse stats = playerService.simulateStats(body.getInitialAttributesBody(), body.getFinalAttributesBody());
        List<Armor> armorPieces = new ArrayList<>();
        for (Long armorId: body.getArmorPiecesIds()){
            armorPieces.add(getArmor(armorId));
        }
        if (body.getArmorPiecesIds() != null) {
            stats = increaseStatsByArmor(stats, armorPieces);
        }
        StatsWeaponNResponse weaponN = null;
        StatsWeaponSResponse weaponS = null;
        TotalAttackBody attackBody;
        if (body.getIsWeaponS()){
            weaponS = weaponService.upgradeLevelS(body.getStatsWeaponSBody());
            if (body.getStatsWeaponSBody().getModifier() != null) weaponService.modifyHandle(weaponS.getStats(), body.getStatsWeaponSBody().getModifier());
            attackBody = new TotalAttackBody(true, weaponS.getStats(),
                    null, null, stats.getMotivity(), stats.getTechnique(), stats.getAdvance());
        } else{
            weaponN = weaponService.upgradeLevelN(body.getStatsWeaponNBody());
            if (body.getStatsWeaponNBody().getModifier() != null) weaponService.modifyHandle(weaponN.getHandle(), body.getStatsWeaponNBody().getModifier());
            attackBody = new TotalAttackBody(false, null,
                    weaponN.getBlade(), weaponN.getHandle(), stats.getMotivity(), stats.getTechnique(), stats.getAdvance());

        }
        TotalAttackResponse attack = weaponService.calculateAttack(attackBody);
        return new FullBuildResponse(stats, attack, body.getIsWeaponS(), weaponN, weaponS, armorPieces, calculateWeight(body));
    }

    public Double calculateWeight(FullBuildBody body){
        double weight = 0.00;
        for (Long amuletId: body.getFinalAttributesBody().getAmuletIds()){
            weight = weight + playerService.getAmulet(amuletId).getWeight();
        }
        for (Long armorId: body.getArmorPiecesIds()){
            weight = weight + getArmor(armorId).getWeight();
        }
        if (body.getIsWeaponS()){
            StatsWeaponSResponse weaponS = weaponService.upgradeLevelS(body.getStatsWeaponSBody());
            weight = weight + weaponS.getStats().getWeight();
        } else{
            StatsWeaponNResponse weaponN = weaponService.upgradeLevelN(body.getStatsWeaponNBody());
            weight = weight + weaponN.getWeight();

        }
        return weight;
    }

    public StatsResponse increaseStatsByArmor(StatsResponse stats, List<Armor> armorPieces){
        List<Stat> allStats = statDAO.findAll();
        List<StatIncreaseArmor> allIncreases = statIncreaseArmorDAO.findByArmorsAndStats(armorPieces, allStats);

        Map<Long, Double> totalIncreases = new LinkedHashMap<>();

        for (StatIncreaseArmor increase : allIncreases) {
            long statId = increase.getStat().getId();
            double flatIncrease = increase.getFlatIncrease();
            totalIncreases.merge(statId, flatIncrease, Double::sum);
        }

        Map<String, Double> preStats = stats.getStats();
        double result;
        Map<Long, String> statIdToNameMap = allStats.stream()
                .collect(Collectors.toMap(Stat::getId, Stat::getName));

        for (Long statId : totalIncreases.keySet()) {
            String statName = statIdToNameMap.get(statId);
            if (statName != null && preStats.containsKey(statName)) {
                result = preStats.get(statName) + totalIncreases.get(statId);
                preStats.replace(statName, Math.round(result * 100.0)/100.0);
            }
        }
        return stats;
    }

    public Armor getArmor(Long id){
        return armorDAO.findById(id).get();
    }

    public List<Armor> getAllArmors(){
        return armorDAO.findAll();
    }
}
