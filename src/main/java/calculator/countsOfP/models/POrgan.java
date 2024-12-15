package calculator.countsOfP.models;

import jakarta.persistence.*;

@Entity
@Table(name = "p_organ")
public class POrgan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quartzs")
    private Integer quartzs;

    @Column(name = "level")
    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuartzs() {
        return quartzs;
    }

    public void setQuartzs(Integer quartzs) {
        this.quartzs = quartzs;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
