package calculator.countsOfP.api.models.body;

public class TotalAttackBody {

    private Boolean isWeaponS;

    private Long weaponSId;

    private Long bladeId;

    private Long handleId;

    private Integer motivity;

    private Integer technique;

    private Integer advance;

    public TotalAttackBody() {
    }

    public TotalAttackBody(Boolean isWeaponS, Long weaponSId, Long bladeId, Long handleId, Integer motivity, Integer technique, Integer advance) {
        this.isWeaponS = isWeaponS;
        this.weaponSId = weaponSId;
        this.bladeId = bladeId;
        this.handleId = handleId;
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

    public Long getWeaponSId() {
        return weaponSId;
    }

    public void setWeaponSId(Long weaponSId) {
        this.weaponSId = weaponSId;
    }

    public Long getBladeId() {
        return bladeId;
    }

    public void setBladeId(Long bladeId) {
        this.bladeId = bladeId;
    }

    public Long getHandleId() {
        return handleId;
    }

    public void setHandleId(Long handleId) {
        this.handleId = handleId;
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
