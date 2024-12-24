package calculator.countsOfP.services;

import calculator.countsOfP.api.models.body.AttributesBody;
import calculator.countsOfP.api.models.response.StatsResponse;
import calculator.countsOfP.models.player.Attribute;
import calculator.countsOfP.models.player.Stat;
import calculator.countsOfP.models.player.StatIncreaseAtt;
import calculator.countsOfP.models.player.dao.AttributeDAO;
import calculator.countsOfP.models.player.dao.LevelPDAO;
import calculator.countsOfP.models.player.dao.StatDAO;
import calculator.countsOfP.models.player.dao.StatIncreaseDAO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.LongStream;

@Service
public class PlayerService {

    private final LevelPDAO levelPDAO;
    private final StatIncreaseDAO statIncreaseDAO;
    private final AttributeDAO attributeDAO;
    private final StatDAO statDAO;


    public PlayerService(LevelPDAO levelPDAO, StatIncreaseDAO statIncreaseDAO, AttributeDAO attributeDAO, StatDAO statDAO) {
        this.levelPDAO = levelPDAO;
        this.statIncreaseDAO = statIncreaseDAO;
        this.attributeDAO = attributeDAO;
        this.statDAO = statDAO;
    }

    public Long costUpgradeLevelP(Long initial, Long destination){
        return LongStream.rangeClosed(initial, destination - 1)
                .mapToObj(levelPDAO::findById)
                .filter(Optional::isPresent)
                .mapToLong(optional -> optional.get().getErgo())
                .sum();
    }

    public Map<Long, Double> increaseAttribute(Long attributeId, Integer initialValue, Integer finalValue){
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

        response.setLevel(finalBody.getLevel());
        response.setVitality(finalBody.getVitality());
        response.setVigor(finalBody.getVigor());
        response.setCapacity(finalBody.getCapacity());
        response.setMotivity(finalBody.getMotivity());
        response.setTechnique(finalBody.getTechnique());
        response.setAdvance(finalBody.getAdvance());

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
        finalAttribute.add(finalBody.getMotivity());
        finalAttribute.add(finalBody.getTechnique());
        finalAttribute.add(finalBody.getAdvance());

        Map<Long, Double> finalStats = increasedStatsMap(initialAttribute, finalAttribute);
        response.setStats(nameStatsMap(finalStats));
        return response;
    }

    public Map<Long, Double> increasedStatsMap(List<Integer> initialAttributes, List<Integer> finalAttributes){
        Map<Long, Double> finalStats = new HashMap<>();
        List<Attribute> attributes = attributeDAO.findAll();
        //We get initial and desired attributes
        for (int index = 0; index<attributes.size(); index++){
            //We create a list of the stat increase for each attribute they are related to
            Attribute attribute = attributes.get(index);
            Integer initialAttribute = initialAttributes.get(index);
            Integer finalAttribute = finalAttributes.get(index);
            Map<Long, Double> increasedStats = increaseAttribute(attribute.getId(),
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
        return finalStats;
    }

    public Map<String, Double> nameStatsMap(Map<Long, Double> stats){
        Map<String, Double> namedStats = new LinkedHashMap<>();
        for (Long id: stats.keySet()){
            namedStats.put(statDAO.findById(id).get().getName(), stats.get(id));
        }
        return namedStats;
    }
}
