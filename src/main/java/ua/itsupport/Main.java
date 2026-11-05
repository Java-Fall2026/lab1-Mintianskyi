package ua.itsupport;

import ua.itsupport.model.ITTicket;
import ua.itsupport.model.ITUser;
import ua.itsupport.model.Priority;
import ua.itsupport.model.Technician;
import ua.itsupport.model.WorkLog;
import ua.itsupport.util.ITSupportUtils;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("1. Створення record та звернення до компонентів");
        ITUser user1 = new ITUser("alex.dou@corp.net", "Олексій Дорошенко", "Фінансовий відділ");
        System.out.println("Користувач (record): " + user1);
        System.out.println("Виклик компонента user1.email(): " + user1.email());
        System.out.println("Виклик компонента user1.fullName(): " + user1.fullName());

        System.out.println("\n2. Робота з Enum");
        System.out.println("Перебір усіх констант Priority через values():");
        for (Priority p : Priority.values()) {
            System.out.println(" - " + p + " (коефіцієнт: " + p.getWeightFactor() + ")");
        }

        try {
            Priority.valueOf("URGENT");
      } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено помилку valueOf на невідомій константі: " + e.getMessage());
      }

        System.out.println("\n3. Демонстрація switch-виразів");
        ITTicket ticket1 = new ITTicket(
                "INC-101",
                user1,
                Priority.CRITICAL,
                "Збій серверної бази даних",
                LocalDate.now().minusDays(3),
                LocalDate.now(),
                "CLOSED"
        );
        System.out.println("Switch 1 (опис Priority.CRITICAL): " + ITSupportUtils.getPriorityDescription(ticket1.priority()));
        System.out.println("Switch 2 (опис дії за станом CLOSED): " + ITSupportUtils.getActionDescriptionByState(ticket1.state()));

        System.out.println("\n4. Валідація у компактному конструкторі record");
        try {
            new ITUser("   ", "Помилковий Користувач", "IT");
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено виняток з компактного конструктора ITUser: " + e.getMessage());
        }

        System.out.println("\n5. Автоматичні equals та hashCode для record");
        ITUser userDuplicate = new ITUser("alex.dou@corp.net", "Олексій Дорошенко", "Фінансовий відділ");
        System.out.println("user1.equals(userDuplicate): " + user1.equals(userDuplicate));
        System.out.println("user1.hashCode() == userDuplicate.hashCode(): " + (user1.hashCode() == userDuplicate.hashCode()));

        System.out.println("\n6. Вивід через згенерований toString()");
        Technician tech1 = new Technician("TECH-01", "Олександр Мельник", 4);
        WorkLog workLog = new WorkLog(ticket1, tech1, LocalDate.now().minusDays(1), 6.0);
        
        System.out.println("Вивід ITUser: " + user1);
        System.out.println("Вивід ITTicket: " + ticket1);
        System.out.println("Вивід WorkLog: " + workLog);
        
        double cost = ITSupportUtils.workCost(workLog);
        System.out.println("Розрахована вартість робіт: " + ITSupportUtils.formatMoneyValue(cost));
    }
}