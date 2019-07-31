package com.infy.couchbasesvc.repository;

import com.infy.couchbasesvc.domain.Employee;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends N1qlCouchbaseRepository<Employee, String> {

}
