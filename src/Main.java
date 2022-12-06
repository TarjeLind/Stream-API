import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long personChild = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + personChild);

        List<String> personArm = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 27)
                .map(Person::getFamily)
                .limit(40)
                .toList();
        System.out.println("Количество призывников: " + personArm.size());
        System.out.println("Список призывников: " + personArm);

        List<Person> personWorkMen = persons.stream()
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 65)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .limit(40)
                .toList();
        System.out.println("Количество работоспособных мужчин: " + personWorkMen.size());

        List<Person> personWorkWomen = persons.stream()
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 60)
                .filter(person -> person.getSex().equals(Sex.WOMEN))
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .limit(40)
                .toList();
        System.out.println("Количество работоспособных женщин: " + personWorkWomen.size());

        List<Person> personsWork = Stream.concat(personWorkMen.stream(), personWorkWomen.stream()).toList();
        personsWork.stream()
                .sorted(Comparator.comparing(Person::getFamily))
                .forEach(System.out::println);

    }
}