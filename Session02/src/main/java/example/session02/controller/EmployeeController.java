package example.session02.controller;

import example.session02.model.dto.EmployeeFilter;
import example.session02.model.entity.Employee;
import example.session02.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Bài tập 3 dùng @RestController
@RequestMapping("/api/employees")  // Bài tập 3 định nghĩa tiền tố chúng
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Bài tập 3 + 5 Lấy danh sách + ResponseEntity
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Bài tập 4 Lấy chi tiết thì dùng @PathVariable

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee); // trả về 200 Ok
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Bài tập 4 Tìm kiếm dùng @RequestParam
    // URL: /api/employees/search?name=Nguyen
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.searchByName(name));
    }

    // Bài tập 4 Lọc nâng cao dùng @ModelAttribute
    // URL: /api/employees/filter?name=A&department=IT
    @GetMapping("/filter")
    public ResponseEntity<String> filterEmployees(@ModelAttribute EmployeeFilter employeeFilter) {
        // Demo nhận dữ liệu
        return ResponseEntity.ok("Đang lọc theo tên: " + employeeFilter.getName() +
                " và phòng ban: " + employeeFilter.getDepartment());
    }

    // Bài tập 5+6 Thêm mới dùng @PostMapping, @RequestBody và Status 201
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    // Bài tập 5 Cập nhật dùng @PutMapping
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Bài tập 5 + 6 Xoá dùng @DeleteMapping và Status 204/200
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            return ResponseEntity.ok("Xoá thành công nhân viên có ID = " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm tháy nhân viên để xoá");
        }
    }

}
