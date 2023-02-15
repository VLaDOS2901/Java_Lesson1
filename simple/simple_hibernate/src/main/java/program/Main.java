package program;

import enums.QuestionType;
import models.*;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HiberContext;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //AddUserAndRoles();
        //addCategory("Ноутбуки","1.jpg");
//        addProduct();
        try (Session context = HiberContext.getSessionFactory().openSession()) {


        }


        /*try{
            Scanner in = new Scanner(System.in);


            boolean cycle = true;
            while (cycle)
            {
                System.out.println("Виберіть дію \n 1)Пройти тест \n 2)Додати нове запитання \n 3)Вийти");
                int action = Integer.parseInt(in.nextLine());
                switch (action)
                {
                    case 1:
                        Session context = HiberContext.getSessionFactory().openSession();
                        Query query = context.createQuery("FROM Question");
                        List<Question> questions = query.list();
                        int questionCount = 0;
                        int counter = 0;
                        for (Question question:questions)
                        {
                            System.out.println(question.getText());

                            Query query2 = context.createQuery("FROM QuestionItem where question.id = " + question.getId());
                            List<QuestionItem> answers = query2.list();
                            int i = 1;
                            int correctAnsw = 0;
                            for(QuestionItem answer:answers) {
                                System.out.println(i + ")"+answer.getText());
                                if(answer.isTrue() == true)
                                    correctAnsw= i;
                                i++;
                            }
                            System.out.println("Введіть відповідь");
                            int answ = Integer.parseInt(in.nextLine());
                            if (answ == correctAnsw) {
                                counter++;
                            }
                            questionCount++;
                        }
                        System.out.println("Your result is " + counter + "/" + questionCount);
                        break;
                    case 2:
                        // Додати нове запитання
                        System.out.println("Введіть запитання");
                        String que = in.nextLine();
                        addQuestion(que, QuestionType.RUDIO_BUTTON);

                        int id = 0;
                        Session context2 = HiberContext.getSessionFactory().openSession();
                        Query query2 = context2.createQuery("FROM Question");
                        List<Question> questions2 = query2.list();
                        for (Question question:questions2)
                            id=question.getId();

                        String answText = "";
                        boolean isCorrect = false;
                        for (int i = 0; i<3; i++)
                        {
                            System.out.println("Введіть відповідь");
                            answText = in.nextLine();
                            System.out.println("Ця відповідь правильна? y/n");
                            String isCorrectStr = in.nextLine();
                            isCorrectStr = isCorrectStr.toLowerCase();
                            if(isCorrectStr.contains("y"))
                                isCorrect = true;
                            else if(isCorrectStr.contains("n"))
                                isCorrect = false;
                            AddQuestionItem(id,answText,isCorrect);
                        }
                        context2.close(); // закриває підключення
                        break;
                    case 3:
                        cycle = false;
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception ex)
        {
            System.out.println("Виникла помилка " + ex.getMessage());
        }*/
    }
    private static void addProduct(){ //метод, який додає новий продукт
        try (Session context = HiberContext.getSessionFactory().openSession()) {
            Transaction tx = context.beginTransaction();
            var cat = context.get(Category.class, 1);

            Product p = new Product(new Date(), false, "Молоток", "Для цвяхів", cat);
            context.save(p);
            ProductImage pi1 = new ProductImage(new Date(), false, "1.jpg", 1, p);
            ProductImage pi2 = new ProductImage(new Date(), false, "2.jpg", 2, p);
            context.save(pi1);
            context.save(pi2);
            tx.commit();
        }

    }
    private static void addCategory(String name, String image){
        try (Session context = HiberContext.getSessionFactory().openSession()) {
            Category c =  new Category(name, image, new Date(), false);
            context.save(c);
        }
    }
    private static void AddUserAndRoles() { //метод, який додає нову роль і користувача
        try (Session context = HiberContext.getSessionFactory().openSession()) {
            Transaction tx = context.beginTransaction();
            Role role = new Role();
            role.setName("Мурчик");
            context.save(role);
            User user = new User("Іван", "Бомбардир", "ivan@gmail.com", "+380165784596", "23456");
            context.save(user);
            UserRole ur = new UserRole();
            ur.setRole(role);
            ur.setUser(user);
            context.save(ur);
            tx.commit();
        }
    }

    private static void addQuestion(String text, QuestionType type) throws SQLException {
        Session context = HiberContext.getSessionFactory().openSession(); // підключаємся до БД
        Transaction tx = context.beginTransaction();
        Question q = new Question();
        q.setText(text);
        q.setQuestionType(type);
        context.save(q);
        tx.commit();
        context.close(); // закриваємо підключення до БД
    }


    private static void AddQuestionItem(int questionId, String text,
                                        boolean isTrue) throws SQLException {
        Session session = HiberContext.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Question question = new Question();
        question.setId(questionId);
        QuestionItem qi = new QuestionItem(question, text, isTrue);
        session.save(qi);
        tx.commit();
        session.close();
    }

    private static void testRole() {
        Scanner in = new Scanner(System.in);
//        System.out.println("Вкажіть назву ролі");
//        Role role = new Role();
//        String name = in.nextLine();
//        role.setName(name);

        Session context = HiberContext.getSessionFactory().openSession();
//        context.save(role); // зберігає данні
        Query query = context.createQuery("FROM Role");
        List<Role> roles = query.list();
        for (Role role : roles)
            System.out.println(role);
        context.close(); // закриває підключення
//        System.out.println("Role id = " + role.getId());
    }
}
