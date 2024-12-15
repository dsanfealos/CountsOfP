package calculator.countsOfP.models.weapon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "weapon_upgrade_s")
public class WeaponUpgradeS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "current_lvl")
    private Long currentLevel;

    @Column(name = "ergo")
    private Long ergo;

    @Column(name = "material", length = 60)
    private String material;

    @Column(name = "quantity")
    private Integer quantity;

    @JsonIgnore
    @OneToMany(mappedBy = "currentLevel", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StatsWeaponS> statsWeaponS;

    public Long getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Long currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Long getErgo() {
        return ergo;
    }

    public void setErgo(Long ergo) {
        this.ergo = ergo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<StatsWeaponS> getStatsWeaponS() {
        return statsWeaponS;
    }

    public void setStatsWeaponS(List<StatsWeaponS> statsWeaponS) {
        this.statsWeaponS = statsWeaponS;
    }
}
