package calculator.countsOfP.models.weapon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "stats_weapon_s")
public class StatsWeaponS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "sprite", length = 300)
    private String spritePath;

    @Column(name = "weight", columnDefinition = "Decimal(3,1)")
    private Double weight;

    @Column(name = "total_attack")
    private Integer totalAttack;

    @Column(name = "motivity")
    private Character motivity;

    @Column(name = "technique")
    private Character technique;

    @Column(name = "advance")
    private Character advance;

    @Column(name = "physical_attack")
    private Integer physicalAttack;

    @Column(name = "elemental_attack")
    private Integer elementalAttack;

//    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "current_level", nullable = false)
    private WeaponUpgradeS currentLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public WeaponUpgradeS getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(WeaponUpgradeS currentLevel) {
        this.currentLevel = currentLevel;
    }
}
