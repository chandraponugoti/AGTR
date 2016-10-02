package app.niit.hackaton.agrt.util;

import java.util.List;

import app.niit.hackaton.agrt.AgtrApplication;
import app.niit.hackaton.agrt.dto.Asset;
import app.niit.hackaton.agrt.dto.AssetRegister;
import app.niit.hackaton.agrt.dto.Organisation;
import app.niit.hackaton.agrt.dto.Role;
import app.niit.hackaton.agrt.dto.User;

/**
 * Created by ChandraSekharPonugot on 28-09-2016.
 */

public class Util {

    public static Organisation getOrganisation(Long id) {
        return AgtrApplication.getDbHelper().getOrganisationById(id);
    }

    public static Organisation getOrganisation(String name) {
        return AgtrApplication.getDbHelper().getOrganisationByName(name);
    }

    public static Asset getAsset(Long id) {
        return AgtrApplication.getDbHelper().getAssetById(id);
    }

    public static List<Organisation> getParentOrganisationList() {
        return AgtrApplication.getDbHelper().getParentOrganisationList();
    }

    public static List<Organisation> getOrganisationList() {
        return AgtrApplication.getDbHelper().getOrganisationList();
    }

    public static List<AssetRegister> getAssetRegistryList() {
        return AgtrApplication.getDbHelper().getAssetsListFromRegister();
    }

    public static Asset getAssetByScanCodeAndType(String scancode, String scantype) {
        return AgtrApplication.getDbHelper().getAssetByScanCodeAndType(scancode, scantype);
    }

    public static List<Role> getRoles(Long orgId) {
        return AgtrApplication.getDbHelper().getRolesByOrganisationId(orgId);
    }

    public static Role getRolesById(Long roleId) {
        return AgtrApplication.getDbHelper().getRolesById(roleId);
    }

    public static User getUserProfileByUserNameAndPassword(String username, String password) {
        return AgtrApplication.getDbHelper().getUserProfileByUserNameAndPassword(username, password);
    }

    public static Boolean isOrganisationCreated() {
        return AgtrApplication.getDbHelper().isOrganisationCreated();
    }
}
