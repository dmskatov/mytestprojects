package org.example.mappers.user;

import org.example.models.Itinerary;
import org.example.models.Ticket;
import org.example.models.Transporter;
import org.example.models.User;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserWithTicketRSE implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, User> users = new HashMap<>();
        while (rs.next()) {
            Integer userId = rs.getInt("users_id");
            User user = users.get(userId);
            if (user == null) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                users.put(userId, user);
            }

            int ticketId = rs.getInt("ticket_id");
            if (ticketId != 0) {
                Ticket ticket = new Ticket();
                ticket.setId(ticketId);
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
                transporter.setName("name");
                transporter.setPhoneNumber("phone_number");
                itinerary.addTransporter(transporter);

                ticket.setItinerary(itinerary);
                user.addTicketToList(ticket);
            }
        }
        return users.values().stream().toList();
    }
}
