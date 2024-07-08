package lk.ijse.sgalayeredarchitecture.dto;

import java.io.Serializable;

public class ChargeDTO implements Serializable {
    private String chargeId;
    private String description;
    private double amount;

    public ChargeDTO() {
    }

    public ChargeDTO(String chargeId, String description, double amount) {
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
        return "ChargeDTO{" +
                "chargeId='" + chargeId + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
