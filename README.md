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
  [
    {
      "id": "1",
      "name": "Concert 1",
      "date": "2023-06-01",
      "availableTickets": 100
    },
    {
      "id": "2",
      "name": "Concert 2",
      "date": "2023-06-15",
      "availableTickets": 50
    }
  ]
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
    "id": "ABC123",
    "concert": {
      "id": "1",
      "name": "Concert 1",
      "date": "2023-06-01",
      "availableTickets": 99
    }
  }
  ```
- **Example Response (Not Found):**
  ```json
  {
    "message": "Concert not found or no available tickets"
  }
  ```

## Database Configuration
- Make sure you have installed PostgreSQL on your local computer. 
- Create a database named "ticket". 
- Adjust the database credentials in the `application.properties` file to match your database settings. 
- The default credentials for this application are:
  - Username: postgres
  - Password: postgres
