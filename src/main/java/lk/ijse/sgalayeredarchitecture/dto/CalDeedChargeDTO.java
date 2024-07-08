package lk.ijse.sgalayeredarchitecture.dto;

import java.io.Serializable;
import java.util.List;

public class CalDeedChargeDTO implements Serializable {
    private PaymentDTO payment;
    private List<DeedChargeDTO> deedChargeList;

    public CalDeedChargeDTO() {
    }

    public CalDeedChargeDTO(PaymentDTO payment, List<DeedChargeDTO> deedChargeList) {
        this.payment = payment;
        this.deedChargeList = deedChargeList;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<DeedChargeDTO> getDeedChargeList() {
        return deedChargeList;
    }

    public void setDeedChargeList(List<DeedChargeDTO> deedChargeList) {
        this.deedChargeList = deedChargeList;
    }

    @Override
    public String toString() {
        return "CalDeedChargeDTO{" +
                "payment=" + payment +
                ", deedChargeList=" + deedChargeList +
                '}';
    }
}
