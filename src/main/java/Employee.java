import java.util.Objects;

public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee() {
        // Пустой конструктор
    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "id = " + id +
                " firstName = " + firstName +
                " lastName = " + lastName +
                " country = " + country +
                " age = " + age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName,id,age,country);
    }
    @Override
    public boolean equals(Object obj){
        Employee employee = (Employee) obj;
        return firstName.equals(employee.firstName)&&lastName.equals(employee.lastName)&&country.equals(employee.country)&&age==employee.age&&id==employee.id;
    }
}
