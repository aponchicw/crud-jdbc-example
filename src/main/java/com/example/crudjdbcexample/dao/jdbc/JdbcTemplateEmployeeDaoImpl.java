package com.example.crudjdbcexample.dao.jdbc;

import com.example.crudjdbcexample.dao.EmployeeDao;
import com.example.crudjdbcexample.mapper.EmployeeMapper;
import com.example.crudjdbcexample.mapper.EmployeeResponseMapper;
import com.example.crudjdbcexample.methods.CallApi;
import com.example.crudjdbcexample.models.Employee;
import com.example.crudjdbcexample.models.responses.EmployeeResponse;
import com.example.crudjdbcexample.models.responses.ManagerEmployeeResponse;
import com.example.crudjdbcexample.models.responses.UserEmployeePaginationResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.*;

@Repository
@Transactional
@AllArgsConstructor
public class JdbcTemplateEmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

    private final CallApi callApi;

    @Autowired
    public JdbcTemplateEmployeeDaoImpl(DataSource dataSource,
                                    CallApi callApi) {
        this.callApi = callApi;
        this.setDataSource(dataSource);
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        String sql = "select * from employees where id=?";
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, ps -> ps.setLong(1, employeeId), new EmployeeMapper()).get(0);
    }

    @Override
    public void createEmployee(Employee employee) {

        String sql = "insert into employees (first_name, last_name, email) values (?, ?, ?)";
        assert this.getJdbcTemplate() != null;

        this.getJdbcTemplate().update(sql, employee.getName(), employee.getSurname(), employee.getEmail());
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        String sql = "delete from employees where id=?";
        assert this.getJdbcTemplate() != null;
        this.getJdbcTemplate().update(sql, employeeId);
    }

    @Override
    public void updateEmployeeDetails(Employee employee) {
        String sql = "update employees set first_name=?, last_name=?, email=? where id=?";

        this.getJdbcTemplate().update(sql, employee.getName(), employee.getSurname(), employee.getEmail(), employee.getId());
    }

    @Override
    public UserEmployeePaginationResponse getEmployees(Long userId, int pageNumber, int pageSize, String search) {
        // vse responses
        List<ManagerEmployeeResponse> managerEmployeeResponses = new ArrayList<>();

        ManagerEmployeeResponse managerEmployeeResponse;

        // odin response
        String employeeSql = "select * from employees where id=?";
        assert this.getJdbcTemplate() != null;

        System.out.println("sql: " + employeeSql);

        // присваиваем к каждому запросу значение с таблицы
        List<EmployeeResponse> employees = this.getJdbcTemplate().query(employeeSql, new EmployeeResponseMapper(), userId);

        for(EmployeeResponse employeeResponse : employees){
            managerEmployeeResponse = new ManagerEmployeeResponse();

            managerEmployeeResponse.setId(employeeResponse.getId());
            managerEmployeeResponse.setEmail(employeeResponse.getEmail());
            managerEmployeeResponse.setName(employeeResponse.getName());
            managerEmployeeResponse.setSurname(employeeResponse.getSurname());

            managerEmployeeResponses.add(managerEmployeeResponse);
        }

        String sqlForCount = "select count(*) from employees";
        int count = this.getJdbcTemplate().queryForObject(sqlForCount, Integer.class);

        return new UserEmployeePaginationResponse(managerEmployeeResponses, (long) Math.ceil((double) count / pageSize), count);
    }
}
