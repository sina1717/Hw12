package service;

import Entity.Employee;
import repository.AdminRepository;

public class EmployeeService {
    AdminRepository adminRepository = new AdminRepository();

    public Employee save(Employee employee){
        return adminRepository.save(employee);
    }

    public void update(Employee employee){
        adminRepository.update(employee);
    }

    public void delete(Employee employee){
        adminRepository.delete(employee);
    }

    public Employee findById(Integer id){
        return adminRepository.findById(id);
    }

    public Employee login (String username,String password){
        return adminRepository.login(username,password);
    }


}
