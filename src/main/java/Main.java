import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        List<Department> listDepartament = new ArrayList<>();

        Department economicDepartament = new Department();
        economicDepartament.setName("экономики");

        Department pravoDepartament = new Department();
        pravoDepartament.setName("права");

        Department healthDepartament = new Department();
        healthDepartament.setName("здравоохранения");

        listDepartament.add(economicDepartament);
        listDepartament.add(pravoDepartament);
        listDepartament.add(healthDepartament);


        List<Person> listPerson = new ArrayList<>();
        List<String> names = new ArrayList<>(List.of("Иван", "Константин", "Степан", "Пётр", "Ирина", "Николай"));


        for (int i = 0; i < 10; i++) {
            Department healthDepartament1 = new Department();
            Person person = new Person();
            person.setName(names.get(ThreadLocalRandom.current().nextInt(names.size())));
            person.setAge(ThreadLocalRandom.current().nextInt(24, 70));
            person.setSalary(ThreadLocalRandom.current().nextInt(25000, 140000));
            person.setDepart(listDepartament.get(ThreadLocalRandom.current().nextInt(listDepartament.size())));
            listPerson.add(person);
        }

        print(listPerson);

        System.out.println("Самый молодой сотрудник: ");
        System.out.println(findMostYoungestPerson(listPerson));
        System.out.println();

        System.out.println("Департамент, в котором работает сотрудник с самой большой зарплатой: ");
        System.out.println(findMostExpensiveDepartment(listPerson));
        System.out.println();

        System.out.println("Группировка сотрудников по департаментам: ");
        System.out.println(groupByDepartment(listPerson));
        System.out.println();

        System.out.println("Группировка сотрудников по названиям департаментов: ");
        System.out.println(groupByDepartmentName(listPerson));
        System.out.println();


        System.out.println("В каждом департаменте найти самого старшего сотрудника");
        System.out.println(getDepartmentOldestPerson(listPerson));
        System.out.println();





    }

//************************************************************************************************
//  РЕШЕНИЕ ДОМАШНЕГО ЗАДАНИЯ:


    /**
     * Найти самого молодого сотрудника
     */
    static Optional<Person> findMostYoungestPerson(List<Person> people) {
        return people.stream().sorted((o1, o2) -> o1.getAge() - o2.getAge()).findFirst();
    }



    /**
     * Найти департамент, в котором работает сотрудник с самой большой зарплатой
     */
    static Optional<String> findMostExpensiveDepartment(List<Person> people) {
        return people.stream()
                .sorted((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()))
                .findFirst().get().getDepart().getName().describeConstable();

    }



    /**
     * Сгруппировать сотрудников по департаментам
     */
    static Map<Department, List<Person>> groupByDepartment(List<Person> people) {
        return people.stream().collect(Collectors.groupingBy(d -> d.getDepart()));
    }




    /**
     * Сгруппировать сотрудников по названиям департаментов
     */
    static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
        return people.stream().collect(Collectors.groupingBy(n -> n.getDepart().getName()));
    }



    /**
     * В каждом департаменте найти самого старшего сотрудника
     */
    static Map<String, Person> getDepartmentOldestPerson(List<Person> people) {
        return people.stream().collect(Collectors.toMap(
            d -> d.getDepart().getName(),
            d -> d,
            (a, d) -> {
                if(a.getAge() < d.getAge()){
                    return d;
                }
                return a;
            }
        ));
    }



    /**
     * *Найти сотрудников с минимальными зарплатами в своем отделе
     * (прим. можно реализовать в два запроса)
     */
//    static List<Person> cheapPersonsInDepartment(List<Person> people)  {
//        Map<String, Person> map = new HashMap<>();
//        map = people.stream().collect(Collectors.groupingBy(d -> d.getDepart().getName());
//
//    }

//************************************************************************************************



    public static void print(List<Person> list){
        System.out.println();
        for (Person p : list) {
            System.out.println(p);
        }
        System.out.println();
    }


    private static class Department {
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return name;
        }
    }



    private static class Person {
        private String name;
        private int age;
        private double salary;
        private Department depart;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public Department getDepart() {
            return depart;
        }

        public void setDepart(Department depart) {
            this.depart = depart;
        }


        @Override
        public String toString() {
            return "Имя: " + name + ", возраст: " + age + ", зарплата: " + salary + ", департамент: " + depart;
        }
    }
}












