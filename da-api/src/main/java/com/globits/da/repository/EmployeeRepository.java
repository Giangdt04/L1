package com.globits.da.repository;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("select new com.globits.da.dto.EmployeeDto(ep) from Employee ep")
    List<EmployeeDto> getAll();


    @Query(value = "SELECT new com.globits.da.dto.EmployeeDto(e) FROM Employee e WHERE"
            + "(e.name IS NULL OR e.name like %:name%) OR"
            + "(e.email like %:email%) OR"
            + "(e.age = :age) OR"
            + "(e.phone like %:phone%) OR"
            + "(e.code IS NULL OR e.code like %:code%)")
    List<EmployeeDto> searchEmployees(String name,
                                          String email,
                                          Integer age,
                                          String phone,
                                          String code);

    Optional<Employee> findByCode(String code);

    List<Employee> findAllByCode(String code);

}
