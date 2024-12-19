package calculator.countsOfP.api.models.response;

import java.util.Map;

public class StatsResponse {

    private Long level;

    private Integer vitality;

    private Integer vigor;

    private Integer capacity;

    private Integer motivity;

    private Integer technique;

    private Integer advance;

    private Map<String, Double> stats;

    private Long ergoCost;

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Integer getVitality() {
        return vitality;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }

    public Integer getVigor() {
        return vigor;
    }

    public void setVigor(Integer vigor) {
        this.vigor = vigor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getMotivity() {
        return motivity;
    }

    public void setMotivity(Integer motivity) {
        this.motivity = motivity;
    }

    public Integer getTechnique() {
        return technique;
    }

    public void setTechnique(Integer technique) {
        this.technique = technique;
    }

    public Integer getAdvance() {
        return advance;
    }

    public void setAdvance(Integer advance) {
        this.advance = advance;
    }

    public Map<String, Double> getStats() {
        return stats;
    }

    public void setStats(Map<String, Double> stats) {
        this.stats = stats;
    }

    public Long getErgoCost() {
        return ergoCost;
    }

    public void setErgoCost(Long ergoCost) {
        this.ergoCost = ergoCost;
    }
}
