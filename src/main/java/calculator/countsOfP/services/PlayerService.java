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

    public Map<Long, Double> increaseStatsByAttribute(Long attributeId, int finalValue){
        Attribute attribute = attributeDAO.findById(attributeId).get();
        Map<Long, Double> increasedStats = new HashMap<>();
        List<StatIncreaseAtt> list = statIncreaseDAO.findByAttributeAndAttributeValue(attribute, finalValue);

        for (StatIncreaseAtt row: list){
            increasedStats.put(row.getStat().getId(), row.getIncrease());
        }
        return increasedStats;
    }

    public StatsResponse simulateStats(AttributesBody initialBody, AttributesBody finalBody){
        StatsResponse response = new StatsResponse();
        response.setErgoCost(costUpgradeLevelP(initialBody.getLevel(), finalBody.getLevel()));

        int bodySize = 6;
        int[] finalAttribute = new int[bodySize];
        finalAttribute[0] = finalBody.getVitality();
        finalAttribute[1] = finalBody.getVigor();
        finalAttribute[2] = finalBody.getCapacity();
        Map<String, Integer> increasedAtt;
        if (finalBody.getAmuletIds() != null) {
            increasedAtt = increaseAttributesByAmulet(finalBody.getAmuletIds());

            finalAttribute[3] = finalBody.getMotivity() + increasedAtt.get("Motivity");
            finalAttribute[4] = finalBody.getTechnique() + increasedAtt.get("Technique");
            finalAttribute[5] = finalBody.getAdvance() + increasedAtt.get("Advance");
        }else {
            finalAttribute[3] = finalBody.getMotivity();
            finalAttribute[4] = finalBody.getTechnique();
            finalAttribute[5] = finalBody.getAdvance();
        }

        Map<Long, Double> finalStats = increasedStatsMap(finalAttribute, finalBody.getAmuletIds());
        response.setLevel(finalBody.getLevel());
        response.setVitality(finalAttribute[0]);
        response.setVigor(finalAttribute[1]);
        response.setCapacity(finalAttribute[2]);
        response.setMotivity(finalAttribute[3]);
        response.setTechnique(finalAttribute[4]);
        response.setAdvance(finalAttribute[5]);
        response.setStats(nameStatsMap(finalStats));
        return response;
    }

    public Map<Long, Double> increasedStatsMap(int[] finalAttributes, List<Long> amuletIds) {
        Map<Long, Double> finalStats = new HashMap<>();
        List<Attribute> attributes = attributeDAO.findAll();
        List<Stat> statList = statDAO.findAll();

        for (Stat stat : statList) {
            finalStats.put(stat.getId(), stat.getBaseValue());
        }

        for (int index = 0; index < attributes.size(); index++) {
            Attribute attribute = attributes.get(index);
            int finalAttribute = finalAttributes[index];
            Map<Long, Double> increasedStats = increaseStatsByAttribute(attribute.getId(), finalAttribute);

            Map<Long, Double> finalStats1 = finalStats;
            increasedStats.forEach((statId, increaseValue) -> finalStats1.merge(statId, increaseValue, Double::sum));
        }

        if (amuletIds != null) {
            finalStats = increaseStatsByAmulet(finalStats, amuletIds);
        }

        return finalStats;
    }

    public Map<Long, Double> increaseStatsByAmulet(Map<Long, Double> finalStats, List<Long> amuletIds){
        double result;

        List<Amulet> amulets = new ArrayList<>();
        amuletIds.forEach(id -> amulets.add(getAmulet(id)));
        List<Stat> stats = new ArrayList<>();
        finalStats.keySet().forEach(id -> stats.add(statDAO.findById(id).get()));
        List<StatIncreaseAmu> allIncreases = statIncreaseAmuDAO.findByAmuletsAndStats(amulets, stats);
        for (StatIncreaseAmu increase : allIncreases){
            long statId = increase.getStat().getId();
            double flatIncrease = increase.getFlatIncrease();
            double percentageIncrease = increase.getPercentageIncrease();
            result = finalStats.get(statId) * (1 + percentageIncrease) + flatIncrease;
            finalStats.replace(increase.getStat().getId(), Math.round(result * 100.0)/100.0);
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
        for(Long amuletId : amuletIds){
            for (Attribute attribute : attributes){
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
