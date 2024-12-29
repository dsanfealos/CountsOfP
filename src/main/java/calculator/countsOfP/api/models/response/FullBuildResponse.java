package calculator.countsOfP.api.models.response;

public class FullBuildResponse {

    private StatsResponse stats;

    private TotalAttackResponse attackResponse;

    private Boolean isWeaponS;

    private StatsWeaponNResponse statsWeaponN;

    private StatsWeaponSResponse statsWeaponS;

    public FullBuildResponse() {
    }

    public FullBuildResponse(StatsResponse stats, TotalAttackResponse attackResponse, Boolean isWeaponS, StatsWeaponNResponse statsWeaponN, StatsWeaponSResponse statsWeaponS) {
        this.stats = stats;
        this.attackResponse = attackResponse;
        this.isWeaponS = isWeaponS;
        this.statsWeaponN = statsWeaponN;
        this.statsWeaponS = statsWeaponS;
    }

    public StatsResponse getStats() {
        return stats;
    }

    public void setStats(StatsResponse stats) {
        this.stats = stats;
    }

    public TotalAttackResponse getAttackResponse() {
        return attackResponse;
    }

    public void setAttackResponse(TotalAttackResponse attackResponse) {
        this.attackResponse = attackResponse;
    }

    public Boolean getIsWeaponS() {
        return isWeaponS;
    }

    public void setIsWeaponS(Boolean weaponS) {
        isWeaponS = weaponS;
    }

    public StatsWeaponNResponse getStatsWeaponN() {
        return statsWeaponN;
    }

    public void setStatsWeaponN(StatsWeaponNResponse statsWeaponN) {
        this.statsWeaponN = statsWeaponN;
    }

    public StatsWeaponSResponse getStatsWeaponS() {
        return statsWeaponS;
    }

    public void setStatsWeaponS(StatsWeaponSResponse statsWeaponS) {
        this.statsWeaponS = statsWeaponS;
    }
}
