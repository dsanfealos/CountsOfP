package calculator.countsOfP.api.models.response;

import calculator.countsOfP.models.build.Armor;

import java.util.List;

public class FullBuildResponse {

    private StatsResponse stats;

    private TotalAttackResponse attackResponse;

    private Boolean isWeaponS;

    private StatsWeaponNResponse statsWeaponN;

    private StatsWeaponSResponse statsWeaponS;

    private List<Armor> armorPieces;

    private Double currentWeight;

    public FullBuildResponse() {
    }

    public FullBuildResponse(StatsResponse stats, TotalAttackResponse attackResponse, Boolean isWeaponS, StatsWeaponNResponse statsWeaponN, StatsWeaponSResponse statsWeaponS, List<Armor> armorPieces, Double currentWeight) {
        this.stats = stats;
        this.attackResponse = attackResponse;
        this.isWeaponS = isWeaponS;
        this.statsWeaponN = statsWeaponN;
        this.statsWeaponS = statsWeaponS;
        this.armorPieces = armorPieces;
        this.currentWeight = currentWeight;
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

    public List<Armor> getArmorPieces() {
        return armorPieces;
    }

    public void setArmorPieces(List<Armor> armorPieces) {
        this.armorPieces = armorPieces;
    }

    public Double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Double currentWeight) {
        this.currentWeight = currentWeight;
    }
}
