package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.entities.Ticket;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserBookingServices {

    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper;
    private  static  final  String user_path = "C:\\Users\\hp\\OneDrive\\Desktop\\TicketBooking\\ticketBooking\\src\\main\\java\\org\\example\\localDB\\user.json";


    public UserBookingServices() throws IOException{
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadUsers();
    }
    private void loadUsers() throws IOException{
        userList = objectMapper.readValue(new File(user_path), new TypeReference<List<User>>() {});
    }



//    public Boolean loginUser(){
//        Optional<User> foundUser = userList.stream().filter(user1 -> {
//            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
//        }).findFirst();
//        return foundUser.isPresent();
//    }

    public boolean signUp(User user) throws IOException{
        try{
            Optional<User> foundUser = userList.stream().filter(user1 -> {
                return user1.getName().equals(user.getName());
            }).findFirst();

            if (foundUser.isPresent()) {
                // If a user with the same username exists,this will print an error message
                System.out.println("Username already taken!");
                return false;
            }

            userList.add(user);
            saveUserListToFile();
        }catch (Exception ex){
            System.out.println("saving user list to file failed " + ex.getMessage());
            return false;
        }
        return true;
    }


    private void saveUserListToFile() throws IOException{
        File usersFile = new File(user_path);
        objectMapper.writeValue(usersFile, userList);
    }
    public void fetchBookings(){
        System.out.println("Fetching your bookings");
        user.printTickets();
    }

    public Optional<User> getUserByUsername(String username){
        return userList.stream().filter(user -> user.getName().equals(username)).findFirst();
    }

    public void setUser(User user){
        this.user = user;
    }

    public boolean cancelBooking(String ticketId) throws IOException{
        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }
        boolean isRemoved =  user.getTicketBooked().removeIf(ticket -> ticket.getTicketId().equals(ticketId) );
        if(isRemoved) {
            saveUserListToFile();
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return true;
        }else{
            System.out.println("No ticket found with ID " + ticketId);
            return false;
        }
    }

    public List<Train> getTrains (String source, String destination) throws IOException {
        try{
            TrainServices trainService = new TrainServices();
            return trainService.searchTrains(source,destination);
        }catch (Exception ex){
            System.out.println("There is something wrong!");
            // return empty list if there is an exception
            return Collections.emptyList();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }


    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainServices trainService = new TrainServices();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);

                    train.setSeats(seats);
                    trainService.addTrain(train);

                    Ticket ticket = new Ticket();

                    ticket.setSource(train.getStations().get(0));
                    ticket.setDestination(train.getStations().get(0));
                    ticket.setTrain(train);
                    ticket.setUserId(user.getUserId());
                    ticket.setDateOfTravel("2021-09-01");
                    ticket.setTicketId(UserServiceUtil.generateTicketId());

                    user.getTicketBooked().add(ticket);

                    System.out.println("Seat booked successfully  !  ");

                    System.out.println(ticket.getTicketInfo());

                    saveUserListToFile();
                    return true; // Booking successful
                } else {
                    return false; // Execute when Seat is already booked
                }
            } else {
                return false; // Execute when Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

}
