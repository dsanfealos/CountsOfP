package calculator.countsOfP.services;

import calculator.countsOfP.models.player.Attribute;
import calculator.countsOfP.models.player.StatIncrease;
import calculator.countsOfP.models.player.dao.AttributeDAO;
import calculator.countsOfP.models.player.dao.LevelPDAO;
import calculator.countsOfP.models.player.dao.StatIncreaseDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.LongStream;

@Service
public class PlayerService {

    private LevelPDAO levelPDAO;
    private StatIncreaseDAO statIncreaseDAO;
    private AttributeDAO attributeDAO;


    public PlayerService(LevelPDAO levelPDAO, StatIncreaseDAO statIncreaseDAO, AttributeDAO attributeDAO) {
        this.levelPDAO = levelPDAO;
        this.statIncreaseDAO = statIncreaseDAO;
        this.attributeDAO = attributeDAO;
    }

    public Long costUpgradeLevelP(Long initial, Long destination){
        return LongStream.rangeClosed(initial, destination - 1)
                .mapToObj(levelPDAO::findById)
                .filter(Optional::isPresent)
                .mapToLong(optional -> optional.get().getErgo())
                .sum();
    }

    public Map<Long, Double> increaseAttribute(Long attributeId, Integer initialValue, Integer finalValue){
        //get the List of increased stats
        Attribute attribute = attributeDAO.findById(attributeId).get();
        List<StatIncrease> list = statIncreaseDAO.findByAttributeAndAttributeValueBetween(attribute, initialValue, finalValue);

        for (StatIncrease row:list){
            Long statId = row.getStat().getId();
            Double increase = row.getIncrease();
        }
        return null;
    }

}
