package cz.ptw.temperature.dao;

import cz.ptw.temperature.domain.Phone;

import java.util.List;

/**
 * User: T945135
 * Date: 12.9.14
 * Time: 21:22
 */
public interface RegistrantDao {

    List<Phone> showAllMobilePhones();

    void addNewMobilePhone(Phone phone);
}
