SET SERVEROUTPUT ON;

CREATE TABLE Transactions (
  TransactionID NUMBER PRIMARY KEY,
  AccountID NUMBER,
  TransactionDate DATE,
  Amount NUMBER,
  TransactionType VARCHAR2(10),
  FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (4, 1, 'Checking', 25000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (5, 2, 'Savings', 3500, SYSDATE);

SELECT * FROM Accounts;

CREATE OR REPLACE PROCEDURE TransferFunds(
  p_from IN NUMBER,
  p_to IN NUMBER,
  p_amount IN NUMBER
) AS
  v_balance NUMBER;
BEGIN
  SELECT Balance INTO v_balance
  FROM Accounts
  WHERE AccountID = p_from
  FOR UPDATE;
  
  IF v_balance < p_amount THEN
    DBMS_OUTPUT.PUT_LINE('Insufficient funds! Available: $' || v_balance);
    RETURN;
  END IF;
  
  UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from;
  UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to;
  
  INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES ((SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions), 
          p_from, SYSDATE, -p_amount, 'Transfer');
  
  INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES ((SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions), 
          p_to, SYSDATE, p_amount, 'Transfer');
  
  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Transfer successful!');
END;
/

SELECT AccountID, Balance FROM Accounts WHERE AccountID IN (1, 2);

EXEC TransferFunds(1, 2, 500);

SELECT AccountID, Balance FROM Accounts WHERE AccountID IN (1, 2);

EXEC TransferFunds(1, 2, 10000);

SELECT * FROM Transactions;