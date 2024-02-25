package org.example.services.impl;

import org.example.exceptions.CustomException;
import org.example.models.Ticket;
import org.example.utils.TicketPagAndFilter;
import org.example.repositories.TicketRepository;
import org.example.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void createTicket(Ticket ticket) throws CustomException {
        if (ticket == null) {
            throw new CustomException("Enter correct data for ticket");
        }
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getTicketsWithoutUser(TicketPagAndFilter ticketPagAndFilter) {
        return ticketRepository.findTicketsWithoutUser(ticketPagAndFilter);
    }
}
