package calculator.countsOfP.api.models.response;

import calculator.countsOfP.models.weapon.Blade;
import calculator.countsOfP.models.weapon.Handle;

public class StatsWeaponNResponse {


    private String name;

    private String spritePath;

    private Double weight;

    private Integer totalAttack;

    private Character motivity;

    private Character technique;

    private Character advance;

    private Integer physicalAttack;

    private Integer elementalAttack;

    private Blade blade;

    private Handle handle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getTotalAttack() {
        return totalAttack;
    }

    public void setTotalAttack(Integer totalAttack) {
        this.totalAttack = totalAttack;
    }

    public Character getMotivity() {
        return motivity;
    }

    public void setMotivity(Character motivity) {
        this.motivity = motivity;
    }

    public Character getTechnique() {
        return technique;
    }

    public void setTechnique(Character technique) {
        this.technique = technique;
    }

    public Character getAdvance() {
        return advance;
    }

    public void setAdvance(Character advance) {
        this.advance = advance;
    }

    public Integer getPhysicalAttack() {
        return physicalAttack;
    }

    public void setPhysicalAttack(Integer physicalAttack) {
        this.physicalAttack = physicalAttack;
    }

    public Integer getElementalAttack() {
        return elementalAttack;
    }

    public void setElementalAttack(Integer elementalAttack) {
        this.elementalAttack = elementalAttack;
    }

    public Blade getBlade() {
        return blade;
    }

    public void setBlade(Blade blade) {
        this.blade = blade;
    }

    public Handle getHandle() {
        return handle;
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }
}
