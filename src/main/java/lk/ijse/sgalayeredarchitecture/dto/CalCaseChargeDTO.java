package lk.ijse.sgalayeredarchitecture.dto;

import java.io.Serializable;
import java.util.List;

public class CalCaseChargeDTO implements Serializable {
    private PaymentDTO payment;
    private List<CaseChargeDTO> caseChargeList;

    public CalCaseChargeDTO() {
    }

    public CalCaseChargeDTO(PaymentDTO payment, List<CaseChargeDTO> caseChargeList) {
        this.payment = payment;
        this.caseChargeList = caseChargeList;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<CaseChargeDTO> getCaseChargeList() {
        return caseChargeList;
    }

    public void setCaseChargeList(List<CaseChargeDTO> caseChargeList) {
        this.caseChargeList = caseChargeList;
    }

    @Override
    public String toString() {
        return "CalCaseChargeDTO{" +
                "payment=" + payment +
                ", caseChargeList=" + caseChargeList +
                '}';
    }
}
