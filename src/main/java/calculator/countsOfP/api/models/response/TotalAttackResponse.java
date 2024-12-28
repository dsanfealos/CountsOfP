package calculator.countsOfP.api.models.response;

public class TotalAttackResponse {

    private String weaponName;

    private Integer basePhysicalAttack;

    private Integer baseElementalAttack;

    private Integer bonusPhysicalAttack;

    private Integer bonusElementalAttack;

    private Integer totalAttack;

    public TotalAttackResponse() {
    }

    public TotalAttackResponse(String weaponName, Integer basePhysicalAttack, Integer baseElementalAttack, Integer bonusPhysicalAttack, Integer bonusElementalAttack, Integer totalAttack) {
        this.weaponName = weaponName;
        this.basePhysicalAttack = basePhysicalAttack;
        this.baseElementalAttack = baseElementalAttack;
        this.bonusPhysicalAttack = bonusPhysicalAttack;
        this.bonusElementalAttack = bonusElementalAttack;
        this.totalAttack = totalAttack;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public Integer getBasePhysicalAttack() {
        return basePhysicalAttack;
    }

    public void setBasePhysicalAttack(Integer basePhysicalAttack) {
        this.basePhysicalAttack = basePhysicalAttack;
    }

    public Integer getBaseElementalAttack() {
        return baseElementalAttack;
    }

    public void setBaseElementalAttack(Integer baseElementalAttack) {
        this.baseElementalAttack = baseElementalAttack;
    }

    public Integer getBonusPhysicalAttack() {
        return bonusPhysicalAttack;
    }

    public void setBonusPhysicalAttack(Integer bonusPhysicalAttack) {
        this.bonusPhysicalAttack = bonusPhysicalAttack;
    }

    public Integer getBonusElementalAttack() {
        return bonusElementalAttack;
    }

    public void setBonusElementalAttack(Integer bonusElementalAttack) {
        this.bonusElementalAttack = bonusElementalAttack;
    }

    public Integer getTotalAttack() {
        return totalAttack;
    }

    public void setTotalAttack(Integer totalAttack) {
        this.totalAttack = totalAttack;
    }
}
