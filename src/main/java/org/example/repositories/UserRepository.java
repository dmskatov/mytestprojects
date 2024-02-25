package org.example.repositories;

import org.example.models.User;
import org.example.mappers.user.UserRowMapper;
import org.example.mappers.user.UserWithTicketRSE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;

    public void save(User user) {
        template.update("INSERT INTO ticket_purchase_system.users (login, password, full_name) VALUES (?, ?, ?)",
                user.getLogin(), user.getPassword(), user.getFullName());
    }

    public void buyTicket(Integer ticketId, Integer userId) {
        template.update("UPDATE tickets SET user_id = ? WHERE id = ?", userId, ticketId);
    }

    public boolean loginIsExist(String login) {
        User user = template.query("SELECT * FROM ticket_purchase_system.users WHERE login = ?",
                new Object[]{login}, new UserRowMapper()).stream().findAny().orElse(null);
        return user != null;
    }

    public User findTicketByUser(Integer id) {
        return template.query("SELECT users.id AS users_id, login, password, full_name, tickets.id AS ticket_id," +
                        "seat_number, price, date, time, itineraries.id AS itinerary_id, departure, destination," +
                        "duration, itineraries_transporters.transporters_id, name, phone_number," +
                        "FROM ticket_purchase_system.users JOIN tickets ON users.id = tickets.user_id" +
                        "AND JOIN itineraries ON tickets.itineraries_id = itineraries.id" +
                        "AND JOIN itineraries_transporters ON itineraries.id = itineraries_transporters.itinerary_id" +
                        "AND JOIN transporters ON itineraries_transporters.transporter_id = transporters.id" +
                        "WHERE users.id = ?",
                new Object[]{id}, new UserWithTicketRSE()).get(0);
    }

}
