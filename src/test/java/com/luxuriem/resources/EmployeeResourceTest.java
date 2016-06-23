package com.luxuriem.resources;

import com.luxuriem.domain.Employee;
import com.luxuriem.repository.EmployeeRepository;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class EmployeeResourceTest {

    private final Mockery mockery = new JUnit4Mockery();

    private EmployeeResource employeeResource;
    private EmployeeRepository mockEmployeeRepository;
    private UriInfo mockUriInfo;


    @Before
    public void setUp() {
        this.employeeResource = new EmployeeResource();
        this.mockEmployeeRepository = mockery.mock(EmployeeRepository.class);
        this.mockUriInfo = mockery.mock(UriInfo.class);
        this.employeeResource.setEmployeeRepository(mockEmployeeRepository);
        this.employeeResource.setUriInfo(mockUriInfo);
    }


    @Test
    public void create_shouldCreateAnEmployee() {

        final String baseUri = "http://example.com/employee";

        final Employee employee = new Employee("Jay", "Williams", 33, new BigDecimal("2200.50"));
        employee.setId(1234L);

        mockery.checking(new Expectations() {
            {
                oneOf(mockUriInfo).getBaseUriBuilder();
                will(returnValue(UriBuilder.fromPath(baseUri)));

                oneOf(mockEmployeeRepository).save(employee);
                will(returnValue(employee));
            }
        });

        Response response = employeeResource.create(employee);

        assertThat(response.getStatus(), is(Response.Status.CREATED.getStatusCode()));
        assertThat(response.getLocation(), locationUriWhichStartsWith(baseUri));
    }

    @Test
    public void find_shouldReturnAnEmployeeById() {

        final Employee employee = new Employee("Jay", "Williams", 33, new BigDecimal("2200.50"));
        employee.setId(1234L);

        mockery.checking(new Expectations() {
            {
                oneOf(mockEmployeeRepository).findOne(1234L);
                will(returnValue(employee));
            }
        });

        Response response = employeeResource.find(1234L);

        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(response.getEntity(), is(employee));
    }

    @Test
    public void findAll_shouldReturnAllExistingEmployees() {

        final List<Employee> employees = new ArrayList<>();
        final Employee employee = new Employee("Jay", "Williams", 33, new BigDecimal("2200.50"));
        employee.setId(1234L);
        final Employee employee2 = new Employee("Thomas", "Scott", 51, new BigDecimal("2789.90"));
        employee.setId(1235L);
        employees.add(employee);
        employees.add(employee2);

        mockery.checking(new Expectations() {
            {
                oneOf(mockEmployeeRepository).findAll();
                will(returnValue(employees));
            }
        });

        Response response = employeeResource.findAll();

        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        assertThat(response.getEntity(), is(employees));
    }


    @After
    public void after() {
        mockery.assertIsSatisfied();
    }

    private TypeSafeMatcher<URI> locationUriWhichStartsWith(final String baseUri) {
        return new TypeSafeMatcher<URI>() {
            @Override
            protected boolean matchesSafely(URI item) {
                return item.toString().startsWith(baseUri);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("an URI which starts with ");
                description.appendValue(baseUri);
            }
        };
    }
}