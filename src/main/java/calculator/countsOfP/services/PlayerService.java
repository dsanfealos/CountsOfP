package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.api.models.response.StatsResponse;
import calculator.countsOfP.models.build.Amulet;
import calculator.countsOfP.models.build.AttributeIncreaseAmu;
import calculator.countsOfP.models.build.StatIncreaseAmu;
import calculator.countsOfP.models.build.dao.AmuletDAO;
import calculator.countsOfP.models.build.dao.AttributeIncreaseAmuDAO;
import calculator.countsOfP.models.build.dao.StatIncreaseAmuDAO;
import calculator.countsOfP.models.player.Attribute;
import calculator.countsOfP.models.player.Stat;
import calculator.countsOfP.models.player.StatIncreaseAtt;
import calculator.countsOfP.models.player.dao.AttributeDAO;
import calculator.countsOfP.models.player.dao.LevelPDAO;
import calculator.countsOfP.models.player.dao.StatDAO;
import calculator.countsOfP.models.player.dao.StatIncreaseDAO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.LongStream;

@Service
public class PlayerService {

    private final LevelPDAO levelPDAO;
    private final StatIncreaseDAO statIncreaseDAO;
    private final AttributeDAO attributeDAO;
    private final StatDAO statDAO;
    private final AmuletDAO amuletDAO;
    private final AttributeIncreaseAmuDAO attributeIncreaseAmuDAO;
    private final StatIncreaseAmuDAO statIncreaseAmuDAO;


    public PlayerService(LevelPDAO levelPDAO, StatIncreaseDAO statIncreaseDAO, AttributeDAO attributeDAO, StatDAO statDAO, AmuletDAO amuletDAO, AttributeIncreaseAmuDAO attributeIncreaseAmuDAO, StatIncreaseAmuDAO statIncreaseAmuDAO) {
        this.levelPDAO = levelPDAO;
        this.statIncreaseDAO = statIncreaseDAO;
        this.attributeDAO = attributeDAO;
        this.statDAO = statDAO;
        this.amuletDAO = amuletDAO;
        this.attributeIncreaseAmuDAO = attributeIncreaseAmuDAO;
        this.statIncreaseAmuDAO = statIncreaseAmuDAO;
    }

    public Long costUpgradeLevelP(Long initial, Long destination){
        return LongStream.rangeClosed(initial, destination - 1)
                .mapToObj(levelPDAO::findById)
                .filter(Optional::isPresent)
                .mapToLong(optional -> optional.get().getErgo())
                .sum();
    }

    public Map<Long, Double> increaseStatsByAttribute(Long attributeId, Integer initialValue, Integer finalValue){
        //Get the List of increased stats
        Attribute attribute = attributeDAO.findById(attributeId).get();
        List<StatIncreaseAtt> list = statIncreaseDAO.findByAttributeAndAttributeValueBetween(attribute, initialValue, finalValue);

        //Get only the start and the end of each stat increase
        Map<Long, Double> increasedStats = new HashMap<>();
        Long statId = 0L;
        Double initialIncrease = 0.00;
        for (StatIncreaseAtt row:list){
            if (row.getAttributeValue() == initialValue){
                statId = row.getStat().getId();
                initialIncrease = row.getIncrease();
            } else if (row.getAttributeValue() == finalValue) {
                Double increase = row.getIncrease() - initialIncrease;
                increasedStats.put(statId, increase);
                statId = 0L;
                initialIncrease = 0.00;
            }
        }
        return increasedStats;
    }

    public StatsResponse simulateStats(AttributesBody initialBody, AttributesBody finalBody){
        StatsResponse response = new StatsResponse();
        response.setErgoCost(costUpgradeLevelP(initialBody.getLevel(), finalBody.getLevel()));

        List<Integer> initialAttribute = new ArrayList<>();
        initialAttribute.add(initialBody.getVitality());
        initialAttribute.add(initialBody.getVigor());
        initialAttribute.add(initialBody.getCapacity());
        initialAttribute.add(initialBody.getMotivity());
        initialAttribute.add(initialBody.getTechnique());
        initialAttribute.add(initialBody.getAdvance());

        List<Integer> finalAttribute = new ArrayList<>();
        finalAttribute.add(finalBody.getVitality());
        finalAttribute.add(finalBody.getVigor());
        finalAttribute.add(finalBody.getCapacity());
        Map<String, Integer> increasedAtt;
        if (finalBody.getAmuletIds() != null) {
            increasedAtt = increaseAttributesByAmulet(finalBody.getAmuletIds());

            finalAttribute.add(finalBody.getMotivity() + increasedAtt.get("Motivity"));
            finalAttribute.add(finalBody.getTechnique() + increasedAtt.get("Technique"));
            finalAttribute.add(finalBody.getAdvance() + increasedAtt.get("Advance"));
        }else {
            finalAttribute.add(finalBody.getMotivity());
            finalAttribute.add(finalBody.getTechnique());
            finalAttribute.add(finalBody.getAdvance());
        }

        Map<Long, Double> finalStats = increasedStatsMap(initialAttribute, finalAttribute, finalBody.getAmuletIds());
        response.setLevel(finalBody.getLevel());
        response.setVitality(finalAttribute.get(0));
        response.setVigor(finalAttribute.get(1));
        response.setCapacity(finalAttribute.get(2));
        response.setMotivity(finalAttribute.get(3));
        response.setTechnique(finalAttribute.get(4));
        response.setAdvance(finalAttribute.get(5));
        response.setStats(nameStatsMap(finalStats));
        return response;
    }

