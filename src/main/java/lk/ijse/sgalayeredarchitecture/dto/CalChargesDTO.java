package lk.ijse.sgalayeredarchitecture.dto;

import lk.ijse.sgalayeredarchitecture.entity.Payment;

import java.io.Serializable;
import java.util.ArrayList;

public class CalChargesDTO <T> implements Serializable{

    private Payment payment;
    private ArrayList<T> ChargeList;


    public CalChargesDTO() {
    }

    public CalChargesDTO(Payment payment, ArrayList<T> chargeList) {
        this.payment = payment;
        ChargeList = chargeList;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ArrayList<T> getChargeList() {
        return ChargeList;
    }

    public void setChargeList(ArrayList<T> chargeList) {
        ChargeList = chargeList;
    }

    @Override
    public String toString() {
        return "CalChargesDTO{" +
                "payment=" + payment +
                ", ChargeList=" + ChargeList +
                '}';
    }
}
