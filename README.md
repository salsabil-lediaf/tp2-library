# Library management – TP 2

Java/Maven project for a simple library management application.

## Requirements
- JDK 17 or newer
- Maven (or Eclipse with Maven integration)

## Run tests
```bash
mvn test
```

## Main classes
- `Book`: book details, identified by ISBN
- `Copy`: physical copy of a book
- `Member`: library member
- `Loan`: loan and due date
- `Library`: manages books, copies, members, loans and title search
