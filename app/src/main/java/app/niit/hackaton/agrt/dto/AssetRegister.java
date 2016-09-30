package app.niit.hackaton.agrt.dto;

/**
 * Created by ChandraSekharPonugot on 27-09-2016.
 */

public class AssetRegister {
    private Long id;
    private Asset asset = null;
    private Status status = Status.IN;
    private Long registerDate;
    private String empName = "";
    private Long latitude;
    private Long logitude;

    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Long registerDate) {
        this.registerDate = registerDate;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLogitude() {
        return logitude;
    }

    public void setLongitude(Long longitude) {
        this.logitude = longitude;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "AssetRegister{" +
                "id=" + id +
                ", asset=" + asset +
                ", status=" + status +
                ", registerDate=" + registerDate +
                ", empName='" + empName + '\'' +
                ", latitude=" + latitude +
                ", logitude=" + logitude +
                ", location='" + location + '\'' +
                '}';
    }

}
