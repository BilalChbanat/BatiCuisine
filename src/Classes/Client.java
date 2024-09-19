package Classes;

public class Client {
    private int id;
    private String name;
    private String adress;
    private String telephone;
    private boolean isProfessional;

    public Client(int id, String name, String adress, String telephone, boolean isProfessional) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.telephone = telephone;
        this.isProfessional = isProfessional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isProfessional() {
        return isProfessional;
    }

    public void setProfessional(boolean professional) {
        isProfessional = professional;
    }

    @Override
    public String toString() {
        return "\nClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", telephone='" + telephone + '\'' +
                ", isProfessional=" + isProfessional +
                '}';
    }
}
