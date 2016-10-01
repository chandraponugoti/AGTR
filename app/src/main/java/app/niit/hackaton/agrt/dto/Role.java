package app.niit.hackaton.agrt.dto;

/**
 * Created by ChandraSekharPonugot on 27-09-2016.
 */

public class Role {

    private Long id;

    private String roleName = "";

    private Organisation org = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Organisation getOrg() {
        return org;
    }

    public void setOrg(Organisation org) {
        this.org = org;
    }

    /*
       Donot change this toString as this is used for spinner to show role.
       If possible solution is implemented, then we can change.
    */
    @Override
    public String toString() {
        return this.roleName;
    }
}
