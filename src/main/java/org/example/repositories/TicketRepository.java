package org.example.repositories;

import org.example.mappers.ticket.TicketRowMapper;
import org.example.mappers.ticket.TicketsRSE;
import org.example.models.Ticket;
import org.example.utils.TicketPagAndFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TicketRepository {

    @Autowired
    private JdbcTemplate template;

    public void save (Ticket ticket) {
        template.update("INSERT INTO (date, time, seat_number, price, itinerary_id) VALUES (?, ?, ?, ?, ?)",
                ticket.getDate(), ticket.getTime(), ticket.getSeatNumber(),
                ticket.getPrice(), ticket.getItinerary().getId());
    }

    public boolean ticketIdIsExist (Integer id) {
        Ticket ticket = template.queryForObject("SELECT * FROM tickets WHERE id = ?",
                new Object[]{id}, new TicketRowMapper());
        return ticket != null;
    }

    public Integer findUserById (Integer id) {
        return template.queryForObject("SELECT user_id FROM tickets WHERE id = ?",
                new Object[]{id}, Integer.class);
    }

    private Map<String, Object> getSqlRequest(TicketPagAndFilter ticketPagAndFilter) {
        Map<String, Object> sqlRequest = new HashMap<>();
        List<Object> pagAndFilterList = new ArrayList<>();
        String selectRequest = "SELECT tickets.id AS ticket_id, date, time, seat_number, price, user_id," +
                "itineraries.id AS itinerary_id, departure, destination, duration," +
                "itineraries_transporters.transporter_id AS transporters_id, name, phone_number FROM tickets," +
                "JOIN itineraries ON tickets.itinerary_id AND JOIN itineraries_transporters ON" +
                "itineraries.id = itineraries_transporters.itinerary_id AND JOIN transporters ON" +
                "itineraries_transporters ON itineraries_transporters.transporter_id = transporters.id" +
                "WHERE user_id IS NULL AND";
        String conditionsForSelectRequest = null;
        if (ticketPagAndFilter.getDate() != null) {
            conditionsForSelectRequest = " date = ? AND";
            pagAndFilterList.add(ticketPagAndFilter.getDate());
        }
        if (ticketPagAndFilter.getTime() != null) {
            conditionsForSelectRequest = " time = ? AND";
            pagAndFilterList.add(ticketPagAndFilter.getTime());
        }
        if (ticketPagAndFilter.getDeparture() != null) {
            conditionsForSelectRequest = " departure = ? AND";
            pagAndFilterList.add(ticketPagAndFilter.getDeparture());
        }
        if (ticketPagAndFilter.getDestination() != null) {
            conditionsForSelectRequest = " destination = ? AND";
            pagAndFilterList.add(ticketPagAndFilter.getDestination());
        }
        if (ticketPagAndFilter.getTransporterName() != null) {
            conditionsForSelectRequest = " name = ? AND";
            pagAndFilterList.add(ticketPagAndFilter.getTransporterName());
        }
        String endForSelectRequest = " ORDER BY ticket_id LIMIT " + ticketPagAndFilter.getSize();
        String finalSelectRequest = selectRequest + conditionsForSelectRequest + endForSelectRequest;
        sqlRequest.put("sqlRequest", finalSelectRequest);
        sqlRequest.put("filters", pagAndFilterList);
        return sqlRequest;
    }

    public List<Ticket> findTicketsWithoutUser(TicketPagAndFilter ticketPagAndFilter) {
        Map<String, Object> sqlRequest = getSqlRequest(ticketPagAndFilter);
        String selectRequest = (String) sqlRequest.get("sqlRequest");
        Object[] filters = (Object[]) sqlRequest.get("filters");
        return template.query(selectRequest, filters, new TicketsRSE());
    }
}
