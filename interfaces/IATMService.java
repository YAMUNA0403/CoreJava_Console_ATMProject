package com.codegnan.interfaces;

import com.codegnan.Exceptions.InsufficientFundsException;
import com.codegnan.Exceptions.InsufficientMachineBalanceException;
import com.codegnan.Exceptions.InvalidAmountException;
import com.codegnan.Exceptions.NotAOperatorException;

public interface IATMService {
public abstract String getUserType() throws NotAOperatorException;
public abstract double withdrawAmount(double withAmount) throws InvalidAmountException, InsufficientFundsException, InsufficientMachineBalanceException;
public abstract void depositAmount(double dptAmount) throws InvalidAmountException;
public abstract double checkAccountBalance();
public abstract void changePinNumber(int pinNumber);
public abstract int getPinNumber();
public abstract String getUserName();
public abstract void decreaseChances();
public abstract int getChances();
public abstract void reserPinChances();
public abstract void generateMiniStatement();
int getPinNumber(int pinNumber);
}
