package calculator.countsOfP.api.models.body;

public class StatsWeaponSBody {

    private String weaponName;

    private Long currentLevel;

    private Long finalLevel;

    public StatsWeaponSBody(Long currentLevel) {
    }

    public StatsWeaponSBody(String weaponName, Long currentLevel, Long finalLevel) {
        this.weaponName = weaponName;
        this.currentLevel = currentLevel;
        this.finalLevel = finalLevel;
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
}
