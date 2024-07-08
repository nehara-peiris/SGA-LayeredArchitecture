package lk.ijse.sgalayeredarchitecture.dto;

import java.io.Serializable;
import java.sql.Date;

public class DeedDTO implements Serializable {
    private String deedId;
    private String description;
    private String type;
    private Date date;
    private String lawyerId;
    private String clientId;

    public DeedDTO() {
    }

    public DeedDTO(String deedId, String description, String type, Date date, String lawyerId, String clientId) {
        this.deedId = deedId;
        this.description = description;
        this.type = type;
        this.date = date;
        this.lawyerId = lawyerId;
        this.clientId = clientId;
    }

    public String getDeedId() {
        return deedId;
    }

    public void setDeedId(String deedId) {
        this.deedId = deedId;
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

    public String getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(String lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "DeedDTO{" +
                "deedId='" + deedId + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", lawyerId='" + lawyerId + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
