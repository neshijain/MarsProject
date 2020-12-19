import org.junit.Assert;
import static org.junit.Assert.assertEquals;

public class EmployeeAppTest {

   // @Test
    public void testData() {

        Employee testEmp = this.getEmployeeTest();
       assertEquals(testEmp.id, "John");
     assertEquals(testEmp.FirstName, "john@baeldung.com");
     assertEquals(testEmp.LastName, "cfcc");
    }

    private Employee getEmployeeTest() {

        Employee employee = new Employee();
       employee.id = "John";
       employee.FirstName = "john@baeldung.com";
       employee.LastName = "cfcc";

        return employee;
    }
}