package Models;

public class Material extends Composant {
    private double quantity;
    private double unitCost;
    private double transportCost;
    private double qualityCoefficient;
    private int projectId;


    public Material(int id, String name, String typeComposant, double tauxTVA,
                    double quantity, double unitCost, double transportCost, double qualityCoefficient, int projectId) {
        super(id, name, typeComposant, tauxTVA);
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
        this.projectId = projectId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getQualityCoefficient() {
        return qualityCoefficient;
    }

    public void setQualityCoefficient(double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Material{" +
                "name='" + getName() + '\'' + // Use getName() from Composant
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                ", transportCost=" + transportCost +
                ", qualityCoefficient=" + qualityCoefficient +
                '}';
    }
}
