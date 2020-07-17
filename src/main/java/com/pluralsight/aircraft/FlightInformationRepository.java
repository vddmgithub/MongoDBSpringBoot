package com.pluralsight.aircraft;

import com.pluralsight.aircraft.com.aircraft.FlightInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightInformationRepository extends MongoRepository<FlightInformation, String> {
}
