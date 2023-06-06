# Text Sharing Service

Text Sharing Service is a service for creating, retrieving, updating, and deleting notes.

## Requirements

To run the project, you'll need to install the following components:

- Java Development Kit (JDK) version 8 or above
- Scala Build Tool (sbt) version 1.5.5 or above
- PostgreSQL database

## Installation

1. Clone the project repository to your computer:

git clone https://github.com/your-username/text-sharing-service.git

2. Navigate to the project directory:

cd text-sharing-service

3. Configure the database connection in the `application.conf` file located in the `src/main/resources` directory. Update the following properties with your PostgreSQL database credentials:

database {
driverClassName = "org.postgresql.Driver"
url = "jdbc:postgresql://localhost:5432/postgres"
user = "your-username"
password = "your-password"
}

4. Build the project using sbt:

sbt compile

5. Run the application:

sbt run


6. The Text Sharing Service will be accessible at `http://localhost:8080`.

## Usage

Once the Text Sharing Service is running, you can interact with it using the following API endpoints:

- GET `/notes` - Retrieves all notes
- GET `/notes/{noteId}` - Retrieves a specific note by ID
- POST `/notes` - Creates a new note
- PUT `/notes/{noteId}` - Updates an existing note
- DELETE `/notes/{noteId}` - Deletes a note by ID

Make sure to replace `{noteId}` with the actual ID of the note you want to retrieve, update, or delete.

You can use tools like cURL or Postman to make HTTP requests to these endpoints and interact with the Text Sharing Service.



