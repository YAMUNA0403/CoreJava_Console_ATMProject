# 🏦 ATM Operations System (Java)

A console-based **ATM Simulation System** written in Java that demonstrates core banking functionalities such as withdrawals, deposits, balance checks, PIN management, and operator controls.

This project highlights **Object-Oriented Programming (OOP)**, **exception handling**, and **modular code design** in Java.

---

## 🚀 Features

### 👤 User Features
- Withdraw and deposit money
- Check account balance
- Change PIN
- View mini statement of transactions
- Automatic blocking after 3 invalid PIN attempts

### 🧑‍💼 Operator Features
- View ATM machine balance
- Deposit cash into ATM
- Reset user PIN attempts
- View system activity logs
- Shut down ATM machine

### ⚙️ System Features
- Custom exception handling
- ATM balance management
- Transaction history tracking
- Operator and user modes
- Modular, extensible structure
------
## 🧩 Preloaded Card Details

| Role | User Name | Card Number | PIN | Balance |
|------|------------|--------------|------|----------|
| User | yamuna | `222222222222` | `22222` | ₹50,000 |
| User | raju | `444444444444` | `33333` | ₹80,000 |
| User | sindhu | `333333333333` | `11111` | ₹60,000 |
| User | gokul | `555555555555` | `56324` | ₹42,365 |
| Operator | sai | `666666666666` | `56324` | — |

---

## 🖥️ How to Run

### 🧱 Prerequisites
- Java 8 or higher installed
- IDE or terminal (Eclipse, IntelliJ IDEA, VS Code, or Command Prompt)

### ⚡ Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/ATMOperations.git
   cd ATMOperations
Compile the project:

javac com/codegnan/Operations/ATMOperations.java

Run the application:

java com.codegnan.Operations.ATMOperations

🧠 Key Concepts Used

Object-Oriented Programming (OOP)

Abstraction

Inheritance

Polymorphism

Encapsulation

Exception Handling

Custom exceptions for card, PIN, and balance validation

Data Structures

ArrayList, HashMap for account and transaction storage

Clean Architecture

Interfaces and separate classes for user/operator cards

🧰 Custom Exceptions

<p>InvalidCardException----- Raised for invalid or unregistered card numbers</p>
<p>InvalidPinException-------	Raised for incorrect PIN entries</p>
<p>IncorrectPinLimitReachedException	---------Triggered after 3 wrong PIN attempts</p>
<p>InsufficientFundsException	------Raised for insufficient user account balance</p>
<p>InsufficientMachineBalanceException------	Raised when ATM lacks enough cash</p>
<p>InvalidAmountException--------	Raised for invalid withdrawal/deposit amounts</p>

<h5>📊 Sample Output</h5>

===== HDFC ATM Machine =====
Please Enter The Debit Card Number: 222222222222
Enter PIN Number: 22222
Welcome, yamuna!

===== User Mode =====
1. Withdraw Amount
2. Deposit Amount
3. Check Balance
4. Change PIN
5. Mini Statement
6. Exit
Enter your choice: 1
Enter amount to withdraw: 2000
₹2000 withdrawn successfully.

Do you want to continue? (Y/N): N

===== Thank you for using HDFC ATM Machine =====

