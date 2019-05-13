package edu.knoldus.employee.couchbase;

import edu.knoldus.employee.couchbase.model.UserDetails;
import edu.knoldus.employee.couchbase.repository.EmployeeRepository;
import edu.knoldus.employee.couchbase.service.EmployeeCouchbaseServiceImpl;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class EmployeeCouchbaseServiceImplTest {

    private final MockExternalService mockExternalService = new MockExternalService();
    private final EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
    private EmployeeCouchbaseServiceImpl employeeCouchbaseService = new EmployeeCouchbaseServiceImpl(employeeRepository,
            mockExternalService);


    @Test
    public void testCallToExternalService() {


        UserDetails userDetails = employeeCouchbaseService.callToExternalservice().block();
        assertEquals("ABC", userDetails.getTitle());

    }
}