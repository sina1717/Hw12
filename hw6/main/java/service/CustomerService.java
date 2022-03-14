package service;

import Entity.Customer;
import repository.CustomerRepository;

public class CustomerService {
    CustomerRepository customerRepository = new CustomerRepository();

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public void update(Customer customer){
         customerRepository.update(customer);
    }

    public void delete(Customer customer){
        customerRepository.delete(customer);
    }

    public Customer findById(Integer id){
        return customerRepository.findById(id);
    }

    public Customer findByNationalCode(String nationalCode){
        return customerRepository.findByNationalCode(nationalCode);
    }
}
