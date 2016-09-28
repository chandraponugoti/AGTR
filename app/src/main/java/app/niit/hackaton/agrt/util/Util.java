package app.niit.hackaton.agrt.util;

import java.util.ArrayList;
import java.util.List;

import app.niit.hackaton.agrt.AgtrApplication;
import app.niit.hackaton.agrt.dto.Organisation;

/**
 * Created by ChandraSekharPonugot on 28-09-2016.
 */

public class Util {

    public static Organisation getOrganisation(Long id) {
        Organisation org = AgtrApplication.getDbHelper().getOrganisationById(id);
        return org;
    }

    public static List<String> getParentOrganisationList() {
        /** Items entered by the user is stored in this ArrayList variable */
        ArrayList<String> list = new ArrayList<String>();
        List<Organisation> orgList = AgtrApplication.getDbHelper().getParentOrganisationList();
        for (Organisation org : orgList) {
            list.add(org.getOrganisationName());
        }
        return list;
    }

    public static List<String> getOrganisationList() {
        /** Items entered by the user is stored in this ArrayList variable */
        ArrayList<String> list = new ArrayList<String>();
        List<Organisation> orgList = AgtrApplication.getDbHelper().getOrganisationList();
        for (Organisation org : orgList) {
            list.add(org.getOrganisationName());
        }
        return list;
    }
}
