package com.codegnan.Cards;

import com.codegnan.Exceptions.InsufficientFundsException;
import com.codegnan.Exceptions.InsufficientMachineBalanceException;
import com.codegnan.Exceptions.InvalidAmountException;
import com.codegnan.Exceptions.NotAOperatorException;
import com.codegnan.interfaces.IATMService;

public class OperatorCard implements IATMService {

private long id;
private int pinNumber;
private String name;
private final String type="operator";

	public OperatorCard(long id, int pinNumber, String name) {
	super();
	this.id = id;
	this.pinNumber = pinNumber;
	this.name = name;
}

	@Override
	public String getUserType() throws NotAOperatorException {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public double withdrawAmount(double withAmount)
			throws InvalidAmountException, InsufficientFundsException, InsufficientMachineBalanceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void depositAmount(double dptAmount) throws InvalidAmountException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double checkAccountBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changePinNumber(int pinNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPinNumber(int pinNumber) {
		// TODO Auto-generated method stub
		return pinNumber;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void decreaseChances() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getChances() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reserPinChances() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateMiniStatement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPinNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
