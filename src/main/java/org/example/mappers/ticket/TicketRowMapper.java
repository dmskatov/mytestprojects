package org.example.mappers.ticket;

import org.example.models.Ticket;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TicketRowMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setDate(rs.getDate("date").toLocalDate());
        ticket.setTime(rs.getString("time"));
        ticket.setSeatNumber(rs.getInt("seat_number"));
        ticket.setPrice(rs.getInt("price"));
        return ticket;
    }
}
