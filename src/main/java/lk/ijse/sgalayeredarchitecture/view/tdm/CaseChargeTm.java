package lk.ijse.sgalayeredarchitecture.view.tdm;



import java.sql.Date;

public class CaseChargeTm {
    private String CCPayId;
    private String caseId;
    private String lawyerId;
    private String chargeId;
    private Date date;
    private double amount;
    private String clientId;

    public CaseChargeTm() {}

    public CaseChargeTm(String CCPayId, String caseId, String lawyerId, String chargeId, Date date, double amount, String clientId) {
        this.CCPayId = CCPayId;
        this.caseId = caseId;
        this.lawyerId = lawyerId;
        this.chargeId = chargeId;
        this.date = date;
        this.amount = amount;
        this.clientId = clientId;
    }

    public String getCCPayId() {
        return CCPayId;
    }

    public void setCCPayId(String CCPayId) {
        this.CCPayId = CCPayId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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
        return "CaseChargeTm{" +
                "CCPayId='" + CCPayId + '\'' +
                ", caseId='" + caseId + '\'' +
                ", lawyerId='" + lawyerId + '\'' +
                ", chargeId='" + chargeId + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
