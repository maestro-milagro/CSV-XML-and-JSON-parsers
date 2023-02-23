import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class EmployeeTests {
    @Test
    public void setAgeTest(){
        Employee employeeStart = new Employee(3,"Andrew","Watt","England",26);
        Employee employeeResult = new Employee(3,"Andrew","Watt","England",26);
        String expected = employeeResult.toString();

        employeeStart.setId(3);
        String result = employeeStart.toString();

        Assertions.assertEquals(expected,result);
    }
}
