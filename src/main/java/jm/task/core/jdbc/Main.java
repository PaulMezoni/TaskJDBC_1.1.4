package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Util util = new Util();
        util.getConnection();

        UserService userService = new UserServiceImpl();

        //Создание таблицы User
        userService.dropUsersTable();
        userService.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор.
        try (BufferedReader scanner = new BufferedReader(new InputStreamReader(in))) {
            for (int i = 0; i < 4; i++) {
                out.print("Name: ");
                String name = scanner.readLine();

                out.print("Lastname: ");
                String lastname = scanner.readLine();

                out.print("Age: ");
                byte age = Byte.parseByte(scanner.readLine());

                userService.saveUser(name, lastname, age);
                out.println("User с именем – " + name + " добавлен в базу данных");
            }
        }
        //Получение всех User из базы и вывод в консоль
        out.println("Получение всех User из базы");
        out.println(userService.getAllUsers());

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();
        out.println("таблица очищена");
        out.println(userService.getAllUsers());

        //Удаление таблицы
        userService.dropUsersTable();
        out.println("Таблица удалена");
    }
}
