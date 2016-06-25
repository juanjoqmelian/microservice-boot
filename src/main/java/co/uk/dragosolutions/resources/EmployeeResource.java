package co.uk.dragosolutions.resources;

import co.uk.dragosolutions.domain.Employee;
import co.uk.dragosolutions.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Component
@Path("/employees")
public class EmployeeResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeResource.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Context
    private UriInfo uriInfo;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Employee employee) {
        LOGGER.info("Creating a new employee '{}'", employee);
        final Employee newEmployee = employeeRepository.save(employee);
        return Response.created(uriInfo.getBaseUriBuilder().path("{id}").build(newEmployee.getId())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") long id) {
        LOGGER.debug("Retrieving employee with id '{}'", id);
        final Employee employee = employeeRepository.findOne(id);
        return Response.ok(employee).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        LOGGER.debug("Retrieving all existing employees...");
        final List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return Response.ok(employees).build();
    }


    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }
}
