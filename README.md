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

## Setup Instructions
1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd ParcelCollectorManagement
   ```

2. **Build the Project**
   Ensure you have Maven installed. Run the following command to build the project:
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   To run the application, execute the following command:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.parcel.Main"
   ```

## Usage
- **Admin Users**: Admins can manage parcel collections, view user details, and perform administrative tasks.
- **Student Users**: Students can request parcel collections and view their parcel statuses.

## Testing
Unit tests for the User class are located in `src/test/java/com/example/parcel/model/UserTest.java`. To run the tests, use:
```bash
mvn test
```

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.
