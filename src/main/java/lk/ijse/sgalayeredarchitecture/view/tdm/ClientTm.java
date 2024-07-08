package lk.ijse.sgalayeredarchitecture.view.tdm;

public class ClientTm {
    private String clientId;
    private String name;
    private String address;
    private String email;
    private int contact;
    private String LawyerID;

    public ClientTm() {
    }

    public ClientTm(String clientId, String name, String address, String email, int contact, String lawyerID) {
        this.clientId = clientId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
        LawyerID = lawyerID;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getLawyerID() {
        return LawyerID;
    }

    public void setLawyerID(String lawyerID) {
        LawyerID = lawyerID;
    }

    @Override
    public String toString() {
        return "ClientTm{" +
                "clientId='" + clientId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contact=" + contact +
                ", LawyerID='" + LawyerID + '\'' +
                '}';
    }
}
