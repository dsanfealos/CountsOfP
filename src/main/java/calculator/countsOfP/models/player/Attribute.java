package calculator.countsOfP.models.player;

import calculator.countsOfP.models.build.AttributeIncreaseAmu;
import calculator.countsOfP.models.weapon.Scaling;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attribute")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sprite", length = 300)
    private String spritePath;

    @JsonIgnore
    @OneToMany(mappedBy = "attribute", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StatIncreaseAtt> statIncreaseAtts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "attribute", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AttributeIncreaseAmu> attributeIncreaseAmus = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "attribute", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Scaling> scalings = new ArrayList<>();

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

    public List<StatIncreaseAtt> getStatIncreaseAtts() {
        return statIncreaseAtts;
    }

    public void setStatIncreaseAtts(List<StatIncreaseAtt> statIncreaseAtts) {
        this.statIncreaseAtts = statIncreaseAtts;
    }

    public List<AttributeIncreaseAmu> getAttributeIncreaseAmus() {
        return attributeIncreaseAmus;
    }

    public void setAttributeIncreaseAmus(List<AttributeIncreaseAmu> attributeIncreaseAmus) {
        this.attributeIncreaseAmus = attributeIncreaseAmus;
    }

    public List<Scaling> getScalings() {
        return scalings;
    }

    public void setScalings(List<Scaling> scalings) {
        this.scalings = scalings;
    }
}
