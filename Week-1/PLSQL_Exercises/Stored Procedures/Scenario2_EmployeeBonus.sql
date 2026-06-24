SET SERVEROUTPUT ON;

CREATE TABLE Employees (
  EmployeeID NUMBER PRIMARY KEY,
  Name VARCHAR2(100),
  Position VARCHAR2(50),
  Salary NUMBER,
  Department VARCHAR2(50),
  HireDate DATE
);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (3, 'Charlie Davis', 'Analyst', 65000, 'Finance', TO_DATE('2018-08-10', 'YYYY-MM-DD'));

SELECT * FROM Employees;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
  p_dept IN VARCHAR2,
  p_bonus IN NUMBER
) AS
BEGIN
  FOR emp IN (
    SELECT EmployeeID, Name, Salary
    FROM Employees
    WHERE Department = p_dept
  ) LOOP
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus / 100)
    WHERE EmployeeID = emp.EmployeeID;
    
    DBMS_OUTPUT.PUT_LINE(emp.Name || ' got ' || p_bonus || '% bonus. New salary: $' || 
                         ROUND(emp.Salary * (1 + p_bonus/100), 2));
  END LOOP;
  COMMIT;
END;
/

EXEC UpdateEmployeeBonus('IT', 10);
EXEC UpdateEmployeeBonus('HR', 15);

SELECT EmployeeID, Name, Department, Salary FROM Employees;