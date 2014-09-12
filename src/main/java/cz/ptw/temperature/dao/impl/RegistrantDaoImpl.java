package cz.ptw.temperature.dao.impl;

import cz.ptw.temperature.dao.RegistrantDao;
import cz.ptw.temperature.domain.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: T945135
 * Date: 12.9.14
 * Time: 22:16
 */
@Repository("registrantDao")
public class RegistrantDaoImpl implements RegistrantDao {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<Phone> showAllMobilePhones() {
        return mongoOperations.findAll(Phone.class);
    }

    @Override
    public void addNewMobilePhone(Phone phone) {
        mongoOperations.save(phone);
    }
}
