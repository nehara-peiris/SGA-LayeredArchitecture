package lk.ijse.sgalayeredarchitecture.view.tdm;

public class SalaryTm {
    private String lawyerId;
    private double totalSalary;

    public SalaryTm() {
    }

    public SalaryTm(String lawyerId, double totalSalary) {
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
        return "SalaryTm{" +
                "lawyerId='" + lawyerId + '\'' +
                ", totalSalary=" + totalSalary +
                '}';
    }
}
