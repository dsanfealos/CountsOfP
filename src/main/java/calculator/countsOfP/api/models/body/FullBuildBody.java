package calculator.countsOfP.api.models.body;

import calculator.countsOfP.models.build.Armor;

import java.util.List;

public class FullBuildBody {

    private AttributesBody initialAttributesBody;

    private AttributesBody finalAttributesBody;

    private Boolean isWeaponS;

    private StatsWeaponNBody statsWeaponNBody;

    private StatsWeaponSBody statsWeaponSBody;

    private List<Long> armorPiecesIds;

    public FullBuildBody() {
    }

    public FullBuildBody(AttributesBody initialAttributesBody, AttributesBody finalAttributesBody,
                         Boolean isWeaponS, StatsWeaponNBody statsWeaponNBody, StatsWeaponSBody statsWeaponSBody, List<Long> armorPiecesIds) {
        this.initialAttributesBody = initialAttributesBody;
        this.finalAttributesBody = finalAttributesBody;
        this.isWeaponS = isWeaponS;
        this.statsWeaponNBody = statsWeaponNBody;
        this.statsWeaponSBody = statsWeaponSBody;
        this.armorPiecesIds = armorPiecesIds;
    }

    public AttributesBody getInitialAttributesBody() {
        return initialAttributesBody;
    }

    public void setInitialAttributesBody(AttributesBody initialAttributesBody) {
        this.initialAttributesBody = initialAttributesBody;
    }

    public AttributesBody getFinalAttributesBody() {
        return finalAttributesBody;
    }

    public void setFinalAttributesBody(AttributesBody finalAttributesBody) {
        this.finalAttributesBody = finalAttributesBody;
    }

    public Boolean getIsWeaponS() {
        return isWeaponS;
    }

    public void setIsWeaponS(Boolean weaponS) {
        isWeaponS = weaponS;
    }

    public StatsWeaponNBody getStatsWeaponNBody() {
        return statsWeaponNBody;
    }

    public void setStatsWeaponNBody(StatsWeaponNBody statsWeaponNBody) {
        this.statsWeaponNBody = statsWeaponNBody;
    }

    public StatsWeaponSBody getStatsWeaponSBody() {
        return statsWeaponSBody;
    }

    public void setStatsWeaponSBody(StatsWeaponSBody statsWeaponSBody) {
        this.statsWeaponSBody = statsWeaponSBody;
    }

    public List<Long> getArmorPiecesIds() {
        return armorPiecesIds;
    }

    public void setArmorPiecesIds(List<Long> armorPiecesIds) {
        this.armorPiecesIds = armorPiecesIds;
    }
}
