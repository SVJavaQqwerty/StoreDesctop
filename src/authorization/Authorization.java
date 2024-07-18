package authorization;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Authorization {

    public int authentication() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Хотите войти (y) зарегистрироваться (n)");
            String str = scanner.nextLine();

            if ("y".equals(str)) {
                System.out.println("Введите логин: ");
                String login = scanner.nextLine();

                System.out.println("Введите пароль: ");
                String password = scanner.nextLine();
                int respose;
                if ((respose = fileGetAuthentication(login, password)) != -1) {
                    return respose;
                }
            }
            if ("n".equals(str)) {
                System.out.println("Введите логин: ");
                String login = scanner.nextLine();

                System.out.println("Введите пароль: ");
                String password = scanner.nextLine();
                int responce = 0;

                System.out.println("Ваша роль в системе:\n - администратор(a) \n - пользователь(u) ");
                String role = scanner.nextLine();
                System.out.println(role);
                if ("a".equals(role)) {
                    responce = registration(login, password, "1");
                }
                if("u".equals(role)) {
                    responce = registration(login, password, "2");
                }

                if (responce != 0) {
                    return responce;
                }
            }
        }
    }

    private int registration(String login, String password, String role) {
        try (Scanner file = new Scanner(new FileReader("bd.txt"))) {
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] logPass = line.split(":");
                if (logPass[0].equals(login)) {
                    System.err.println("Логин уже используется");
                    return 0;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        try (FileWriter writer = new FileWriter("bd.txt", true)) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(login + ":" + password + ":" + role + "\n");
            bufferedWriter.close();
            writer.close();
            return Integer.parseInt(role);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private int fileGetAuthentication(String login, String password) {
        try (Scanner file = new Scanner(new FileReader("bd.txt"))) {
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] logPass = line.split(":");
                if (logPass[0].equals(login)) {
                    if (logPass[1].equals(password)) {
                        return Integer.parseInt(logPass[2]);
                    } else {
                        System.out.println("Неверный пароль");
                    }
                }
            }
            System.out.println("Такого логина нет в системе");
            return -1;
        } catch (IOException e) {
            return -1;
        }
    }
}
