package lk.ijse.sgalayeredarchitecture.view.tdm;


import com.jfoenix.controls.JFXButton;

public class ChargeCalculationTm {
    private String chargeId;
    private String description;
    private double amount;
    private JFXButton btnRemove;


    public ChargeCalculationTm() {
    }

    public ChargeCalculationTm(String chargeId, String description, double amount, JFXButton btnRemove) {
        this.chargeId = chargeId;
        this.description = description;
        this.amount = amount;
        this.btnRemove = btnRemove;
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

    public JFXButton getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(JFXButton btnRemove) {
        this.btnRemove = btnRemove;
    }

    @Override
    public String toString() {
        return "ChargeCalculationTm{" +
                "chargeId='" + chargeId + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", btnRemove=" + btnRemove +
                '}';
    }
}
