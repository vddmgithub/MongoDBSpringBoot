package com.pluralsight.aircraft;

import com.pluralsight.aircraft.com.aircraft.FlightInformation;
import com.pluralsight.aircraft.queries.FlightInformationQueries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.System.out;

//@Component
public class ApplicationRunner implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    public ApplicationRunner(MongoTemplate template){
        this.mongoTemplate = template;
    }

    @Override
    public void run(String... args) throws Exception {
        FlightInformationQueries flightInformationQueries = new FlightInformationQueries(this.mongoTemplate);
        /*FlightInformation flightInformation = createFlightInformation();
        mongoTemplate.save(flightInformation);*/
        try {
            out.println("Inside application command line runner");
            List<FlightInformation> flightInformationList = flightInformationQueries.findAll("flightType", 0, 3);
            // This gives a total of 3 documents.
            // Even though DB has 4 document one of the document doesn't have flightType.
            out.println(flightInformationList.size());


            flightInformationList = flightInformationQueries.findAll("id", 0, 10);
            // This gives a total of 4 documents.
            // As all documents have id
            out.println("Number of documents having id field: "+flightInformationList.size());

            out.println("Number of international flights: "+flightInformationQueries.countByInternational());

            // List of flights by its departure
            out.println("Number of flight with departures at Bangalore: "+flightInformationQueries.findByDeparture("Bangalore").size());

            out.println("Number flights by duration between :"+flightInformationQueries.findByDurationBetween(19, 90).size());

            out.println("Sub documents quering...: ");

            out.println("Number of Flights with Aircraft model being Latest Model: "+flightInformationQueries.findFlightsWithModel("Latest Model").size());

        }catch (Exception ex){
            out.println("Caught exception");
            out.println(ex);
        }
    }
}
