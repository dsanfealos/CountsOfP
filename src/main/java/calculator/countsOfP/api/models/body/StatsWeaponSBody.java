package calculator.countsOfP.api.models.body;

public class StatsWeaponSBody {

    private String weaponName;

    private Long currentLevel;

    private Long finalLevel;

    private String modifier;

    public StatsWeaponSBody() {
    }

    public StatsWeaponSBody(String weaponName, Long currentLevel, Long finalLevel, String modifier) {
        this.weaponName = weaponName;
        this.currentLevel = currentLevel;
        this.finalLevel = finalLevel;
        this.modifier = modifier;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public Long getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Long currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Long getFinalLevel() {
        return finalLevel;
    }

    public void setFinalLevel(Long finalLevel) {
        this.finalLevel = finalLevel;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
