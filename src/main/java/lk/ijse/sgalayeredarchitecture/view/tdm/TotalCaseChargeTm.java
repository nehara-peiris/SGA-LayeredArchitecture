package lk.ijse.sgalayeredarchitecture.view.tdm;

public class TotalCaseChargeTm {
    private String caseId;
    private double TotalCharge;

    public TotalCaseChargeTm() {
    }

    public TotalCaseChargeTm(String caseId, double totalCharge) {
        this.caseId = caseId;
        TotalCharge = totalCharge;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public double getTotalCharge() {
        return TotalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        TotalCharge = totalCharge;
    }

    @Override
    public String toString() {
        return "TotalCaseChargeTm{" +
                "caseId='" + caseId + '\'' +
                ", TotalCharge=" + TotalCharge +
                '}';
    }
}
