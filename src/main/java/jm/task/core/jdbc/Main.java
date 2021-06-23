package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        //Удаление таблицы
        userService.dropUsersTable();
        //Создание таблицы User
        userService.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор.
        userService.saveUser("pavel", "mezin", (byte) 30);
        userService.saveUser("paul", "mezoni", (byte) 31);
        userService.saveUser("paulito", "mezotti", (byte) 32);

        //Получение всех User из базы и вывод в консоль
        userService.getAllUsers();
        System.out.println("Получение всех User из базы");
        System.out.println(userService.getAllUsers());

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();
        System.out.println("таблица очищена");
        System.out.println(userService.getAllUsers());

        //Удаление таблицы
        userService.dropUsersTable();
        System.out.println("Таблица удалена");
    }
}
