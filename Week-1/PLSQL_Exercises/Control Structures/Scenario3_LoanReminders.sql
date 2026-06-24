SET SERVEROUTPUT ON;

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) 
VALUES (3, 3, 7500, 4.5, SYSDATE, SYSDATE + 15);

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) 
VALUES (4, 4, 15000, 5.5, SYSDATE, SYSDATE + 45);

BEGIN
  DBMS_OUTPUT.PUT_LINE('=== LOAN REMINDERS (Due within 30 days) ===');
  
  FOR loan_rec IN (
    SELECT l.LoanID, l.CustomerID, c.Name, l.LoanAmount, l.EndDate
    FROM Loans l
    JOIN Customers c ON c.CustomerID = l.CustomerID
    WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || loan_rec.LoanID || 
                         ' for ' || loan_rec.Name || 
                         ' due on ' || TO_CHAR(loan_rec.EndDate, 'DD-MON-YYYY'));
  END LOOP;
END;
/