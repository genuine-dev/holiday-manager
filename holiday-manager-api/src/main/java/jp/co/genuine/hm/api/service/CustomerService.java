package jp.co.genuine.hm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.genuine.hm.api.domain.Customer;
import jp.co.genuine.hm.api.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	public Page
	<Customer> findAll(Pageable pageable) {
		return customerRepository.findAllOrderByName(pageable);
	}

	public Customer findOne(Integer id) {
		return customerRepository.getOne(id);
	}

	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer update(Customer customer) {
		return customerRepository.save(customer);
	}

	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}
}