package com.nordea.service;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nordea.service.TransactionService;

@Service
public class AccountA implements TransactionService {

	private double aBal;

	private List<Double> payments;

	@Autowired
	private AccountB accountB;

	public AccountA() {
		aBal = 123;
	}

	
	  public List<Double> getPayments() { return payments; }
	  
	  public void setPayments(List<Double> payments) { this.payments = payments; }
	 

	public double getaBal() {
		return aBal;
	}

	public void setaBal(double aBal) {
		this.aBal = aBal;
	}

	@Override
	public boolean transefer() {
		payments = payments.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		ListIterator<Double> iterator = payments.listIterator();
		boolean flag = false;
		while (iterator.hasNext()) {
			Double listP = iterator.next();
			if (listP <= aBal) {
				accountB.setbBal(accountB.getbBal() + listP);
				aBal = aBal - listP;
				iterator.remove();
				flag=true;
			}

		}
		System.out.println("remaining payments from A to B : "+payments);
		System.out.println("AccountB balance after first iteration is :" + accountB.getbBal());
		System.out.println("AccountA balance after first iteration is :" + aBal);
		
		return flag;
	}
}
