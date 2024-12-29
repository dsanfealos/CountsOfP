package calculator.countsOfP.api.models.body;

public class FullBuildBody {

    private AttributesBody initialAttributesBody;

    private AttributesBody finalAttributesBody;

    private Boolean isWeaponS;

    private StatsWeaponNBody statsWeaponNBody;

    private StatsWeaponSBody statsWeaponSBody;

    public FullBuildBody() {
    }

    public FullBuildBody(AttributesBody initialAttributesBody, AttributesBody finalAttributesBody,
                         Boolean isWeaponS, StatsWeaponNBody statsWeaponNBody, StatsWeaponSBody statsWeaponSBody) {
        this.initialAttributesBody = initialAttributesBody;
        this.finalAttributesBody = finalAttributesBody;
        this.isWeaponS = isWeaponS;
        this.statsWeaponNBody = statsWeaponNBody;
        this.statsWeaponSBody = statsWeaponSBody;
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
}
