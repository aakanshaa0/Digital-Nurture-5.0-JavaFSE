SET SERVEROUTPUT ON;

ALTER TABLE Customers ADD IsVIP VARCHAR2(5);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (3, 'Robert Wilson', TO_DATE('1970-03-10', 'YYYY-MM-DD'), 12000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (4, 'Mary Johnson', TO_DATE('1988-11-25', 'YYYY-MM-DD'), 8000, SYSDATE);

BEGIN
  FOR cust_rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
    IF cust_rec.Balance > 10000 THEN
      UPDATE Customers
      SET IsVIP = 'TRUE'
      WHERE CustomerID = cust_rec.CustomerID;
      
      DBMS_OUTPUT.PUT_LINE('Customer ' || cust_rec.CustomerID || ' is VIP.');
    ELSE
      UPDATE Customers
      SET IsVIP = 'FALSE'
      WHERE CustomerID = cust_rec.CustomerID;
      
      DBMS_OUTPUT.PUT_LINE('Customer ' || cust_rec.CustomerID || ' is not VIP.');
    END IF;
  END LOOP;
  COMMIT;
END;
/

SELECT CustomerID, Name, Balance, IsVIP FROM Customers;