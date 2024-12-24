package calculator.countsOfP.services;

import calculator.countsOfP.exceptions.NotEnoughModulesException;
import calculator.countsOfP.models.dao.POrganDAO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FullBuildService {

    private POrganDAO pOrganDAO;

    public FullBuildService(POrganDAO pOrganDAO) {
        this.pOrganDAO = pOrganDAO;
    }

    public Integer costUpgradeArm(Integer initialLevel, Integer finalLevel){
        Map<Integer, Integer> costs = Map.of(0,0,1,1,2,2,3,3);
        Integer components = 0;
        for (int index = initialLevel + 1; index <= finalLevel; index++){
            components += costs.get(index);
        }
        return components;
    }

    public Integer costQuartzTotal(Map<Integer, Integer> modules){
        Integer cost = 0;
        Integer totalModules = 0;
        for (Integer level:modules.keySet()){
            Integer moduleQuantity = modules.get(level);
            totalModules += moduleQuantity;
            Integer minimumTotalModules = 2*(level-1);
//            if (totalModules<minimumTotalModules){
//                throw new NotEnoughModulesException("It is needed at least 2 modules for each P Organ level.");
//            }
            cost += moduleQuantity * pOrganDAO.findById(Long.valueOf(level)).get().getQuartzs();
        }
        return cost;
    }
}
