package calculator.countsOfP.models.build;

import calculator.countsOfP.models.player.Attribute;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "attribute_increase_amulet")
public class AttributeIncreaseAmu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer flatIncrease;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "amulet_id", nullable = false)
    private Amulet amulet;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFlatIncrease() {
        return flatIncrease;
    }

    public void setFlatIncrease(Integer flatIncrease) {
        this.flatIncrease = flatIncrease;
    }

    public Amulet getAmulet() {
        return amulet;
    }

    public void setAmulet(Amulet amulet) {
        this.amulet = amulet;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
