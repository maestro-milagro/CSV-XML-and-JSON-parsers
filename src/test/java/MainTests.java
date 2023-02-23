import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MainTests {
    @Test
    public void listToJsonTTest(){
        // given:
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"John","Smith","USA",25));
        employees.add(new Employee(2,"Ivan","Petrov","Russia",23));
        String expected ="[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"Russia\",\"age\":23}]";
        Main main = new Main();

        // when:
        String result = main.listToJsonT(employees);

        // then:
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void jsonToListTTest() throws ParseException {
        // given:
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"John","Smith","USA",25));
        employees.add(new Employee(2,"Ivan","Petrov","Russia",23));
        String expected = employees.toString();
        String text ="[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"Russia\",\"age\":23}]";
        Main main = new Main();
        // when:
        String result = main.jsonToListT(text).toString();

        // then:
        Assertions.assertEquals(expected,result);
    }

}
