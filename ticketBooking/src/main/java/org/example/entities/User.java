package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name ;
    private String password;
    private String hashedPassword;
    private List<Ticket> ticketBooked;
    private  String userId;



    public User(String name, String password, String hashedPassword, List<Ticket> ticketBooked, String userId) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketBooked = ticketBooked != null ? ticketBooked : Collections.emptyList();
        this.userId = userId;
    }

    public User(){};

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<Ticket> getTicketBooked() {
        return ticketBooked;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setTicketBooked(List<Ticket> ticketBooked) {
        this.ticketBooked = ticketBooked;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public  void printTickets(){
        if(ticketBooked.isEmpty()){
            System.out.println("No ticket Booked");
            return;
        }else {
            for(Ticket ticket : ticketBooked){
                System.out.println(ticket.getTicketInfo());
            }
        }
    }
}
