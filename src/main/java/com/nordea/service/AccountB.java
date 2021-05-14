package com.nordea.service;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nordea.service.TransactionService;

@Service
public class AccountB implements TransactionService {

	private double bBal;

	@Autowired
	private AccountC accountC;

	private List<Double> payments;

	public AccountB() {
		bBal = 100;
	}

	public double getbBal() {
		return bBal;
	}

	public void setbBal(double bBal) {
		this.bBal = bBal;
	}

	public List<Double> getPayments() {
		return payments;
	}

	public void setPayments(List<Double> payments) {
		this.payments = payments;
	}

	@Override
	public boolean transefer() {
		payments = payments.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		ListIterator<Double> iterator = payments.listIterator();
		boolean flag = false;
		while (iterator.hasNext()) {
			Double listP = iterator.next();
			if (listP <= bBal) {
				accountC.setcBal(accountC.getcBal() + listP);
				bBal = bBal - listP;
				iterator.remove();
				flag = true;
			}
		}
		System.out.println("remaining payments from B to C :" + payments);
		System.out.println("AccountC balance after first iteration is :" + accountC.getcBal());
		System.out.println("AccountB balance after first iteration is :" + bBal);
		return flag;
	}
}
