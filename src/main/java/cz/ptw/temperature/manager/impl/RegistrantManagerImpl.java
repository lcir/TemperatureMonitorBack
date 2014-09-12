package cz.ptw.temperature.manager.impl;

import cz.ptw.temperature.dao.RegistrantDao;
import cz.ptw.temperature.domain.Phone;
import cz.ptw.temperature.manager.RegistrantManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: T945135
 * Date: 12.9.14
 * Time: 21:22
 */
@Service("registrantManager")
public class RegistrantManagerImpl implements RegistrantManager {

    @Autowired
    private RegistrantDao registrantDao;

    @Override
    public List<String> showAllAvailableRegistrantIds() {

        List<String> listOfMobileList = new ArrayList<>();
        registrantDao.showAllMobilePhones().forEach((registrant) -> listOfMobileList.add(registrant.getMobileIdentification()));
        return listOfMobileList;
    }

    @Override
    public void addNewMobilePhone(Phone mobilePhone) {
        registrantDao.addNewMobilePhone(mobilePhone);
    }
}
