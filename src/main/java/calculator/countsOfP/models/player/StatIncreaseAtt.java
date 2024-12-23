package calculator.countsOfP.models.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "stat_increase")
public class StatIncreaseAtt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attribute_value")
    private Integer attributeValue;

    @Column(name = "increase", columnDefinition = "Decimal(5,2)")
    private Double increase;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "stat_id", nullable = false)
    private Stat stat;

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

    public Integer getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(Integer attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Double getIncrease() {
        return increase;
    }

    public void setIncrease(Double increase) {
        this.increase = increase;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
