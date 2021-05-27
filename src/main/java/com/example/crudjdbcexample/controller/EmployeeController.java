package com.example.crudjdbcexample.controller;

import com.example.crudjdbcexample.dao.jdbc.JdbcTemplateEmployeeDaoImpl;
import com.example.crudjdbcexample.models.Employee;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class EmployeeController extends BaseController {

    private final JdbcTemplateEmployeeDaoImpl jdbcTemplateEmployeeDao;

    @ApiOperation(value = "Получение списка заказов пользователя")
    @RequestMapping(value = "/employees/all", method = RequestMethod.GET)
    public ResponseEntity<?> getOrders(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                       @RequestParam(value = "pageSize", defaultValue = "12") Integer pageSize,
                                       @RequestParam(value = "search", required = false) String search,
                                       @RequestHeader(value = "userId", required = false) Long userId) {
        return buildResponse(jdbcTemplateEmployeeDao.getEmployees(userId, pageNumber, pageSize, search), HttpStatus.OK);
    }

    @ApiOperation(value = "Удалить сотрудника")
    @RequestMapping(value = "/employees", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@RequestParam(value = "employeeId") Long employeeId) {
        jdbcTemplateEmployeeDao.deleteEmployee(employeeId);
        return buildSuccessResponse("Employee is deleted!");
    }

    @ApiOperation(value = "Посмотреть данные о сотруднике")
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        return buildResponse(jdbcTemplateEmployeeDao.getEmployee(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Добавить сотрудника")
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        jdbcTemplateEmployeeDao.createEmployee(employee);
        return buildSuccessResponse("Employee is Created!");
    }

    @ApiOperation(value = "Изменение контента сотрудника")
    @RequestMapping(value = "/employees", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateOrder(@RequestBody Employee employee) {
        jdbcTemplateEmployeeDao.updateEmployeeDetails(employee);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Updated!");
    }
}
