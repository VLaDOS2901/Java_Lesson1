package program;

import enums.QuestionType;
import models.Question;
import models.QuestionItem;
import models.Role;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HiberContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner in = new Scanner(System.in);
            System.out.println("Введіть запитання");
            String que = in.nextLine();
            addQuestion(que, QuestionType.RUDIO_BUTTON);

            int id = 0;
            Session context = HiberContext.getSessionFactory().openSession();
            Query query = context.createQuery("FROM Question");
            List<Question> questions = query.list();
            for (Question question:questions)
                id=question.getId();

            String answText = "";
            boolean isCorrect = false;
            for (int i = 0; i<3; i++)
            {
                System.out.println("Введіть відповідь");
                answText = in.nextLine();
                System.out.println("Ця відповідь правильна? y/n");
                String isCorrectStr = in.nextLine();
                if(isCorrectStr == "y")
                    isCorrect = true;
                else
                    isCorrect = false;
                AddQuestionItem(id,answText,isCorrect);
            }



            context.close(); // закриває підключення
//            AddQuestionItem(1,"1945",false);
//            AddQuestionItem(1,"1986",true);
//            AddQuestionItem(1,"1991",false);
        }catch (Exception ex)
        {
            System.out.println("Виникла помилка " + ex.getMessage());
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
                                        boolean isTrue ) throws SQLException {
        Session session = HiberContext.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Question question = new Question();
        question.setId(questionId);
        QuestionItem qi = new QuestionItem(question, text, isTrue);
        session.save(qi);
        tx.commit();
        session.close();
    }
    private static void testRole()
    {
        Scanner in = new Scanner(System.in);
//        System.out.println("Вкажіть назву ролі");
//        Role role = new Role();
//        String name = in.nextLine();
//        role.setName(name);

        Session context = HiberContext.getSessionFactory().openSession();
//        context.save(role); // зберігає данні
        Query query = context.createQuery("FROM Role");
        List<Role> roles = query.list();
        for (Role role:roles)
            System.out.println(role);
        context.close(); // закриває підключення
//        System.out.println("Role id = " + role.getId());
    }
}