    public Map<Long, Double> increasedStatsMap(List<Integer> initialAttributes, List<Integer> finalAttributes, List<Long> amuletIds){
        Map<Long, Double> finalStats = new HashMap<>();
        List<Attribute> attributes = attributeDAO.findAll();
        //We get initial and desired attributes
        for (int index = 0; index<attributes.size(); index++){
            //We create a list of the stat increase for each attribute they are related to
            Attribute attribute = attributes.get(index);
            Integer initialAttribute = initialAttributes.get(index);
            Integer finalAttribute = finalAttributes.get(index);
            Map<Long, Double> increasedStats = increaseStatsByAttribute(attribute.getId(),
                    initialAttribute, finalAttribute);
            //For each altered stat, we add them to finalStats map, in order to stack up each
            // stat increase caused by different attributes.
            for (Long statId: increasedStats.keySet()){
                //If the stat already exists in the map, we add the additional increases.
                if (finalStats.containsKey(statId)){
                    Double result = increasedStats.get(statId) + finalStats.get(statId);
                    finalStats.replace(statId, finalStats.get(statId), result);
                }else {
                    //If not, we add the base value and the first increase.
                    Stat stat = statDAO.findById(statId).get();
                    Double baseIncrease = stat.getBaseValue() + statIncreaseDAO.
                            findByAttributeAndAttributeValueAndStat(attribute, finalAttribute, stat).getIncrease();
                    finalStats.put(statId, baseIncrease);
                }
            }
        }
        if (amuletIds != null) finalStats = increaseStatsByAmulet(finalStats, amuletIds);
        return finalStats;
    }

    public Map<Long, Double> increaseStatsByAmulet(Map<Long, Double> finalStats, List<Long> amuletIds){
        for (Long amuletId: amuletIds){
            for (Long stat: finalStats.keySet()){
                Optional<StatIncreaseAmu> increase = statIncreaseAmuDAO.findByAmuletAndStat(getAmulet(amuletId), statDAO.findById(stat).get());
                if (increase.isPresent()){
                    Double result = finalStats.get(stat) * (1 + increase.get().getPercentageIncrease()) + increase.get().getFlatIncrease();
                    BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
                    finalStats.replace(stat, bd.doubleValue());
                }
            }
        }
        return finalStats;
    }

    public Map<String, Double> nameStatsMap(Map<Long, Double> stats){
        Map<String, Double> namedStats = new LinkedHashMap<>();
        for (Long id: stats.keySet()){
            namedStats.put(statDAO.findById(id).get().getName(), stats.get(id));
        }
        return namedStats;
    }

    public Map<String,Integer> increaseAttributesByAmulet(List<Long> amuletIds){
        List<Attribute> attributes = attributeDAO.findAll();
        Map<String, Integer> increases = new LinkedHashMap<>();
        increases.put("Motivity", 0);
        increases.put("Technique", 0);
        increases.put("Advance", 0);
        for(Long amuletId:amuletIds){
            for (Attribute attribute:attributes){
                Amulet amulet = getAmulet(amuletId);
                Optional<AttributeIncreaseAmu> increase = attributeIncreaseAmuDAO.findByAmuletAndAttribute(amulet,attribute);
                if (increase.isPresent()){
                    String name = attribute.getName();
                    Integer result = attributeIncreaseAmuDAO.findByAmuletAndAttribute(amulet, attribute).get().getFlatIncrease();
                    increases.replace(name, increases.get(name) + result);
                }
            }
        }
        return increases;
    }

    public Amulet getAmulet(Long id){
        return amuletDAO.findById(id).get();
    }

    public List<Amulet> getAllAmulets(){
        return amuletDAO.findAll();
    }
}
