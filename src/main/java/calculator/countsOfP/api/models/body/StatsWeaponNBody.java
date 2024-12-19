package calculator.countsOfP.api.models.body;

import calculator.countsOfP.models.weapon.Handle;

public class StatsWeaponNBody {

    private String bladeName;

    private Long currentLevel;

    private Handle handle;

    public StatsWeaponNBody(String bladeName, Long currentLevel, Handle handle) {
        this.bladeName = bladeName;
        this.currentLevel = currentLevel;
        this.handle = handle;
    }

    public StatsWeaponNBody() {
    }

    public String getBladeName() {
        return bladeName;
    }

    public void setBladeName(String bladeName) {
        this.bladeName = bladeName;
    }

    public Long getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Long currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Handle getHandle() {
        return handle;
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }
}
