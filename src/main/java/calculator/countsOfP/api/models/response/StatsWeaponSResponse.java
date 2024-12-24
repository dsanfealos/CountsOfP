package calculator.countsOfP.api.models.response;

import calculator.countsOfP.models.weapon.StatsWeaponS;

import java.util.Map;

public class StatsWeaponSResponse {

    private Long ergoCost;

    private Map<String, Integer> materials;

    private StatsWeaponS stats;

    public StatsWeaponSResponse(Long ergoCost, Map<String, Integer> materials, StatsWeaponS stats) {
        this.ergoCost = ergoCost;
        this.materials = materials;
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

    public Map<String, Integer> getMaterials() {
        return materials;
    }

    public void setMaterials(Map<String, Integer> materials) {
        this.materials = materials;
    }

    public StatsWeaponS getStats() {
        return stats;
    }

    public void setStats(StatsWeaponS stats) {
        this.stats = stats;
    }
}
