package lk.ijse.sgalayeredarchitecture.dto;

import java.io.Serializable;

public class CourtDTO implements Serializable {
    private String courtId;
    private String location;

    public CourtDTO() {
    }

    public CourtDTO(String courtId, String location) {
        this.courtId = courtId;
        this.location = location;
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CourtDTO{" +
                "courtId='" + courtId + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
