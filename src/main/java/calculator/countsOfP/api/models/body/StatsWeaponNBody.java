package calculator.countsOfP.api.models.body;

import calculator.countsOfP.models.weapon.Handle;

public class StatsWeaponNBody {

    private String bladeName;

    private Long currentLevel;

    private Long finalLevel;

    private Long handleId;

    private String modifier;

    public StatsWeaponNBody(String bladeName, Long currentLevel, Long finalLevel, Long handleId, String modifier) {
        this.bladeName = bladeName;
        this.currentLevel = currentLevel;
        this.finalLevel = finalLevel;
        this.handleId = handleId;
        this.modifier = modifier;
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

    public Long getFinalLevel() {
        return finalLevel;
    }

    public void setFinalLevel(Long finalLevel) {
        this.finalLevel = finalLevel;
    }

    public Long getHandleId() {
        return handleId;
    }

    public void setHandleId(Long handleId) {
        this.handleId = handleId;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
