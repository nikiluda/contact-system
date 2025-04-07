import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final int MAX_NUM = 100;
    private static String[] names = new String[MAX_NUM];
    private static String[] phoneNumbers = new String[MAX_NUM];
    private static int countContacts = 0;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1.Добавить контакт\n2.Просмотреть контакты\n3.Найти контакт\n4.Удалить контакт\n5.Выйти");
            System.out.print("Выберите действие: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 5");
                continue;
            }

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    outputContacts();
                    break;
                case 3:
                    findContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Выход из программы");
                    return;
                default:
                    System.out.println("Некорректное значение");
            }
        }
    }

    private static void addContact() {
        while (true) {
            if (countContacts >= MAX_NUM) {
                System.out.println("В списке контактов нет мест");
                return;
            }

            System.out.print("Введите имя: ");
            String name = scanner.nextLine().trim();

            System.out.print("Введите номер телефона: ");
            String phoneNumber = scanner.nextLine().trim();

            if (name.isEmpty() || phoneNumber.isEmpty()) {
                System.out.println("Ошибка: имя и номер телефона не могут быть пустыми.");
                continue;
            }

            names[countContacts] = name;
            phoneNumbers[countContacts] = phoneNumber;
            countContacts++;
            System.out.println("Контакт добавлен!");

            System.out.print("Добавить ещё один контакт? (1 - да, 0 - вернуться в меню): ");
            String continueChoice = scanner.nextLine().trim();

            if (continueChoice.equals("0")) {
                return;
            } else if (!continueChoice.equals("1")) {
                System.out.println("Некорректный ввод, используйте 1 или 0");
            }
        }
    }

    private static void outputContacts() {
        if (countContacts == 0) {
            System.out.println("Список контактов пуст");
            return;
        }

        System.out.println("\nСписок контактов:");
        for (int i = 0; i < countContacts; i++) {
            System.out.println((i+1) + ". " + names[i] + " - " + phoneNumbers[i]);
        }
    }

    private static void findContact() {
        System.out.println("Введите имя контакта: ");
        String findName = scanner.nextLine().trim().toLowerCase();
        boolean status = false;

        if (countContacts == 0) {
            System.out.println("Список контактов пуст");
            return;
        }

        for (int i=0;i< countContacts;i++) {
            if (names[i].toLowerCase().contains(findName)) {
                System.out.println("Номер телефона данного человека: "+phoneNumbers[i]);
                return;
            }
        }
        if (!status) {
            System.out.println("Контакт не найден");
        }
    }

    private static void deleteContact() {
        boolean status = false;
        System.out.println("Введите контакт,который хотите удалить: ");
        String deleteName = scanner.nextLine().trim().toLowerCase();

        for (int i=0;i<countContacts;i++) {
            if (names[i].toLowerCase().contains(deleteName)) {
                for(int j=i;j<countContacts-1;j++) {
                    names[j]=names[j+1];
                    phoneNumbers[j]=phoneNumbers[j+1];

                }
                names[countContacts - 1] = null;
                phoneNumbers[countContacts - 1] = null;
                countContacts--;
            }
            System.out.println("Контак успешно удалён");
            return;
        }
        if (!status) {
            System.out.println("Контакт не найден");
        }
    }
}