package lk.ijse.sgalayeredarchitecture.view.tdm;

public class CourtTm {
    private String courtId;
    private String location;

    public CourtTm() {
    }

    public CourtTm(String courtId, String location) {
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
        return "CourtTm{" +
                "courtId='" + courtId + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
