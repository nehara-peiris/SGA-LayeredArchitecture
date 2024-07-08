package lk.ijse.sgalayeredarchitecture.dto;

import java.io.Serializable;

public class SalaryDTO implements Serializable {
    private String lawyerId;
    private double totalSalary;

    public SalaryDTO() {
    }

    public SalaryDTO(String lawyerId, double totalSalary) {
        this.lawyerId = lawyerId;
        this.totalSalary = totalSalary;
    }

    public String getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(String lawyerId) {
        this.lawyerId = lawyerId;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    @Override
    public String toString() {
        return "SalaryDTO{" +
                "lawyerId='" + lawyerId + '\'' +
                ", totalSalary=" + totalSalary +
                '}';
    }
}
