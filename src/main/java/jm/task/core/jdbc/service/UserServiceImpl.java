package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        System.out.println("Таблица users успешно удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("Пользователь: " + name + " " + lastName + " " + age + " успешно добавлен в таблицу");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.println("Пользователь с id: " + id + " успешно удалён");
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("Таблица users успешно очищена");
    }
}
