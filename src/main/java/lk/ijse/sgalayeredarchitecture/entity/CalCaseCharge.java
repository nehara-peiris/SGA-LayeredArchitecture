package lk.ijse.sgalayeredarchitecture.entity;

import java.util.List;

public class CalCaseCharge {
    private Payment payment;
    private List<CaseCharge> caseChargeList;

    public CalCaseCharge() {
    }

    public CalCaseCharge(Payment payment, List<CaseCharge> caseChargeList) {
        this.payment = payment;
        this.caseChargeList = caseChargeList;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<CaseCharge> getCaseChargeList() {
        return caseChargeList;
    }

    public void setCaseChargeList(List<CaseCharge> caseChargeList) {
        this.caseChargeList = caseChargeList;
    }

    @Override
    public String toString() {
        return "CalCaseCharge{" +
                "payment=" + payment +
                ", caseChargeList=" + caseChargeList +
                '}';
    }
}
