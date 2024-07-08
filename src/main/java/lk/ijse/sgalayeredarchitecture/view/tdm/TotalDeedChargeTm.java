package lk.ijse.sgalayeredarchitecture.view.tdm;

public class TotalDeedChargeTm {
    private String deedId;
    private double TotalCharge;

    public TotalDeedChargeTm() {
    }

    public TotalDeedChargeTm(String deedId, double totalCharge) {
        this.deedId = deedId;
        TotalCharge = totalCharge;
    }

    public String getDeedId() {
        return deedId;
    }

    public void setDeedId(String deedId) {
        this.deedId = deedId;
    }

    public double getTotalCharge() {
        return TotalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        TotalCharge = totalCharge;
    }

    @Override
    public String toString() {
        return "TotalDeedChargeTm{" +
                "deedId='" + deedId + '\'' +
                ", TotalCharge=" + TotalCharge +
                '}';
    }
}
