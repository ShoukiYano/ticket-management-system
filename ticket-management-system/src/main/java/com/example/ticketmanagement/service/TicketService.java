package com.example.ticketmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.ticketmanagement.entity.Ticket;
import com.example.ticketmanagement.entity.User;
import com.example.ticketmanagement.repository.TicketRepository;
import com.example.ticketmanagement.repository.UserRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Ticket> findAllByOwner() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User owner = userRepository.findByUsername(username);
        return ticketRepository.findByOwner(owner);
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public void save(Ticket ticket) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User owner = userRepository.findByUsername(username);
        ticket.setOwner(owner);
        ticketRepository.save(ticket);
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
