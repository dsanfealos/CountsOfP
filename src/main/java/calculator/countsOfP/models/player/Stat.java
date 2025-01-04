package calculator.countsOfP.models.player;


import calculator.countsOfP.models.build.StatIncreaseAmu;
import calculator.countsOfP.models.build.StatIncreaseArmor;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "stat", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StatIncreaseAtt> statIncreaseAtts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "stat", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StatIncreaseAmu> statIncreaseAmus = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "stat", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StatIncreaseArmor> statIncreaseArmors = new ArrayList<>();

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

    public Double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(Double baseValue) {
        this.baseValue = baseValue;
    }

    public List<StatIncreaseAtt> getStatIncreaseAtts() {
        return statIncreaseAtts;
    }

    public void setStatIncreaseAtts(List<StatIncreaseAtt> statIncreaseAtts) {
        this.statIncreaseAtts = statIncreaseAtts;
    }

    public List<StatIncreaseAmu> getStatIncreaseAmus() {
        return statIncreaseAmus;
    }

    public void setStatIncreaseAmus(List<StatIncreaseAmu> statIncreaseAmus) {
        this.statIncreaseAmus = statIncreaseAmus;
    }

    public List<StatIncreaseArmor> getStatIncreaseArmors() {
        return statIncreaseArmors;
    }

    public void setStatIncreaseArmors(List<StatIncreaseArmor> statIncreaseArmors) {
        this.statIncreaseArmors = statIncreaseArmors;
    }
}
