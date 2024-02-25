package org.example.repositories;

import org.example.models.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItineraryRepository {

    @Autowired
    private JdbcTemplate template;

    public void save (Itinerary itinerary) {
        template.update("INSERT INTO itinerary (departure, destination, duration) VALUES (?, ?, ?)",
                itinerary.getDeparture(), itinerary.getDestination(), itinerary.getDuration());
    }
}
