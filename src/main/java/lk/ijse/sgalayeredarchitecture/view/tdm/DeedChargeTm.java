package lk.ijse.sgalayeredarchitecture.view.tdm;

import java.sql.Date;
public class DeedChargeTm {
    private String DCPayId;
    private String deedId;
    private String lawyerId;
    private String chargeId;
    private Date date;
    private double amount;
    private String clientId;

    public DeedChargeTm() {
    }

    public DeedChargeTm(String DCPayId, String deedId, String lawyerId, String chargeId, Date date, double amount, String clientId) {
        this.DCPayId = DCPayId;
        this.deedId = deedId;
        this.lawyerId = lawyerId;
        this.chargeId = chargeId;
        this.date = date;
        this.amount = amount;
        this.clientId = clientId;
    }

    public String getDCPayId() {
        return DCPayId;
    }

    public void setDCPayId(String DCPayId) {
        this.DCPayId = DCPayId;
    }

    public String getDeedId() {
        return deedId;
    }

    public void setDeedId(String deedId) {
        this.deedId = deedId;
    }

    public String getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(String lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "DeedChargeTm{" +
                "DCPayId='" + DCPayId + '\'' +
                ", deedId='" + deedId + '\'' +
                ", lawyerId='" + lawyerId + '\'' +
                ", chargeId='" + chargeId + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
