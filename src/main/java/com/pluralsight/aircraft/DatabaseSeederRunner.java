package com.pluralsight.aircraft;

import com.mongodb.client.result.DeleteResult;
import com.pluralsight.aircraft.com.aircraft.Aircraft;
import com.pluralsight.aircraft.com.aircraft.FlightInformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
@Order(1)
public class DatabaseSeederRunner implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    public DatabaseSeederRunner(MongoTemplate template){
        this.mongoTemplate = template;
    }

    @Override
    public void run(String... args) throws Exception {
        empty();
        seed();
    }

    private void seed() {
        FlightInformation f1 = FlightInformation.builder()
                                .aircraft(new Aircraft("Indigo", 300))
                                .departureDate(LocalDate.now().plusDays(2))
                                .destinationCity("Paris")
                                .departureCity("Bangalore")
                                .durationMin(300)
                                .id("1")
                                .isDelayed(true)
                                .build();

        FlightInformation f2 = FlightInformation.builder()
                .aircraft(new Aircraft("Indigo", 300))
                .departureDate(LocalDate.now().plusDays(3000))
                .destinationCity("Bangalore")
                .departureCity("Paris")
                .durationMin(300)
                .id("2")
                .isDelayed(false)
                .build();
        // insertIndividually(f1);
        // insertIndividually(f2);
        bulkInsert(List.of(f1, f2));
        // BulkUpdates
        markAllFlightsAsDelayedForDestination("Bangalore");
    }

    // Bulk Updates
    private void markAllFlightsAsDelayedForDestination(String destination) {
        Query query = Query.query(Criteria.where("destinationCity").is("Bangalore"));
        Update setDelayed = Update.update("isDelayed", true);

        this.mongoTemplate.updateMulti(query, setDelayed, FlightInformation.class);
    }

    // Bulk insertions
    private void bulkInsert(List<FlightInformation> list) {
        this.mongoTemplate.insertAll(list);
    }

    private void insertIndividually(FlightInformation f) {
        // One by one insertion
        this.mongoTemplate.insert(f);
    }

    private void empty() {
        try {
            DeleteResult flights = this.mongoTemplate.remove(new Query(),
                    FlightInformation.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
