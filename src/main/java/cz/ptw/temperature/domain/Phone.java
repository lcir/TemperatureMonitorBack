package cz.ptw.temperature.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: T945135
 * Date: 12.9.14
 * Time: 21:27
 */
@Document(collection = "phones")
public class Phone {

    @Id
    private String mobileIdentification;

    public Phone(String mobileIdentification) {
        this.mobileIdentification = mobileIdentification;
    }

    public String getMobileIdentification() {
        return mobileIdentification;
    }

    public void setMobileIdentification(String mobileIdentification) {
        this.mobileIdentification = mobileIdentification;
    }
}
