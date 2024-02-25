package org.example.services.impl;

import org.example.exceptions.CustomException;
import org.example.models.User;
import org.example.repositories.TicketRepository;
import org.example.repositories.UserRepository;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    List<User> userList = new ArrayList<>();


    @Override
    public void createUser(User user) throws CustomException {
//        boolean loginIsExist = userRepository.loginIsExist(user.getLogin());
//        if (loginIsExist) {
//            throw new CustomException("Login is exist, please, use other login");
//        }
//        userList.add(user);
        userRepository.save(user);
    }

    @Override
    public User getUserByLogin(String login) throws CustomException {
        User user = userRepository.findUserByLogin(login);
        if (user == null) {
                throw new CustomException("User not found, please, enter correct login");
        }
        return user;
    }

    @Override
    public void buyTicket(Integer ticketId) throws CustomException {
        if (!ticketRepository.ticketIdIsExist(ticketId) || ticketRepository.findUserById(ticketId) != null) {
            throw new CustomException("Enter correct values for ticket");
        }
        for (User user : userList) {
            userRepository.buyTicket(ticketId, user.getId());
        }
    }

    @Override
    public User getUserTickets(Integer userId) throws CustomException {
        User user = userRepository.findTicketsByUser(userId);
        if (user == null) {
                throw new CustomException("User not found, please, enter correct user id");
        }
        return user;
    }
}
