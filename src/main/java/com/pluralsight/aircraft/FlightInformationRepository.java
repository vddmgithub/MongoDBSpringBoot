package com.pluralsight.aircraft;

import com.pluralsight.aircraft.com.aircraft.FlightInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightInformationRepository extends MongoRepository<FlightInformation, String> {

    List<FlightInformation> findByDepartureCityAndDestinationCity(String departure, String destination);

    @Query("{'aircraft.nseats': {$gt: 70}}")
    List<FlightInformation> findByMinAircraftsByNbSeats(int minNbSeats);

}
