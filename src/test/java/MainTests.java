import org.hamcrest.Matchers;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;
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
        assertThat(result,equalTo(expected));
    }

    @Test
    public void jsonToListTTest() throws ParseException {
        // given:
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee(1,"John","Smith","USA",25);
        Employee employee2 = new Employee(2,"Ivan","Petrov","Russia",23);
        employees.add(employee1);
        employees.add(employee2);
        String text ="[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Petrov\",\"country\":\"Russia\",\"age\":23}]";
        Main main = new Main();
        // when:
        List<Employee> result = main.jsonToListT(text);

        // then:

        assertThat(result,contains(employee1,employee2));
    }

}
