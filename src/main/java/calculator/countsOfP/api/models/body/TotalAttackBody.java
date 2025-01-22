package calculator.countsOfP.api.models.body;

import calculator.countsOfP.models.weapon.Blade;
import calculator.countsOfP.models.weapon.Handle;
import calculator.countsOfP.models.weapon.StatsWeaponS;

public class TotalAttackBody {

    private Boolean isWeaponS;

    private StatsWeaponS weaponS;

    private Blade blade;

    private Handle handle;

    private Integer motivity;

    private Integer technique;

    private Integer advance;

    public TotalAttackBody() {
    }

    public TotalAttackBody(Boolean isWeaponS, StatsWeaponS weaponS, Blade blade, Handle handle, Integer motivity, Integer technique, Integer advance) {
        this.isWeaponS = isWeaponS;
        this.weaponS = weaponS;
        this.blade = blade;
        this.handle = handle;
        this.motivity = motivity;
        this.technique = technique;
        this.advance = advance;
    }

    public Boolean getIsWeaponS() {
        return isWeaponS;
    }

    public void setIsWeaponS(Boolean weaponS) {
        isWeaponS = weaponS;
    }

    public StatsWeaponS getWeaponS() {
        return weaponS;
    }

    public void setWeaponS(StatsWeaponS weaponS) {
        this.weaponS = weaponS;
    }

    public Blade getBlade() {
        return blade;
    }

    public void setBlade(Blade blade) {
        this.blade = blade;
    }

    public Handle getHandle() {
        return handle;
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }

    public Integer getMotivity() {
        return motivity;
    }

    public void setMotivity(Integer motivity) {
        this.motivity = motivity;
    }

    public Integer getTechnique() {
        return technique;
    }

    public void setTechnique(Integer technique) {
        this.technique = technique;
    }

    public Integer getAdvance() {
        return advance;
    }

    public void setAdvance(Integer advance) {
        this.advance = advance;
    }
}
