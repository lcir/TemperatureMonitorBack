package cz.ptw.temperature.manager;

import cz.ptw.temperature.domain.Phone;

import java.util.List;

/**
 * User: T945135
 * Date: 12.9.14
 * Time: 20:59
 */
public interface RegistrantManager {

    List<String> showAllAvailableRegistrantIds();

    void addNewMobilePhone(Phone mobilePhone);
}
