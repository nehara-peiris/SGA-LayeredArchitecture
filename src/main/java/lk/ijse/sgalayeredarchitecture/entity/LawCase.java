package lk.ijse.sgalayeredarchitecture.entity;

import java.sql.Date;

public class LawCase {
    private String lawyerId;
    private String CaseId;
    private Date date;

    public LawCase() {
    }

    public LawCase(String lawyerId, String caseId, Date date) {
        this.lawyerId = lawyerId;
        CaseId = caseId;
        this.date = date;
    }

    public String getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(String lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getCaseId() {
        return CaseId;
    }

    public void setCaseId(String caseId) {
        CaseId = caseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LawCase{" +
                "lawyerId='" + lawyerId + '\'' +
                ", CaseId='" + CaseId + '\'' +
                ", date=" + date +
                '}';
    }
}
