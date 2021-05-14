package com.nordea.service;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nordea.service.TransactionService;

@Service
public class AccountC implements TransactionService {
	private double cBal;
	
	@Autowired
	private AccountA accountA;
	
	private List<Double> payments;
	
	public AccountC() {
		cBal = 50;
	}

	
	  public List<Double> getPayments() 
	  { 
		  return payments; 
	  }
	  
	  public void setPayments(List<Double> payments) { this.payments = payments; }
	 
	public double getcBal() {
		return cBal;
	}

	public void setcBal(double cBal) {
		this.cBal = cBal;
	}

	@Override
	public boolean transefer() {
		payments = payments.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		ListIterator<Double> iterator = payments.listIterator();
		boolean flag=false;
		
		while (iterator.hasNext()) {
			Double listP = iterator.next();
			if (listP <= cBal) {
				accountA.setaBal(accountA.getaBal() + listP);
				cBal = cBal - listP;
				iterator.remove();
				flag=true;
			}
		}
		
		System.out.println("remaining payments from C to A :"+payments);

		System.out.println("AccountA balance after is :" + accountA.getaBal());
		System.out.println("AccountC balance after is :" + cBal);
		return flag;
		}
}
