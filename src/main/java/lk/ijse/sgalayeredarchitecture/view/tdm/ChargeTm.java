package lk.ijse.sgalayeredarchitecture.view.tdm;

public class ChargeTm {
    private String chargeId;
    private String description;
    private double amount;

    public ChargeTm() {
    }

    public ChargeTm(String chargeId, String description, double amount) {
        this.chargeId = chargeId;
        this.description = description;
        this.amount = amount;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ChargeTm{" +
                "chargeId='" + chargeId + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
