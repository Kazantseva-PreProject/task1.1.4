package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionDB;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = getSessionDB().getCurrentSession();

        try {
          } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionDB().getCurrentSession();

        try {
            session.beginTransaction();

            // Native SQL для удаления таблицы "user"
            String sql = "DROP TABLE IF EXISTS \"user\"";
            session.createNativeQuery(sql).executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionDB().getCurrentSession();

        try {
            session.beginTransaction();

            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.save(user);

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionDB().getCurrentSession();

        try {
            session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionDB().getCurrentSession();
        List<User> userList = new ArrayList<>();

        try {
            session.beginTransaction();

            String sql = "SELECT * FROM \"user\"";
            NativeQuery<User> query = session.createNativeQuery(sql, User.class);
            userList = query.getResultList();

            session.getTransaction().commit();
        } finally {
            session.close();
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionDB().getCurrentSession();

        try {
            session.beginTransaction();

            String sql = "DELETE FROM \"user\"";
            NativeQuery query = session.createNativeQuery(sql);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
