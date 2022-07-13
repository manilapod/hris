package org.goup10.hris.repositories;

import org.goup10.hris.entities.Employees;
import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepository extends CrudRepository<Employees, Integer> {
}
