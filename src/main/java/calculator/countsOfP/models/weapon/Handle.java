package calculator.countsOfP.models.weapon;

import jakarta.persistence.*;

@Entity
@Table(name = "handle")
public class Handle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "sprite", length = 300)
    private String spritePath;

    @Column(name = "weight", columnDefinition = "Decimal(3,1)")
    private Double weight;

    @Column(name = "motivity")
    private Character motivity;

    @Column(name = "technique")
    private Character technique;

    @Column(name = "advance")
    private Character advance;

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Character getMotivity() {
        return motivity;
    }

    public void setMotivity(Character motivity) {
        this.motivity = motivity;
    }

    public Character getTechnique() {
        return technique;
    }

    public void setTechnique(Character technique) {
        this.technique = technique;
    }

    public Character getAdvance() {
        return advance;
    }

    public void setAdvance(Character advance) {
        this.advance = advance;
    }
}
