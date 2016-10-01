package app.niit.hackaton.agrt.dto;

/**
 * Created by ChandraSekharPonugot on 27-09-2016.
 */

public class Organisation {
    private Long id;

    private Long parentId;

    private String organisationName = "";

    private String branch = "";

    private String address = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*
    Donot change this to string as this is used for spinner to show organisation.
    If possible solution is implemented, then we can change.
     */
    @Override
    public String toString() {
        return this.organisationName;
    }

}
