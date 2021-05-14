package com.nordea.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nordea.service.AccountA;
import com.nordea.service.AccountB;
import com.nordea.service.AccountC;
import com.nordea.service.TransactionService;

@RestController
public class TriggerTransferController {
	
	@Autowired
	private AccountA accountA;
	
	@Autowired
	private AccountB accountB;
	
	@Autowired
	private AccountC accountC;
	
	private List<TransactionService> accountList = new ArrayList<>();
	
	@GetMapping("/transfer")
	public List<TransactionService> triggerTransfer() {
		
		List<Double> paymentAtoB = Arrays.asList(10.0,52.0,3.2,58.0);
		List<Double> paymentBtoC = Arrays.asList(100.0,50.0,32.0,58.0); 
		List<Double> paymentCtoA = Arrays.asList(120.0,152.0,132.0,5.0);
		accountA.setPayments(paymentAtoB);
		accountB.setPayments(paymentBtoC);
		accountC.setPayments(paymentCtoA);
		
		boolean paymentPossible = true;
        int iteration = 1;
        while(paymentPossible) {
            System.out.println("******************************** Iteration - " + iteration + " *********************");
            boolean paymentPossibleFromA = accountA.transefer();
            boolean paymentPossibleFromB = accountB.transefer();
            boolean paymentPossibleFromC = accountC.transefer();
            paymentPossible = paymentPossibleFromA || paymentPossibleFromB || paymentPossibleFromC;
            iteration++;
            System.out.println();
        }
        accountList.add(accountA);
        accountList.add(accountB);
        accountList.add(accountC);
        						
		return accountList;
	}
	
}
