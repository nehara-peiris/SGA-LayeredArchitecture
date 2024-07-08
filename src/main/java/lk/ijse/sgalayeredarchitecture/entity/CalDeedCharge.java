package lk.ijse.sgalayeredarchitecture.entity;

import java.util.List;

public class CalDeedCharge {
    private Payment payment;
    private List<DeedCharge> deedChargeList;

    public CalDeedCharge() {
    }

    public CalDeedCharge(Payment payment, List<DeedCharge> deedChargeList) {
        this.payment = payment;
        this.deedChargeList = deedChargeList;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<DeedCharge> getDeedChargeList() {
        return deedChargeList;
    }

    public void setDeedChargeList(List<DeedCharge> deedChargeList) {
        this.deedChargeList = deedChargeList;
    }

    @Override
    public String toString() {
        return "CalDeedCharge{" +
                "payment=" + payment +
                ", deedChargeList=" + deedChargeList +
                '}';
    }
}
