package org.example.repositories;

import org.example.models.Itinerary;
import org.example.models.Transporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransporterRepository {

    @Autowired
    private JdbcTemplate template;

    public void save (Transporter transporter) {
        template.update("INSERT INTO transporters (name, phone_number) VALUES (?, ?)",
                transporter.getName(), transporter.getPhoneNumber());

        Integer transporterId = template.queryForObject("SELECT id FROM transporters" +
                "ORDER BY transporters.id LIMIT 1", Integer.class);
        for (Itinerary itinerary : transporter.getItineraries()) {
            template.update("INSERT INTO itineraries_transporters (itinerary_id, transporter_id)" +
                    "VALUES (?, ?)", itinerary.getId(), transporterId);
        }
    }
}
