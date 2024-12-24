package calculator.countsOfP.api.models.response;

import calculator.countsOfP.models.weapon.Blade;
import calculator.countsOfP.models.weapon.Handle;

import java.util.Map;

public class StatsWeaponNResponse {


    private String name;

    private Long ergoCost;

    private Map<String, Integer> materials;

    private Double weight;

    private Integer totalAttack;

    private Character motivity;

    private Character technique;

    private Character advance;

    private Integer physicalAttack;

    private Integer elementalAttack;

    private Blade blade;

    private Handle handle;

    public StatsWeaponNResponse() {
    }

    public StatsWeaponNResponse(String name, Long ergoCost, Map<String, Integer> materials, Double weight, Integer totalAttack, Character motivity, Character technique,
                                Character advance, Integer physicalAttack, Integer elementalAttack, Blade blade, Handle handle) {
        this.name = name;
        this.ergoCost = ergoCost;
        this.materials = materials;
        this.weight = weight;
        this.totalAttack = totalAttack;
        this.motivity = motivity;
        this.technique = technique;
        this.advance = advance;
        this.physicalAttack = physicalAttack;
        this.elementalAttack = elementalAttack;
        this.blade = blade;
        this.handle = handle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getErgoCost() {
        return ergoCost;
    }

    public void setErgoCost(Long ergoCost) {
        this.ergoCost = ergoCost;
    }

    public Map<String, Integer> getMaterials() {
        return materials;
    }

    public void setMaterials(Map<String, Integer> materials) {
        this.materials = materials;
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
