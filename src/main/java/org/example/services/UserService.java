package org.example.services;

import org.example.exceptions.CustomException;
import org.example.models.User;

public interface UserService {

    void createUser (User user) throws CustomException;

    User getUserByLogin (String login) throws CustomException;

    void buyTicket (Integer ticketId) throws CustomException;

    User getUserTickets (Integer userId) throws CustomException;
}
