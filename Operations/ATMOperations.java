package com.codegnan.Operations;

import com.codegnan.Cards.AxisDebitCard;
import com.codegnan.Cards.HDFCDebitCard;
import com.codegnan.Cards.KotakDebitCard;
import com.codegnan.Cards.OperatorCard;
import com.codegnan.Cards.SBIDebitCard;
import com.codegnan.Exceptions.*;
import com.codegnan.interfaces.IATMService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ATMOperations {

    // Initial ATM Balance
    public static double ATM_MACHINE_BALANCE = 100000.0;
    public static ArrayList<String> ACTIVITY = new ArrayList<>();
    public static HashMap<Long, IATMService> database = new HashMap<>();
    public static boolean MACHINE_ON = true;
    public static IATMService card;

    // Validate card existence
    public static IATMService validateCard(long cardNumber) throws InvalidCardException {
        if (database.containsKey(cardNumber)) {
            return database.get(cardNumber);
        } else {
            ACTIVITY.add("Accessed by: " + cardNumber + " - Invalid card access attempt");
            throw new InvalidCardException("This is not a valid card.");
        }
    }

    // Print ATM activities
    public static void checkATMMachineActivities() {
        System.out.println("================= Activities performed ============");
        for (String activity : ACTIVITY) {
            System.out.println(activity);
            System.out.println("==========================================");
        }
    }

    // Reset user attempts
    public static void resetUserAttempts(IATMService operatorCard) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user card number to reset attempts:");
        long number = sc.nextLong();

        try {
            IATMService userCard = validateCard(number);
            userCard.reserPinChances();
            ACTIVITY.add("Accessed by: " + operatorCard.getUserName() + " - reset PIN attempts for user");
            System.out.println("✅ User PIN attempts reset successfully.");
        } catch (InvalidCardException e) {
            System.out.println("❌ Invalid card entered.");
        }
    }

    // Validate user/operator credentials
    public static IATMService validateCredentials(long cardNumber, int pinNumber)
            throws InvalidCardException, InvalidPinException, IncorrectPinLimitReachedException {

        if (!database.containsKey(cardNumber)) {
            throw new InvalidCardException("❌ This card is not valid.");
        }

        card = database.get(cardNumber);

        // Check pin attempts
        if (card.getChances() <= 0) {
            throw new IncorrectPinLimitReachedException("❌ You have reached the max PIN attempt limit.");
        }

        // Validate pin
        if (card.getPinNumber() != pinNumber) {
            card.decreaseChances();
            throw new InvalidPinException("❌ Incorrect PIN. Remaining chances: " + card.getChances());
        }

        return card;
    }

    // Withdraw amount
    public static void withdrawAmount(double amount) throws InsufficientMachineBalanceException {
        if (amount > ATM_MACHINE_BALANCE) {
            throw new InsufficientMachineBalanceException("❌ Insufficient cash in the machine.");
        }
        ATM_MACHINE_BALANCE -= amount;
        ACTIVITY.add("Amount withdrawn: " + amount);
    }

    // Validate deposit
    public static void validateDepositAmount(double amount)
            throws InvalidAmountException, InsufficientMachineBalanceException {
        if (amount % 100 != 0) {
            throw new InvalidAmountException("❌ Please deposit amount in multiples of 100.");
        }
        if (amount + ATM_MACHINE_BALANCE > 200000.0d) {
            ACTIVITY.add("Unable to deposit: machine cash limit reached.");
            throw new InsufficientMachineBalanceException("❌ Cannot deposit: machine limit reached.");
        }
    }

    // Operator mode
    public static void operatorMode(IATMService card) {
        Scanner sc = new Scanner(System.in);
        double amount;
        boolean flag = true;

        while (flag) {
            System.out.println("\n===== Operator Mode =====");
            System.out.println("Operator Name: " + card.getUserName());
            System.out.println("1. Switch off the machine");
            System.out.println("2. Check ATM Machine Balance");
            System.out.println("3. Deposit cash in the machine");
            System.out.println("4. Reset user PIN attempts");
            System.out.println("5. Check activities performed");
            System.out.println("6. Exit operator mode");
            System.out.print("Enter your choice: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    MACHINE_ON = false;
                    ACTIVITY.add("Accessed by " + card.getUserName() + " - Switched off the ATM machine");
                    flag = false;
                    break;
                case 2:
                    ACTIVITY.add("Accessed by " + card.getUserName() + " - Checked ATM balance");
                    System.out.println("ATM Machine Balance: ₹" + ATM_MACHINE_BALANCE);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    amount = sc.nextDouble();
                    try {
                        validateDepositAmount(amount);
                        ATM_MACHINE_BALANCE += amount;
                        ACTIVITY.add("Accessed by " + card.getUserName() + " - Deposited ₹" + amount);
                        System.out.println("✅ Cash added successfully to ATM machine.");
                    } catch (InvalidAmountException | InsufficientMachineBalanceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    resetUserAttempts(card);
                    ACTIVITY.add("Accessed by " + card.getUserName() + " - Reset user PIN attempts");
                    break;
                case 5:
                    checkATMMachineActivities();
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("❌ Invalid option entered.");
            }
        }
    }

    // Main
    public static void main(String[] args) throws NotAOperatorException {

        // Adding sample users
        database.put(222222222222L, new AxisDebitCard("yamuna", 222222222222L, 50000.0, 22222));
        database.put(444444444444L, new HDFCDebitCard("raju", 444444444444L, 80000.0, 33333));
        database.put(333333333333L, new KotakDebitCard("sindhu", 333333333333L, 60000.0, 11111));
        database.put(555555555555L, new SBIDebitCard("gokul", 555555555555L, 42365.0, 56324));
        database.put(666666666666L, new OperatorCard(666666666666L, 56324, "sai"));

        Scanner scanner = new Scanner(System.in);
        long cardNumber;
        double depositAmount;
        double withdrawAmount;
        int pin;

        // Main loop for ATM Operations
        while (MACHINE_ON) {
            System.out.print("\nPlease Enter The Debit Card Number: ");
            cardNumber = scanner.nextLong();
            System.out.print("Enter PIN Number: ");
            pin = scanner.nextInt();

            try {
                card = validateCredentials(cardNumber, pin);
                System.out.println("✅ Welcome, " + card.getUserName() + "!");

                ACTIVITY.add("Accessed by: " + card.getUserName() + " - Access Approved");

                if (card.getUserType().equalsIgnoreCase("operator")) {
                    operatorMode(card);
                    continue;
                }

                boolean continueUser = true;
                while (continueUser) {
                    System.out.println("\n===== User Mode: " + card.getUserName() + " =====");
                    System.out.println("1. Withdraw Amount");
                    System.out.println("2. Deposit Amount");
                    System.out.println("3. Check Balance");
                    System.out.println("4. Change PIN");
                    System.out.println("5. Mini Statement");
                    System.out.println("6. Exit");
                    System.out.print("Enter your choice: ");
                    int option = scanner.nextInt();

                    try {
                        switch (option) {
                            case 1:
                                System.out.print("Enter amount to withdraw: ");
                                withdrawAmount = scanner.nextDouble();
                                card.withdrawAmount(withdrawAmount);
                                ATM_MACHINE_BALANCE -= withdrawAmount;
                                ACTIVITY.add("Accessed by: " + card.getUserName() + " - Withdrawn ₹" + withdrawAmount);
                                System.out.println("✅ ₹" + withdrawAmount + " withdrawn successfully.");
                                break;
                            case 2:
                                System.out.print("Enter amount to deposit: ");
                                depositAmount = scanner.nextDouble();
                                validateDepositAmount(depositAmount);
                                ATM_MACHINE_BALANCE += depositAmount;
                                card.depositAmount(depositAmount);
                                ACTIVITY.add("Accessed by: " + card.getUserName() + " - Deposited ₹" + depositAmount);
                                System.out.println("✅ ₹" + depositAmount + " deposited successfully.");
                                break;
                            case 3:
                                System.out.println("💰 Your Account Balance: ₹" + card.checkAccountBalance());
                                ACTIVITY.add("Accessed by: " + card.getUserName() + " - Checked balance");
                                break;
                            case 4:
                                System.out.print("Enter a new PIN: ");
                                pin = scanner.nextInt();
                                card.changePinNumber(pin);
                                ACTIVITY.add("Accessed by: " + card.getUserName() + " - Changed PIN");
                                System.out.println("✅ PIN changed successfully.");
                                break;
                            case 5:
                                ACTIVITY.add("Accessed by: " + card.getUserName() + " - Generated mini statement");
                                card.generateMiniStatement();
                                break;
                            case 6:
                                continueUser = false;
                                break;
                            default:
                                System.out.println("❌ Invalid option.");
                                break;
                        }
                    } catch (InvalidAmountException | InsufficientFundsException | InsufficientMachineBalanceException e) {
                      e.printStackTrace();
                    }

                    if (continueUser) {
                        System.out.print("Do you want to continue? (Y/N): ");
                        String nextOption = scanner.next();
                        if (nextOption.equalsIgnoreCase("n")) {
                            continueUser = false;
                        }
                    }
                }

            } catch (InvalidCardException | InvalidPinException | IncorrectPinLimitReachedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\n===============================================");
        System.out.println("===== Thank you for using HDFC ATM Machine =====");
        System.out.println("===============================================");

        scanner.close();
    }
}
