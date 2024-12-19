package calculator.countsOfP.api.models.response;

import calculator.countsOfP.models.weapon.StatsWeaponS;

public class StatsWeaponSResponse {

    private Long ergoCost;

    private StatsWeaponS stats;

    public StatsWeaponSResponse(Long ergoCost, StatsWeaponS stats) {
        this.ergoCost = ergoCost;
        this.stats = stats;
    }

    public StatsWeaponSResponse() {
    }

    public Long getErgoCost() {
        return ergoCost;
    }

    public void setErgoCost(Long ergoCost) {
        this.ergoCost = ergoCost;
    }

    public StatsWeaponS getStats() {
        return stats;
    }

    public void setStats(StatsWeaponS stats) {
        this.stats = stats;
    }
}
