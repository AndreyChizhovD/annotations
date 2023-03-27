import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Person person = new Person("Ivan", "Ivanov", LocalDate.of(1997, 11, 2));
        // var serializable = new JsonSerializer<>(Person.class);
        // var JSON = serializable.serialize(person);
    }
}