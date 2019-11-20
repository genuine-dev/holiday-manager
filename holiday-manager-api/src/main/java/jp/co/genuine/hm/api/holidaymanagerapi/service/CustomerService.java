package jp.co.genuine.hm.api.holidaymanagerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.genuine.hm.api.holidaymanagerapi.domain.Customer;
import jp.co.genuine.hm.api.holidaymanagerapi.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> findAll() {
		return customerRepository.findAllOrderByName();
	}

	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	public  Customer update(Customer customer) {
		return customerRepository.save(customer);
	}

	public void delete(Customer customer) {
		customerRepository.delete(customer
				);
	}
}