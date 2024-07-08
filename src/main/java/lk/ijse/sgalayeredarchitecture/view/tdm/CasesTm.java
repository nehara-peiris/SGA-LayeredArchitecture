package lk.ijse.sgalayeredarchitecture.view.tdm;

import java.sql.Date;

public class CasesTm {
    private String caseId;
    private String description;
    private Date date;
    private String type;
    private String clientId;

    public CasesTm() {
    }

    public CasesTm(String caseId, String description, Date date, String type, String clientId) {
        this.caseId = caseId;
        this.description = description;
        this.date = date;
        this.type = type;
        this.clientId = clientId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "CasesTm{" +
                "caseId='" + caseId + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
