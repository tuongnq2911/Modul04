package example.session02.repository;

import example.session02.model.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // Đánh dấu tầng Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    public EmployeeRepository() {
        // Giả lập dữ liệu
        employees.add(new Employee(1,"Nguyễn Văn A","nguyenvana@gmail.com","IT"));
        employees.add(new Employee(2,"Trần Thị B","tranthib@gmail.com","HR"));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Optional<Employee> findById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst();
    }

    public void save(Employee employee) {
        employees.add(employee);
    }

    public void update(int id,Employee newInfo) {
        findById(id).ifPresent(employee -> {
            employee.setFullName(newInfo.getFullName());
            employee.setEmail(newInfo.getEmail());
            employee.setDepartment(newInfo.getDepartment());
        });
    }

    public boolean delete(int id) {
        return employees.removeIf(employee -> employee.getId() == id);
    }
}
