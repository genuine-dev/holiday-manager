package jp.co.genuine.hm.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jp.co.genuine.hm.api.domain.Customer;
import jp.co.genuine.hm.api.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {
	@Autowired
	CustomerService customerService;

	@GetMapping
	Page<Customer> getCustomers(@PageableDefault Pageable pageable) {
		Page<Customer> customers = customerService.findAll(pageable);
		return customers;
	}

	@GetMapping(path = "{id}")
	Customer getCustomer(@PathVariable Integer id) {
		Customer customer = customerService.findOne(id);
		return customer;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, UriComponentsBuilder uribBuilder) {
		Customer created = customerService.create(customer);
		URI location = uribBuilder.path("api/customers/{id}").buildAndExpand(created.getId()).toUri();
		return ResponseEntity.created(location).body(created);
	}

	@PutMapping(path = "{id}")
	Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
		customer.setId(id);
		return customerService.update(customer);
	}

	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCustomer(@PathVariable Integer id) {
		Customer customer = new Customer();
		customer.setId(id);
		customerService.delete(customer);
	}
}
