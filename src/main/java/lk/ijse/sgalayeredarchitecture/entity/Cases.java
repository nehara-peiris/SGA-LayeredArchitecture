package lk.ijse.sgalayeredarchitecture.entity;

import java.sql.Date;

public class Cases {

    private String caseId;
    private String description;
    private String type;
    private Date date;
    private String clientId;

    public Cases() {
    }

    public Cases(String caseId, String description, String type, Date date, String clientId) {
        this.caseId = caseId;
        this.description = description;
        this.type = type;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Cases{" +
                "caseId='" + caseId + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
