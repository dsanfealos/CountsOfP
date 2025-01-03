package calculator.countsOfP.api.models.body;

import calculator.countsOfP.models.build.Amulet;

import java.util.List;

public class AttributesBody {

    private Long level;

    private Integer vitality;

    private Integer vigor;

    private Integer capacity;

    private Integer motivity;

    private Integer technique;

    private Integer advance;

    private List<Amulet> amulets;

    public AttributesBody() {
    }

    public AttributesBody(Long level, Integer vitality, Integer vigor, Integer capacity, Integer motivity, Integer technique, Integer advance, List<Amulet> amulets) {
        this.level = level;
        this.vitality = vitality;
        this.vigor = vigor;
        this.capacity = capacity;
        this.motivity = motivity;
        this.technique = technique;
        this.advance = advance;
        this.amulets = amulets;
    }

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

    public List<Amulet> getAmulets() {
        return amulets;
    }

    public void setAmulets(List<Amulet> amulets) {
        this.amulets = amulets;
    }
}
