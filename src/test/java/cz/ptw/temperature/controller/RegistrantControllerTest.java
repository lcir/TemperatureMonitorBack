package cz.ptw.temperature.controller;

import cz.ptw.temperature.MockedData;
import cz.ptw.temperature.controllers.RegistrantController;
import cz.ptw.temperature.domain.Phone;
import cz.ptw.temperature.manager.RegistrantManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.mongodb.util.MyAsserts.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * User: t945135
 * Date: 3.9.14
 * Time: 19:25
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrantControllerTest {

    @Mock
    private RegistrantManager registrantManager;

    @InjectMocks
    private RegistrantController registrantController;
    private Phone mockedMobilePhone;

    @Before
    public void setUp() {
        mockedMobilePhone = MockedData.initializeNewMobilePhone();
    }

    /**
     * Test to add new phone
     */
    @Test
    public void addNewPhoneFromRequest() {

        registrantController.addNewPhone(mockedMobilePhone.getMobileIdentification());
        verify(registrantManager).addNewMobilePhone(any(Phone.class));
    }
}
