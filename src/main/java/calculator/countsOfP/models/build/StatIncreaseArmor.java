package calculator.countsOfP.models.build;

import calculator.countsOfP.models.player.Stat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "stat_increase_armor")
public class StatIncreaseArmor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flat_increase", columnDefinition = "Decimal(5,2)")
    private Double flatIncrease;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "armor_id", nullable = false)
    private Armor armor;

//    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "stat_id", nullable = false)
    private Stat stat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFlatIncrease() {
        return flatIncrease;
    }

    public void setFlatIncrease(Double flatIncrease) {
        this.flatIncrease = flatIncrease;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
