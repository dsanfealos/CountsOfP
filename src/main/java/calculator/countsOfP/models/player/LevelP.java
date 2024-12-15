package calculator.countsOfP.models.player;

import jakarta.persistence.*;

@Entity
@Table(name = "level_p")
public class LevelP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long level;

    @Column(name = "ergo")
    private Long ergo;

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getErgo() {
        return ergo;
    }

    public void setErgo(Long ergo) {
        this.ergo = ergo;
    }
}
