package org.example.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    private int id;

    private String login;

    private String password;

    private String fullName;

    private List<Ticket> tickets = new ArrayList<>();

    public void addTicketToList (Ticket ticket) {
        tickets.add(ticket);
    }
}
