package cz.ptw.temperature.dao;

import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.dao.impl.RegistrantDaoImpl;
import cz.ptw.temperature.domain.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:25
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrantDaoTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private RegistrantDao registrantDao = new RegistrantDaoImpl();
    private Phone mockedMobilePhone;

    @Before
    public void setUp(){
        mockedMobilePhone = MockedData.initializeNewMobilePhone();
    }

    /**
     * Test to register new mobile phone
     */
    @Test
    public void registerNewMobilePhone(){

        registrantDao.addNewMobilePhone(mockedMobilePhone);
        verify(mongoOperations).save(mockedMobilePhone);
    }

    /**
     * Test to select all mobile phones
     */
    @Test
    public void showAllMobilePhones(){

        when(mongoOperations.findAll(Phone.class)).thenReturn(MockedData.initializeSomeMobilePhones());
        registrantDao.showAllMobilePhones();
        verify(mongoOperations).findAll(Phone.class);
    }

}

