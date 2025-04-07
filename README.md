#🚆✨ Ticket Booking System – 
Your Gateway to Seamless Train Travel!
Imagine booking a train ticket as smooth as scrolling your feed — that’s what we built.
A fast, simple, secure, and interactive CLI-based Train Ticket Booking System powered by Java & JSON.

# 🚀 Features
🌐 Interactive CLI Booking
Log in, search trains, view available seats, and book your ticket — all from your terminal.

🔐 Secure User Auth with BCrypt
Passwords are encrypted before saving. No more plaintext vulnerabilities.

📍 Smart Train Search
Enter source and destination — we’ll fetch all valid trains passing through in the correct order.

🎟️ Unique Ticket Generator
Each ticket gets a unique ID and stores user, train, route, seat, and date.

🧾 Booking & Cancellation
Book seats visually and cancel anytime. All changes reflect in real-time (via JSON).

🪑 Seat Availability Matrix
2D seat layout displays what's taken and what's available.
  
# 🧠 How It Works
💡 The system loads users and trains from JSON files.
Once you log in or sign up, it offers you a personalized booking experience:

Enter travel route

Choose a train

Select your seat

Boom 💥 — Ticket Booked!

Under the hood:

Seats are marked 1 (booked) or 0 (available) in a 2D array.

All bookings are synced instantly back to train.json and user.json.

# 🛠️ Tech Stack
Layer	           Technology
Language	       Java
JSON Parsing	   Jackson
Auth	           BCrypt (jbcrypt)
Persistence	     Local JSON files
IDE	             IntelliJ / Eclipse / VS Code
Build Tool	     None (Pure Java Project), Maven

# 📦 Project Structure
ticketBooking/
├── App.java                        # Main entry point
├── entities/
│   ├── User.java
│   ├── Train.java
│   └── Ticket.java
├── services/
│   ├── TrainServices.java         # Train management logic
│   └── UserBookingServices.java   # Booking, cancellation, login/signup
├── util/
│   └── UserServiceUtil.java       # Hashing, ticket ID generator
├── localDB/
│   ├── train.json                 # Mock train data
│   └── user.json                  # Mock user data




# “Code is poetry — and this system is a rhythm of data, users, and trains.”

