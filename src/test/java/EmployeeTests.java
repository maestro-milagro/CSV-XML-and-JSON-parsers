import org.junit.Test;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class EmployeeTests {
    @Test
    public void setIdTest(){
        Employee employeeStart = new Employee(2,"Andrew","Watt","England",26);
        Employee employeeResult = new Employee(3,"Andrew","Watt","England",26);
        String expected = employeeResult.toString();

        employeeStart.setId(3);
        String result = employeeStart.toString();

        assertThat(result,equalTo(expected));
    }
    @Test
    public void setAgeTest(){
        Employee employeeStart = new Employee(3,"Andrew","Watt","England",20);
        Employee employeeResult = new Employee(3,"Andrew","Watt","England",26);
        String expected = employeeResult.toString();

        employeeStart.setAge(26);
        String result = employeeStart.toString();

        assertThat(result,equalTo(expected));
    }
    @Test
    public void setCountryTest(){
        Employee employeeStart = new Employee(3,"Andrew","Watt","Brazil",26);
        Employee employeeResult = new Employee(3,"Andrew","Watt","England",26);
        String expected = employeeResult.toString();

        employeeStart.setCountry("England");
        String result = employeeStart.toString();

        assertThat(result,equalTo(expected));
    }
}
