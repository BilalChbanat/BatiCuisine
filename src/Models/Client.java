package Models;

public class Client {
    private int id;
    private String name;
    private String address;  // Corrected spelling
    private String phone;
    private boolean isProfessional;

    public Client(int id, String name, String address, String phone, boolean isProfessional) {
        this.id = id;
        this.name = name;
        this.address = address;  // Corrected spelling
        this.phone = phone;
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

    public String getAddress() {  // Corrected spelling
        return address;
    }

    public void setAddress(String address) {  // Corrected spelling
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
                ", address='" + address + '\'' +  // Corrected spelling
                ", telephone='" + telephone + '\'' +
                ", isProfessional=" + isProfessional +
                '}';
    }
}
