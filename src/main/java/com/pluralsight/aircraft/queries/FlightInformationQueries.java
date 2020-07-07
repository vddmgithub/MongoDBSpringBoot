package com.pluralsight.aircraft.queries;

import com.pluralsight.aircraft.com.aircraft.FlightInformation;
import com.pluralsight.aircraft.com.aircraft.FlightType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightInformationQueries {

    private MongoTemplate mongoTemplate;

    public FlightInformationQueries(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * This method returns list of all files. We can even count the number of flights available in the Database
     */
    public List<FlightInformation> findAll(String byField,
                                           int pageNb,
                                           int pageSize){
        Query allPagedAndOrdered = new Query().with(Sort.by(Sort.Direction.ASC, byField))
                .with(PageRequest.of(pageNb, pageSize));


        return this.mongoTemplate.find(allPagedAndOrdered, FlightInformation.class);
    }

    /**
     * Find single document by ID
     */
    public FlightInformation findById(String id){
        return this.findById(id);
    }

    /**
     * Counts the number of internal flights.
     */
    public int countByInternational(){
        Query numberOfInternationalFlights = Query.query(Criteria.where("type").is(FlightType.International));
        return this.mongoTemplate.find(numberOfInternationalFlights, FlightInformation.class).size();
    }

    public List<FlightInformation> findByDeparture(String departure) {
        Query byDepature = new Query().addCriteria(Criteria.where("departure").is(departure));
        return mongoTemplate.find(byDepature, FlightInformation.class);
    }


    public List<FlightInformation> findByDurationBetween(int min, int max) {
        Query query = Query.query(
                Criteria.where("durationMin")
                        .gt(min)
                        .lt(max))
                .with(Sort.by(Sort.Direction.ASC, "durationMin"));
        return mongoTemplate.find(query, FlightInformation.class);
    }

    public List<FlightInformation> findFlightsWithModel(String latest_model) {
        Query query = Query.query(Criteria.where("aircraft.model").is(latest_model));
        return mongoTemplate.find(query, FlightInformation.class);
    }
}
