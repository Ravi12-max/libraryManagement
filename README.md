JDBC URL: `jdbc:h2:mem:librarydb`

## API Endpoints

### Auth
| Method | Endpoint         | Description         | Auth Required |
|--------|------------------|----------------------|----------------|
| POST   | `/auth/register` | Register a new user  | No             |
| POST   | `/auth/login`    | Login, returns JWT   | No             |

### Books
| Method | Endpoint                             | Description                     |
|--------|---------------------------------------|----------------------------------|
| GET    | `/books?page=0&size=5&sortBy=title`   | Get paginated, sorted books      |
| GET    | `/books/{id}`                         | Get a book by ID                 |
| POST   | `/books`                              | Add a new book                   |
| PUT    | `/books/{id}`                         | Update a book                    |
| DELETE | `/books/{id}`                         | Delete a book                    |

### Members
| Method | Endpoint      | Description         |
|--------|----------------|----------------------|
| GET    | `/members`     | Get all members      |
| POST   | `/members`     | Register a member    |

### Book Issues
| Method | Endpoint                            | Description                   |
|--------|---------------------------------------|--------------------------------|
| POST   | `/issues/issue?bookId=1&memberId=1`  | Issue a book to a member       |
| PUT    | `/issues/return/{issueId}`           | Return an issued book          |
| GET    | `/issues/member/{memberId}`          | Get issue history for a member |

> All endpoints except `/auth/**` and `/h2-console/**` require a `Bearer <token>` in the `Authorization` header.

## Example Usage

**Register:**
```json
POST /auth/register
{
  "username": "rakesh",
  "password": "secret123"
}
```

**Login:**
```json
POST /auth/login
{
  "username": "rakesh",
  "password": "secret123"
}
```
Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**Add a book (with token):**
```json
POST /books
Authorization: Bearer <token>
{
  "title": "Atomic Habits",
  "author": "James Clear",
  "isbn": "9780735211292"
}
```

## Running Tests

```bash
mvnw.cmd test
```

Covers service-layer logic including book CRUD, exception scenarios, and the issue/return business rules (e.g. preventing double-issuing a book).

## Future Improvements

- Role-based access control (ADMIN vs USER)
- Dockerize the application
- Switch to a persistent database (PostgreSQL/MySQL) for production
- Add integration tests

## Author

Built by Ravi Ranjan Kumar as a learning project to practice Spring Boot, Spring Security, and REST API best practices.