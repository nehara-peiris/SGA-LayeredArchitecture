package lk.ijse.sgalayeredarchitecture.entity;

public class Salary {
    private String lawyerId;
    private double totalSalary;

    public Salary() {
    }

    public Salary(String lawyerId, double totalSalary) {
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
        return "Salary{" +
                "lawyerId='" + lawyerId + '\'' +
                ", totalSalary=" + totalSalary +
                '}';
    }
}
