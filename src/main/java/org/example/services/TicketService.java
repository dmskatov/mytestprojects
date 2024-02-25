package org.example.services;

import org.example.exceptions.CustomException;
import org.example.models.Ticket;
import org.example.utils.TicketPagAndFilter;

import java.util.List;

public interface TicketService {

    void createTicket (Ticket ticket) throws CustomException;

    List<Ticket> getTicketsWithoutUser (TicketPagAndFilter ticketPagAndFilter);
}
