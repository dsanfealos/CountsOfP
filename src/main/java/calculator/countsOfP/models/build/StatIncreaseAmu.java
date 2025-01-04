package calculator.countsOfP.models.build;

import calculator.countsOfP.models.player.Stat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "stat_increase_amulet")
public class StatIncreaseAmu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer flatIncrease;

    @Column(name = "percentage_increase", columnDefinition = "Decimal(3,2)")
    private Double percentageIncrease;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "amulet_id", nullable = false)
    private Amulet amulet;

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

    public Amulet getAmulet() {
        return amulet;
    }

    public void setAmulet(Amulet amulet) {
        this.amulet = amulet;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public Integer getFlatIncrease() {
        return flatIncrease;
    }

    public void setFlatIncrease(Integer flatIncrease) {
        this.flatIncrease = flatIncrease;
    }

    public Double getPercentageIncrease() {
        return percentageIncrease;
    }

    public void setPercentageIncrease(Double percentageIncrease) {
        this.percentageIncrease = percentageIncrease;
    }
}
