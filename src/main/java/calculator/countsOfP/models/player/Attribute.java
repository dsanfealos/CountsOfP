package calculator.countsOfP.models.player;

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
    @OneToMany(mappedBy = "attribute", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StatIncrease> statIncreases = new ArrayList<>();

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
}
