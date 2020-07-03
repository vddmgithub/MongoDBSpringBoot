package com.pluralsight.aircraft;

import com.pluralsight.aircraft.com.aircraft.FlightInformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    public ApplicationRunner(MongoTemplate template){
        this.mongoTemplate = template;
    }

    @Override
    public void run(String... args) throws Exception {
        FlightInformation flightInformation = new FlightInformation();
        try {
            System.out.println("Inside application command line runner");
            mongoTemplate.save(flightInformation);
        }catch (Exception ex){
            System.out.println("Caught exception");
            System.out.println(ex);
        }
    }
}
