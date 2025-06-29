package calculator.countsOfP.models.build;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NamedQuery(name = "Amulet.findAllNamed" , query = "SELECT a FROM Amulet a")
public class Amulet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "weight", columnDefinition = "Decimal(3,1)")
    private Double weight;

    @OneToMany(mappedBy = "amulet", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StatIncreaseAmu> statIncreaseAmus = new ArrayList<>();

    @OneToMany(mappedBy = "amulet", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AttributeIncreaseAmu> attributeIncreaseAmus = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public List<StatIncreaseAmu> getStatIncreaseAmus() {
        return statIncreaseAmus;
    }

    public void setStatIncreaseAmus(List<StatIncreaseAmu> statIncreaseAmus) {
        this.statIncreaseAmus = statIncreaseAmus;
    }

    public List<AttributeIncreaseAmu> getAttributeIncreaseAmus() {
        return attributeIncreaseAmus;
    }

    public void setAttributeIncreaseAmus(List<AttributeIncreaseAmu> attributeIncreaseAmus) {
        this.attributeIncreaseAmus = attributeIncreaseAmus;
    }
}
