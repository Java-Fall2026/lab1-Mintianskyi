package ua.itsupport;

import ua.itsupport.model.ITUser;
import ua.itsupport.model.Technician;
import ua.itsupport.model.ITTicket;
import ua.itsupport.model.WorkLog;
import ua.itsupport.util.ITSupportUtils;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. Створення об'єктів");
        ITUser user1 = new ITUser("  USER1@MAIL.COM ", " Ivan Franko ", " DevOps ");
        Technician tech1 = new Technician("TECH-001", " Taras Shevchenko ", 3);
        ITTicket ticket1 = new ITTicket("INC-100", user1, "high", " Fix server issue ", LocalDate.now().minusDays(4), null, "open");
        WorkLog log1 = WorkLog.of(ticket1, tech1, LocalDate.now().minusDays(2), 5.0);

        System.out.println(user1);
        System.out.println(tech1);
        System.out.println(ticket1);
        System.out.println(log1);

        System.out.println("\n2. Демонстрація нормалізації");
        System.out.println("Email: " + user1.getEmail());
        System.out.println("Priority: " + ticket1.getPriority());

        System.out.println("\n3. Перевірка помилок (try/catch)");
        try {
            new ITUser("", "Name", "Dept");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 1: " + e.getMessage());
        }

        try {
            ticket1.setPriority("SUPER_HIGH");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 2: " + e.getMessage());
        }

        try {
            WorkLog.of(ticket1, tech1, LocalDate.now().minusDays(1), 30.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 3: " + e.getMessage());
        }

        System.out.println("\n4. Обчислювані методи");
        System.out.println("Днів у роботі (до закриття): " + ITSupportUtils.resolutionDays(ticket1));
        ticket1.setResolvedDate(LocalDate.now());
        ticket1.setState("RESOLVED");
        System.out.println("Днів у роботі (після закриття): " + ITSupportUtils.resolutionDays(ticket1));
        System.out.println("Вартість роботи: $" + ITSupportUtils.workCost(log1));
    }
}