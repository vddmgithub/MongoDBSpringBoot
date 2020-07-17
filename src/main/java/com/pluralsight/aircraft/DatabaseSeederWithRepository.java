package com.pluralsight.aircraft;

import com.pluralsight.aircraft.com.aircraft.Aircraft;
import com.pluralsight.aircraft.com.aircraft.FlightInformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class DatabaseSeederWithRepository implements CommandLineRunner {

    private FlightInformationRepository flightInformationRepository;

    public DatabaseSeederWithRepository(FlightInformationRepository flightInformationRepository){
        this.flightInformationRepository = flightInformationRepository;
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

        List<FlightInformation> flightInformationList = new ArrayList<>();
        flightInformationList.add(f1);
        flightInformationList.add(f2);
        System.out.println("Inserted documents: "+this.flightInformationRepository.insert(flightInformationList).size());

    }

    private void empty() {
        try {
            this.flightInformationRepository.deleteAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        empty();
        seed();
        sortByDepartureCity();
        printById("1");
        findAircraftsWithMoreThanSeats(70);
    }

    private void findAircraftsWithMoreThanSeats(int i) {
        System.out.println("Inside Custom Query Execution");
        System.out.println(this.flightInformationRepository.findByMinAircraftsByNbSeats(i).size());
    }

    private void printById(String s) {
        System.out.println(this.flightInformationRepository.findById(s));
    }

    private void sortByDepartureCity() {
        List<FlightInformation> flightInformations;
        flightInformations = this.flightInformationRepository.findAll(Sort.by("departureCity").ascending());
        System.out.println(flightInformations);
    }
}

