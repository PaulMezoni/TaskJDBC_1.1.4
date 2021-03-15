package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

public class Util {

    private static SessionFactory sessionFactory;

    public Util() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(URL, "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false");
                settings.put(USER, "root");
                settings.put(PASS, "rootroot");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addPackage("ru.mysql.db");

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Исключение! sessionFactory" + e);
            }
        }
        return sessionFactory;
    }

    private Connection connection;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connect BASE");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Disconnect BASE");
        }
        return connection;
    }
}

////
////    private static final String URL = "jdbc:mysql//localhost:3306/mydbtest";
////    private final static String DB_URL = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
////            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
////    private static final String DB_USERNAME = "root";
////    private static final String DB_PASSWORD = "root0000";
////
////    private Connection connection;
////
////    public Connection getConnection() {
////        try {
////            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
////            System.out.println("Connect BASE");
////        } catch (SQLException e) {
////            e.printStackTrace();
////            System.out.println("Disconnect BASE");
////        }
////        return connection;
////    }
////}
