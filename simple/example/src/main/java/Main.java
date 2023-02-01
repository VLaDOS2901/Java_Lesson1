import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
//        testArray();
//        sortArray();
        //шлях підключення до бази данних
        String strCon="jdbc:mariadb://localhost:3306/java_spu013";
        //перевіряє чи підключення є успішним
        try(Connection con = DriverManager.getConnection(strCon, "root", "")){
            System.out.println("Connection is good");
            Boolean continueExe = true;
            System.out.println("1. Вивести список категорі\n" +
                    "2. Додати категорію\n" +
                    "3. Вихід");
            while (continueExe){
                System.out.println("Виберіть дію");
                Scanner input = new Scanner(System.in);
                String str = input.nextLine();
                switch (str){
                    case "1":
                        //текст запиту на базу данних
                        String query="SELECT * FROM categories";
                        //готує запит до виконання
                        PreparedStatement command = con.prepareStatement(query);
                        //виконує запит
                        ResultSet resultSet = command.executeQuery();
                        //next - читає кожен рядок окремо
                        while(resultSet.next())
                        {
                            System.out.println("Id: "+ resultSet.getString("id"));
                            System.out.println("Name: "+ resultSet.getString("name"));
                        }
                        break;
                    case "2":
                        System.out.println("Введіть назву категорії");
                        String name = input.nextLine();
                        String insertQuery="INSERT INTO categories (name, datecreate) VALUES ('"+ name +"' , NOW());";
                        PreparedStatement command2 = con.prepareStatement(insertQuery);
                        command2.executeQuery();
                        System.out.println("Додано нову категорію - " + name);
                        break;
                    case "3":
                        System.out.println("Вихід");
                        continueExe = false;
                        break;
                    default:
                        break;

                }
            }


        }
        catch (Exception ex){
            System.out.println("Error connection " + ex.getMessage());
        }


//        Person p = new Person("Іван", "Музичко"); // створення нового об'єкту типу Person
//        System.out.println(p);
//        p.setFirstName("Василь"); // Виклик сетера
//        System.out.println(p);


//        switch if || && == != > < >= <= - аналогічно як в С#
        //random - генерує число від 0 до 1 дробове
//        System.out.println(Math.random());
//        String str = input.nextLine(); // Приймає значення з консолі
//        int a = 10;
//        System.out.println("Вкажіть значення а");
//        a=input.nextInt();
//        double short boolean byte char float
//        System.out.println("Hello world!!! " + a + " " + str); //виводить данні у консоль
    }

    public static void sortArray()
    {
        Person [] list = {
                new Person("Іван", "Музичко"),
                new Person("Оксана", "Марко"),
                new Person("Василь", "Шлунок"),
                new Person("Олег", "Закуска"),
                new Person("Вікторія", "Закуска "),
                new Person("Артур", "Закуска")
        };
        for (Person p : list)
            System.out.println(p);

        Arrays.sort(list/*, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        }*/);
        System.out.println("Сортований список");
        for (Person p : list)
            System.out.println(p);
    }
    public static void testArray()
    {
        Scanner input = new Scanner(System.in); // Ініціалізує змінну, за допомогою якаої можна вводити данні у консоль
        int n = 10;
        int[] mas = new int[n];
        for (int i = 0; i < n; i++)
            mas[i] = getRandomNumber(-10, 20);
        for (int item : mas)
            System.out.printf("%d\t", item);
        int count = 0;
        for (int item : mas) {
            if(item>=0)
                count++;
        }
        System.out.println("\nКількість додатних чисел " + count);
    }
    //Генерує рандомні числа в межах від min до max
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
