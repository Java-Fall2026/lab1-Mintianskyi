package ua.itsupport;

import ua.itsupport.model.ITTicket;
import ua.itsupport.model.ITUser;
import ua.itsupport.model.Technician;
import ua.itsupport.model.WorkLog;
import ua.itsupport.util.ITSupportUtils;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("1. Створення конструктором та фабричним методом");
        Technician tech1 = new Technician("TECH-01", "Олександр Мельник", 3);
        System.out.println("Технік (конструктор): " + tech1);

        ITUser user1 = ITUser.of("  Alex.DOU@Corp.NET  ", "Олексій Дорошенко", "Фінансовий відділ");
        System.out.println("Користувач (фабрика of()): " + user1);

        System.out.println("\n2. Нормалізація в дії");
        System.out.println("Email до: '  Alex.DOU@Corp.NET  ' -> після: '" + user1.getEmail() + "'");
        
        ITTicket ticket1 = new ITTicket(
                "INC-101",
                user1,
                " critical ", 
                "Збій принтера",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(1),
                " closed "    
        );
        System.out.println("Priority після нормалізації: '" + ticket1.getPriority() + "'");
        System.out.println("State після нормалізації: '" + ticket1.getState() + "'");

        System.out.println("\n3. Демонстрація formatMoney та capitalize");
        System.out.println("capitalize('іван петренко'): '" + ITSupportUtils.capitalizeText("іван петренко") + "'");
        WorkLog workLog = WorkLog.of(ticket1, tech1, LocalDate.now().minusDays(2), 4.5);
        double cost = ITSupportUtils.workCost(workLog);
        System.out.println("Розрахунок вартості: " + ITSupportUtils.formatMoneyValue(cost));

        System.out.println("\n4. Порушення правил валідації (try/catch)");
        try {
            new Technician("TECH-99", "Іван", 8);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 1: " + e.getMessage());
        }

        try {
            new ITTicket("INC-998", user1, "   ", "Тест", LocalDate.now(), null, "OPEN");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 2: " + e.getMessage());
        }

        System.out.println("\n5. Невдалий виклик сеттера та збереження старого значення");
        System.out.println("Початковий skillLevel: " + tech1.getSkillLevel());
        try {
            tech1.setSkillLevel(10);
        } catch (IllegalArgumentException e) {
            System.out.println("Сеттер відхилив значення: " + e.getMessage());
        }
        System.out.println("SkillLevel після невдалої спроби не змінився: " + tech1.getSkillLevel());

        System.out.println("\n6. Порівняння ==/equals/hashCode");
        ITUser user2 = ITUser.of("alex.dou@corp.net", "Інше Ім'я", "Інший Департамент");
        ITUser userDifferent = ITUser.of("marta@corp.net", "Марта", "HR");

        System.out.println("user1 == user2: " + (user1 == user2));
        System.out.println("user1.equals(user2): " + user1.equals(user2));
        System.out.println("user1.hashCode() == user2.hashCode(): " + (user1.hashCode() == user2.hashCode()));
        System.out.println("user1.equals(userDifferent): " + user1.equals(userDifferent));

        System.out.println("\n7. Вивід сутностей через toString() (усі поля включено)");
        System.out.println(user1);
        System.out.println(tech1);
        System.out.println(ticket1);
        System.out.println(workLog);
    }
}