package com.example.ticketmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ticketmanagement.entity.Ticket;
import com.example.ticketmanagement.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String listTickets(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        if (username.equals("admin")) {
            List<Ticket> tickets = ticketService.findAllTickets();
            model.addAttribute("tickets", tickets);
            return "admin_tickets";
        } else {
            List<Ticket> tickets = ticketService.findAllByOwner();
            model.addAttribute("tickets", tickets);
            return "tickets";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "create_ticket";
    }

    @PostMapping
    public String createTicket(@ModelAttribute Ticket ticket) {
        ticketService.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/{id}")
    public String showTicket(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.findById(id);
        if (ticket != null && ticket.getDescription() != null) {
            ticket.setDescription(ticket.getDescription().replace("\n", "<br>"));
        }
        model.addAttribute("ticket", ticket);
        return "ticket_detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.findById(id);
        model.addAttribute("ticket", ticket);
        return "edit_ticket";
    }

    @PostMapping("/{id}")
    public String updateTicket(@PathVariable Long id, @ModelAttribute Ticket ticket) {
        ticket.setId(id);
        ticketService.save(ticket);
        return "redirect:/tickets";
    }

    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);
        return "redirect:/tickets";
    }
}
