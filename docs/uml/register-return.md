 UC-07 – Register a return

1. Name and identifier

UC-07 – Register a return

2. Goal

To register the return of a borrowed copy, close the corresponding loan, and update the copy's status.

3. Actors

- Primary actor: Librarian.
- Secondary actor: Member.
- Other stakeholders: The member who reserved the returned copy.

4. Level and scope

- Level: User goal.
- Scope: Library Management System.

5. Preconditions

- The librarian is authenticated.
- The returned copy is registered in the library system.
- The copy has an open loan.

6. Trigger

A member returns a borrowed copy to the librarian.

7. Main success scenario

1. The member gives the borrowed copy to the librarian.
2. The librarian identifies the returned copy.
3. The system retrieves the corresponding loan.
4. The system closes the loan.
5. The system checks whether the return is late.
6. The system checks whether the copy is reserved.
7. The system registers the return.

8. Alternative flows

- 6a – Copy reserved: The system keeps the copy aside and notifies the member who reserved it. The use case then continues at step 7.

9. Exceptions

- 3a – Loan not found: The system informs the librarian that no corresponding open loan was found. The return cannot be completed.
- 4a – Database connection lost: The system displays an error and does not confirm the return.

10. Postconditions

- Success guarantee: The loan is closed and the return is registered.
- Minimal guarantee: If the return cannot be completed, the system must not confirm a return that has not been recorded.

11. Special requirements

The system should update the loan and copy information reliably and display confirmation to the librarian.

12. Open issues

- Should the system send an email notification to the member who reserved the copy?
- What should happen if the corresponding loan cannot be identified?