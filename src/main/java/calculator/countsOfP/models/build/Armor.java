package calculator.countsOfP.models.build;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "armor")
public class Armor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight", columnDefinition = "Decimal(3,1)")
    private Double weight;

    @Column(name = "type")
    private Integer type;

    @OneToMany(mappedBy = "armor", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StatIncreaseArmor> statIncreaseArmors = new ArrayList<>();

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<StatIncreaseArmor> getStatIncreaseArmors() {
        return statIncreaseArmors;
    }

    public void setStatIncreaseArmors(List<StatIncreaseArmor> statIncreaseArmors) {
        this.statIncreaseArmors = statIncreaseArmors;
    }
}
