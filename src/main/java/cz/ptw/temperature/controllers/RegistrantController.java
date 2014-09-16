package cz.ptw.temperature.controllers;

import cz.ptw.temperature.domain.Phone;
import cz.ptw.temperature.manager.RegistrantManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: T945135
 * Date: 12.9.14
 * Time: 22:26
 */
@Controller
public class RegistrantController {

    @Autowired
    private RegistrantManager registrantManager;

    @RequestMapping(value = "/rest/phone/add", method = RequestMethod.GET, params = {"phoneId"})
    public void addNewPhone(@RequestParam("phoneId") String mobileIdentification) {
        registrantManager.addNewMobilePhone(new Phone(mobileIdentification));
    }
}
