package com.axa.test;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Controller {

	private final ObjectMapper om = new ObjectMapper();
	private static HashMap<Integer, CostumerModel> cList = new HashMap<Integer, CostumerModel>();
	private static HashMap<Integer, BankAccountModel> baList = new HashMap<Integer, BankAccountModel>();

	
	@PostMapping(path = "/customer/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String registerCustomer(HttpServletRequest header, @RequestBody HashMap<String, String> request)
			throws JsonProcessingException {
		final HashMap<String, Object> response = new HashMap<String, Object>();
		
		int i = cList.size() + 1;
		
		CostumerModel cm = new CostumerModel();
		cm.setId(String.valueOf(i));
		cm.setFirst_name(request.get("first_name"));
		cm.setLast_name(request.get("last_name"));
		cm.setUser_name(request.get("user_name"));
		cm.setPassword(request.get("password"));
		
		if(request.get("bank_account_id")!=null) {
			cm.setBank_account_id(request.get("bank_account_id"));
		}else {
			cm.setBank_account_id("");
		}
		
		cList.put(i, cm);
		
		response.put("status", "success");
		response.put("id", i);
		
		return om.writeValueAsString(response);
	}

	@PostMapping(path = "/customer/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateCustomer(HttpServletRequest header, @PathVariable("id") Integer id, @RequestBody HashMap<String, String> request)
			throws JsonProcessingException {
		final HashMap<String, Object> response = new HashMap<String, Object>();
		
		CostumerModel cm = new CostumerModel();
		cm = cList.get(id);
		if(cm!=null) {
			if(request.get("first_name")!=null && !request.get("first_name").isEmpty()) {
				cm.setFirst_name(request.get("first_name"));
			}
			if(request.get("last_name")!=null && !request.get("last_name").isEmpty()) {
				cm.setLast_name(request.get("last_name"));
			}
			if(request.get("user_name")!=null && !request.get("user_name").isEmpty()) {
				cm.setUser_name(request.get("first_name"));
			}
			if(request.get("password")!=null && !request.get("password").isEmpty()) {
				cm.setPassword(request.get("first_name"));
			}
			if(request.get("bank_account_id")!=null && !request.get("bank_account_id").isEmpty()) {
				cm.setBank_account_id(request.get("bank_account_id"));
			}
			cList.put(id, cm);
			response.put("status", "success");
		}else {
			response.put("status", "failed");
		}
		
		return om.writeValueAsString(response);
	}

	@PostMapping(path = "/bankaccount/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public String registerBankAccount(HttpServletRequest header, @RequestBody HashMap<String, String> request)
			throws JsonProcessingException {
		final HashMap<String, Object> response = new HashMap<String, Object>();
			
		int i = baList.size() + 1;
		
		BankAccountModel bam = new BankAccountModel();
		bam.setId(String.valueOf(i));
		bam.setBank_name(request.get("bank_name"));
		bam.setAccount_number(request.get("account_number"));
		
		baList.put(i, bam);
		
		response.put("status", "success");
		return om.writeValueAsString(response);
	}

	@PostMapping(path = "/bankaccount/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateBankAccount(HttpServletRequest header, @PathVariable("id") Integer id, @RequestBody HashMap<String, String> request)
			throws JsonProcessingException {
		final HashMap<String, Object> response = new HashMap<String, Object>();
		
		BankAccountModel bam = new BankAccountModel();
		bam = baList.get(id);
		if(bam!=null) {
			if(request.get("bank_name")!=null && !request.get("bank_name").isEmpty()) {
				bam.setBank_name(request.get("bank_name"));
			}
			if(request.get("account_number")!=null && !request.get("account_number").isEmpty()) {
				bam.setAccount_number(request.get("account_number"));
			}
			baList.put(id, bam);
			response.put("status", "success");
		}else {
			response.put("status", "failed");
		}
		
		return om.writeValueAsString(response);
	}

	@GetMapping(path = "/customer/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String closeAccount(@PathVariable("id") Integer id)
			throws JsonProcessingException {
		CostumerModel cm = new CostumerModel();
		cm = cList.get(id);
		return om.writeValueAsString(cm);
	}
}
