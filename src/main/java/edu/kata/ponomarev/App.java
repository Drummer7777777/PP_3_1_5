package edu.kata.ponomarev;

import edu.kata.ponomarev.config.MyConfig;
import edu.kata.ponomarev.models.UserKata;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = annotationConfigApplicationContext.getBean("communication"
                , Communication.class);
        List<UserKata> userKataList1 = communication.userKataList();
        UserKata userKataSave = new UserKata(3L,(byte)25, "Brown", "James");
        UserKata userKataEdit = new UserKata(3L,(byte) 25, "Thomas", "Shelby");
//        List<UserKata> userKataList = communication.userKataList();
        for (UserKata userKata: userKataList1) {
            System.out.println(userKata);
        }
        communication.saveUser(userKataSave);
//        List<UserKata> userKataList2 = communication.userKataList();
//        for (UserKata userKata: userKataList2) {
//            System.out.println(userKata);
//        }
        communication.editUser(userKataEdit);
        communication.deleteUser(3);
    }
}
