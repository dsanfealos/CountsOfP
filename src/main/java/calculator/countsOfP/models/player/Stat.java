package calculator.countsOfP.models.player;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stat")
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sprite", length = 300)
    private String spritePath;

    @OneToMany(mappedBy = "stat", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StatIncrease> statIncreases = new ArrayList<>();

    @Column(name = "base_value", columnDefinition = "Decimal(5,2)")
    private Double baseValue;

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

    public List<StatIncrease> getStatIncreases() {
        return statIncreases;
    }

    public void setStatIncreases(List<StatIncrease> statIncreases) {
        this.statIncreases = statIncreases;
    }

    public Double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(Double baseValue) {
        this.baseValue = baseValue;
    }
}
