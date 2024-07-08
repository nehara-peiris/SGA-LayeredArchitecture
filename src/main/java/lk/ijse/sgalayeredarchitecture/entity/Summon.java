package lk.ijse.sgalayeredarchitecture.entity;

import java.sql.Date;

public class Summon {
    private String summonId;
    private String description;
    private String defendant;
    private String lawyerId;
    private Date date;

    public Summon() {
    }

    public Summon(String summonId, String description, String defendant, String lawyerId, Date date) {
        this.summonId = summonId;
        this.description = description;
        this.defendant = defendant;
        this.lawyerId = lawyerId;
        this.date = date;
    }

    public String getSummonId() {
        return summonId;
    }

    public void setSummonId(String summonId) {
        this.summonId = summonId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefendant() {
        return defendant;
    }

    public void setDefendant(String defendant) {
        this.defendant = defendant;
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

    @Override
    public String toString() {
        return "Summon{" +
                "summonId='" + summonId + '\'' +
                ", description='" + description + '\'' +
                ", defendant='" + defendant + '\'' +
                ", lawyerId='" + lawyerId + '\'' +
                ", date=" + date +
                '}';
    }
}
