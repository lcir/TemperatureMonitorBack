package cz.ptw.temperature.dao.impl;

import cz.ptw.temperature.dao.TemperatureDao;
import cz.ptw.temperature.domain.DateInterval;
import cz.ptw.temperature.domain.TemperatureInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: t945135
 * Date: 4.9.14
 * Time: 8:56
 */
@Repository("temperatureDao")
public class TemperatureDaoImpl implements TemperatureDao {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<TemperatureInformation> findTemperatureInformationForProbe(String[] probeIds) {
        Query searchTemperatureQuery = new Query(Criteria.where("probeId").in(probeIds));
        return mongoOperations.find(searchTemperatureQuery, TemperatureInformation.class);
    }

    @Override
    public List<TemperatureInformation> findTemperatureInformationForProbe(DateInterval dateInterval) {
        Query searchTemperatureQuery = new Query(Criteria.where("date").gt(dateInterval.getStartDateInterval().toDate()).lte(dateInterval.getFinishDateInterval().toDate()));
        return mongoOperations.find(searchTemperatureQuery, TemperatureInformation.class);
    }

    @Override
    public List<TemperatureInformation> findTemperatureInformationForProbe(String[] probeIds, DateInterval dateInterval) {
        Query searchTemperatureQuery = new Query(Criteria.where("probeId").in(probeIds).and("date").gt(dateInterval.getStartDateInterval().toDate()).lte(dateInterval.getFinishDateInterval().toDate()));

        return mongoOperations.find(searchTemperatureQuery, TemperatureInformation.class);
    }

    @Override
    public void saveTemperatureInformationToDb(TemperatureInformation temperatureInformation) {
        mongoOperations.save(temperatureInformation);
    }
}
