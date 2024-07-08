package lk.ijse.sgalayeredarchitecture.dto;

import java.io.Serializable;
import java.sql.Date;

public class PaymentDTO implements Serializable {
    private String paymentId;
    private String lawyerId;
    private Date date;
    private double amount;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentId, String lawyerId, Date date, double amount) {
        this.paymentId = paymentId;
        this.lawyerId = lawyerId;
        this.date = date;
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(String lawyerId) {
        this.lawyerId = lawyerId;
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

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentId='" + paymentId + '\'' +
                ", lawyerId='" + lawyerId + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
