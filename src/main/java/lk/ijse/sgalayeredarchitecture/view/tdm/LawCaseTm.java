package lk.ijse.sgalayeredarchitecture.view.tdm;

import java.sql.Date;

public class LawCaseTm {
    private String lawyerId;
    private String CaseId;
    private Date date;

    public LawCaseTm() {
    }

    public LawCaseTm(String lawyerId, String caseId, Date date) {
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
        return "LawCaseTm{" +
                "lawyerId='" + lawyerId + '\'' +
                ", CaseId='" + CaseId + '\'' +
                ", date=" + date +
                '}';
    }
}
