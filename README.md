# Parcel Collector Management

## Overview
The Parcel Collector Management project is designed to manage parcel collection processes for different user types, specifically Admins and Students. This application allows for efficient handling of parcel-related tasks and provides a user-friendly interface for both user types.

## Project Structure
```
ParcelCollectorManagement
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── example
│   │               └── parcel
│   │                   ├── model
│   │                   │   ├── User.java
│   │                   │   ├── Admin.java
│   │                   │   └── Student.java
|   |                   |   └── Parcel.java
|   |                   |   └── ParcelCentre.java
│   │                   └── Main.java
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── parcel
│                       └── model
│                           └── UserTest.java
├── pom.xml
├── .gitignore
└── README.md
```
## Class responsibilities

File           | Responsibility
- **Main.java**: Entry point. Logs the user in, then routes to either the admin menu or the student flow.

- **User.java**: Base class with shared fields (name, id, email, phone) used by both Admin and Student.

- **Admin.java**: Extends User. Holds admin role/password and admin-only actions like registering parcels and other admins.

- **Student.java**: Extends User. Holds the student's linked Parcel, matric number, and OTP logic for claiming a package.

- **Parcel.java**: A single parcel record: tracking number, recipient details, delivery info, and status.

- **ParcelCentre.java**: Acts as the in-memory "database" for the centre — stores all parcels and provides lookup/update/remove operations.

There is no real database — all data lives in memory for the duration of one run, aside from one demo student + parcel seeded directly in main (see Demo accounts below).

## Tracking numbers
Tracking numbers are exactly 15 digits (e.g. 225347071883677), stored as long. New parcels are checked against this rule when registered.

## Demo accounts (built into the code)
- Admin login — username: admin, password: password
- Student login — matric number: 2025801536, tracking number: 225347071883677

## Setup Instructions
1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd ParcelCollectorManagement
   ```
2. **Run it via VS Code (recommended)**
   This project doesn't require Maven on your PATH to run — VS Code's Java tooling can compile and run it directly.

   - Install the Extension Pack for Java (by Microsoft) from the Extensions panel, if you don't have it already.

   - Open src/main/java/com/example/parcel/Main.java.

   - Click the Run link that appears just above public static void main(...).

   - A terminal panel opens at the bottom showing the program's output — click into it to type your input. 

3. **Build the Project**
   Ensure you have Maven installed. Run the following command to build the project:
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   To run the application, execute the following command:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.parcel.Main"
   ```

## Usage
- **Admin Users**: Admins can register, view, update, and delete parcels, send notifications, assign staff, and register new admins.
   1. Register new parcel
   2. View all parcels
   3. Update parcel status
   4. Delete parcel
   5. Send notification to student
   6. Assign staff to parcel centre
   7. Register new admin
   8. Exit

- **Student Users**: Logging in with any other username/password falls through to the student flow, where you'll be asked for your matric number and tracking number, then an OTP to claim your package

## Known limitations
- All data is in-memory only 
- Nothing is saved between runs, aside from the one hardcoded demo student/parcel.
- Admin login is a hardcoded check (admin / password) rather than a real authentication system.
- There's no persistence layer (database or file storage) yet

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.
