package Models;


public class Material {

    private String name;
    private double quantity;
    private double unitCost;
    private double transportCost;
    private double qualityCoefficient;

    public Material(String name, double quantity, double unitCost, double transportCost, double qualityCoefficient) {
        this.name = name;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Material{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                ", transportCost=" + transportCost +
                ", qualityCoefficient=" + qualityCoefficient +
                '}';
    }
}
