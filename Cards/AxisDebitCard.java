package com.codegnan.Cards;

import java.util.ArrayList;
import java.util.Collections;

import com.codegnan.Exceptions.InsufficientFundsException;
import com.codegnan.Exceptions.InsufficientMachineBalanceException;
import com.codegnan.Exceptions.InvalidAmountException;
import com.codegnan.Exceptions.NotAOperatorException;
import com.codegnan.interfaces.IATMService;

public class AxisDebitCard implements IATMService{
String name;
long debitcardNumber;
double accountBalance;
int pinNumber;
ArrayList<String> statement;
final String type="user";
int chances;

	public AxisDebitCard(String name, long debitcardNumber, double accountBalance, int pinNumber) {
	chances=3;
	this.name = name;
	this.debitcardNumber = debitcardNumber;
	this.accountBalance = accountBalance;
	this.pinNumber = pinNumber;
	statement=new ArrayList<>();
}

	@Override
	public String getUserType() throws NotAOperatorException {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public double withdrawAmount(double withAmount)
			throws InvalidAmountException, InsufficientFundsException, InsufficientMachineBalanceException {
		if(withAmount<=0) {
			throw new InvalidAmountException("you can enterZero Amount "+"to withdraw please enter valid amount");
		}else {
			if(withAmount%100!=0) {
				throw new InvalidAmountException("please withdraw multiples of 100");
			}else {
			
				if(withAmount>accountBalance) {
					throw new InsufficientFundsException("you cannnot withdraw amount more than amount of your account");
				}else {
					accountBalance-=withAmount;
					statement.add("Debited:"+withAmount);
					return withAmount;
					}
				}
			}
		}
		

	@Override
	public void depositAmount(double dptAmount) throws InvalidAmountException {
		if(dptAmount <=0 || dptAmount%100!=0) {
			throw new InvalidAmountException("you can't deposit less than zero rupees"+"and deposit multiples of 100");
		}else {
			accountBalance+=dptAmount;
			statement.add("Credited:"+dptAmount);
		}
	}

	@Override
	public double checkAccountBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changePinNumber(int pinNumber) {
		// TODO Auto-generated method stub
		this.pinNumber=pinNumber;
	}

	@Override
	public int getPinNumber(int pinNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void decreaseChances() {
		// TODO Auto-generated method stub
		--chances;
	}

	@Override
	public int getChances() {
		// TODO Auto-generated method stub
		return chances;
	}

	@Override
	public void reserPinChances() {
		// TODO Auto-generated method stub
		chances=3;
	}

	@Override
	public void generateMiniStatement() {
		// TODO Auto-generated method stub
		int count=5;
		if(statement.size()==0) {
			System.out.println("there are no transcations happened");
			return ;
		}
		System.out.println("===========List 5 Trransaction===========");
		for(String trans:statement) {
			if(count==0) {
				break;
			}
			System.out.println(trans);
			count--;
		}
		Collections.reverse(statement);
		
	}

	@Override
	public int getPinNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
