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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
        Integer cost = 0;
        Integer totalModules = 0;
        for (Integer level:modules.keySet()){
            Integer moduleQuantity = modules.get(level);
            Integer minimumTotalModules = 2*(level-1);
            int nextLevel = level + 1;
            if (totalModules<minimumTotalModules){
                throw new NotEnoughModulesException();
            }
            totalModules += moduleQuantity;
            cost += moduleQuantity * pOrganDAO.findById(Long.valueOf(level)).get().getQuartzs();
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
            attackBody = new TotalAttackBody(true, weaponS.getStats().getId(),
                    null, null, stats.getMotivity(), stats.getTechnique(), stats.getAdvance());
        } else{
            weaponN = weaponService.upgradeLevelN(body.getStatsWeaponNBody());
            attackBody = new TotalAttackBody(false, null,
                    weaponN.getBlade().getId(), weaponN.getHandle().getId(), stats.getMotivity(), stats.getTechnique(), stats.getAdvance());

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
        Map<Long, Double> totalIncreases = new LinkedHashMap<>();
        for (Armor armor: armorPieces){
            for (Stat stat: allStats){
                Optional<StatIncreaseArmor> increaseByArmor = statIncreaseArmorDAO.findByArmorAndStat(armor, stat);
                if (increaseByArmor.isPresent()) {
                    if (totalIncreases.containsKey(stat.getId())) {
                        double result = totalIncreases.get(stat.getId()) + increaseByArmor.get().getFlatIncrease();
                        totalIncreases.replace(stat.getId(), result);
                    }else{
                        totalIncreases.put(stat.getId(),increaseByArmor.get().getFlatIncrease());
                    }
                }
            }
        }

//        armorPieces.stream()
//                .flatMap(armor -> allStats.stream()
//                        .filter(stat -> statIncreaseArmorDAO.findByArmorAndStat(armor, stat).isPresent())
//                        .filter(stat -> totalIncreases.containsKey(stat.getId()))
//                        .map(stat -> Map.entry(stat.getId(), totalIncreases.get(stat.getId()) + statIncreaseArmorDAO.findByArmorAndStat(armor,stat).get().getFlatIncrease())))
//                .forEach(entry -> totalIncreases.replace(entry.getKey(), entry.getValue()));
//

        Map<String, Double> preStats = stats.getStats();
//        for (Long statId: totalIncreases.keySet()){
//            for (String statName: preStats.keySet()){
//                if (Objects.equals(statDAO.findById(statId).get().getName(), statName)){
//                    double result = preStats.get(statName) + totalIncreases.get(statId);
//                    BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
//                    preStats.replace(statName, bd.doubleValue());
//                }
//            }
//        }

        totalIncreases.keySet().stream()
                .flatMap(statId -> preStats.keySet().stream()
                        .filter(statName -> Objects.equals(statDAO.findById(statId).get().getName(), statName))
                        .map(statName -> Map.entry(statName, preStats.get(statName) + totalIncreases.get(statId))))
                .forEach(entry -> preStats.replace(entry.getKey(), new BigDecimal(entry.getValue()).setScale(2, RoundingMode.HALF_UP).doubleValue()));
        stats.setStats(preStats);
        return stats;
    }

    public Armor getArmor(Long id){
        return armorDAO.findById(id).get();
    }

    public List<Armor> getAllArmors(){
        return armorDAO.findAll();
    }
}
