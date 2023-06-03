# Ticket App Documentation

## Endpoints

### Get Active Concerts

Retrieves a list of active concerts.

- **URL:** `/concerts`
- **Method:** GET
- **Response Body Format:** JSON
- **Response Status Codes:**
    - 200 OK: Success
- **Example Response:**
  ```json
  {
    "body": [
        {
            "id": "4028e488887ca02301887ca028e60000",
            "name": "Coldplay Concert",
            "availableTickets": 9999,
            "createdDate": "2023-06-02T15:00:51.046+00:00",
            "modifiedDate": "2023-06-03T15:03:35.232+00:00"
        },
        {
            "id": "4028e488887ca02301887ca028e60001",
            "name": "Stand Up Comedy Show",
            "availableTickets": 20,
            "createdDate": "2023-06-02T15:00:51.046+00:00",
            "modifiedDate": "2023-06-03T15:03:35.232+00:00"
        }
    ],
    "statusMessage": "OK",
    "statusCode": 200
  }
  ```

### Book Ticket

Books a ticket for a specified concert.

- **URL:** `/tickets/book`
- **Method:** POST
- **Request Body Format:** JSON
- **Response Body Format:** JSON
- **Response Status Codes:**
    - 200 OK: Success
    - 404 Not Found: Concert not found or no available tickets
- **Example Request:**
  ```json
  {
    "id": "1"
  }
  ```
- **Example Response (Success):**
  ```json
  {
    "body": {
        "id": "4028e4888881b7f4018881c906270000",
        "concert": {
            "id": "4028e488887ca02301887ca028e60000",
            "name": "Coldplay Concert",
            "availableTickets": 9999,
            "createdDate": "2023-06-02T15:00:51.046+00:00",
            "modifiedDate": "2023-06-03T15:03:35.232+00:00"
        },
        "createdDate": "2023-06-03T15:03:35.204+00:00",
        "modifiedDate": "2023-06-03T15:03:35.204+00:00"
    },
    "statusMessage": "OK",
    "statusCode": 200
  }
  ```
- **Example Response (Not Found):**
  ```json
  {
    "statusMessage": "Not Found",
    "errors": null,
    "statusCode": 404
  }
  ```

## Database Configuration
- Make sure you have installed PostgreSQL on your local computer. 
- Create a database named "ticket". 
- Adjust the database credentials in the `application.properties` file to match your database settings. 
- The default credentials for this application are:
  - Username: postgres
  - Password: postgres


## Database Structure
### Table: concert
- **Description:** Stores information about concerts.
- **Columns:**
  - id (UUID, Primary Key): The unique identifier of the concert.
  - name (VARCHAR, Not Null): The name of the concert.
  - available_tickets (INTEGER, Not Null): The number of available tickets for the concert.
  - created_date (TIMESTAMP, Not Null): The date and time when the concert was created.
  - modified_date (TIMESTAMP): The date and time when the concert was last modified.

### Table: ticket
- **Description:** Stores information about tickets booked for concerts.
- **Columns:**
  - id (UUID, Primary Key): The unique identifier of the ticket.
  - concert_id (UUID, Not Null): The foreign key referencing the concert that the ticket is booked for.
  - created_date (TIMESTAMP, Not Null): The date and time when the ticket was created.
  - modified_date (TIMESTAMP): The date and time when the ticket was last modified.

### Relationships:
The "ticket" table has a Many-to-One relationship with the "concert" table. Each ticket is associated with a single concert.
