package org.example.mappers.ticket;

import org.example.models.Itinerary;
import org.example.models.Ticket;
import org.example.models.Transporter;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsRSE implements ResultSetExtractor<List<Ticket>> {

    @Override
    public List<Ticket> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Ticket> tickets = new ArrayList<>();
        while (rs.next()) {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("ticket_id"));
            ticket.setDate(rs.getDate("date").toLocalDate());
            ticket.setTime(rs.getString("time"));
            ticket.setSeatNumber(rs.getInt("seat_number"));
            ticket.setPrice(rs.getInt("price"));

            Itinerary itinerary = new Itinerary();
            itinerary.setId(rs.getInt("itinerary_id"));
            itinerary.setDeparture(rs.getString("departure"));
            itinerary.setDestination(rs.getString("destination"));
            itinerary.setDuration(rs.getInt("duration"));

            Transporter transporter = new Transporter();
            transporter.setId(rs.getInt("transporters_id"));
            transporter.setName(rs.getString("name"));
            transporter.setPhoneNumber(rs.getString("phone_number"));
            itinerary.addTransporter(transporter);

            ticket.setItinerary(itinerary);
            tickets.add(ticket);
        }
        return tickets;
    }
}
