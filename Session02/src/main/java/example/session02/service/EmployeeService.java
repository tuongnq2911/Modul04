package example.session02.service;

import example.session02.model.entity.Employee;
import example.session02.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired // Tiêm Repository vào Service
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getFullName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Employee createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployee(int id,Employee employee) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.update(id, employee);
            return employee;
        }
        return null;
    }

    public boolean deleteEmployee(int id) {
        return employeeRepository.delete(id);
    }
}
