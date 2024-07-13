package lk.ijse.sgalayeredarchitecture.entity;


import java.util.ArrayList;

public class CalCharges <T>{
    private Payment payment;
    private ArrayList<T> ChargeList;

    public CalCharges() {
    }

    public CalCharges(Payment payment, ArrayList<T> chargeList) {
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
        return "CalCharges{" +
                "payment=" + payment +
                ", ChargeList=" + ChargeList +
                '}';
    }
}
