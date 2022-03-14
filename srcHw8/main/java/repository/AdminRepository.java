package repository;

import eceptions.NotFoundException;
import eceptions.SaveException;
import entity.Admin;
import org.hibernate.SessionFactory;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminRepository implements UserInterface<Admin> {

    private static final SessionFactory sessionFactory = SingletonSessionFactory.getInstance();
    @Override
    public int save(Admin admin) {
//        String sql = "insert into admins(username, password, national_code) VALUES (?,?,?) ";
//        int id =0;
//        try {
//            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1,admin.getUsername());
//            preparedStatement.setString(2, admin.getPassword());
//            preparedStatement.setString(3,admin.getNationalCode());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            resultSet.next();
//            id = resultSet.getInt(1);
//            preparedStatement.close();
//            resultSet.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
        try(var session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.save(admin);
                session.getTransaction().commit();
                return admin.getId();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new SaveException("customer can not save");
            }
        }
    }

    @Override
    public void update(Admin admin) {
//        String sql = "UPDATE admins set username = ? , password = ? , national_code = ? where id =?";
//        try {
//            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
//            preparedStatement.setString(1,admin.getUsername());
//            preparedStatement.setString(2, admin.getPassword());
//            preparedStatement.setString(3,admin.getNationalCode());
//            preparedStatement.setInt(4,admin.getId());
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            throw new SaveException("admin can not save");
//        }

        try(var session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(admin);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
//                throw new SaveException("customer can not save");
            }
        }
    }

    @Override
    public List<Admin> findAll() {
//        String sql = "select * from admins";
//        List<Admin> adminList= new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                adminList.add(new Admin(
//                        resultSet.getInt("id"),
//                        resultSet.getString("username"),
//                        resultSet.getString("password"),
//                        resultSet.getString("national_code")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return adminList;
        String hql = " From entity.Admin  where dtype = Admin ";
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery(hql, Admin.class);
            return query.list();
        }
    }

    @Override
    public void delete(int id) {
//        String sql = "delete from admins where id = ?";
//        try {
//            PreparedStatement preparedStatement =MyConnection.getConnection().prepareStatement(sql);
//            preparedStatement.setInt(1,id);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try(var session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.delete(new Admin(id,"","","1234567890"));
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public Admin findById(int id) {
//        String sql = "select * from admins where id = ?";
//        Admin admin=null;
//        try {
//            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
//            preparedStatement.setInt(1,id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()){
//                admin = new Admin(
//                        resultSet.getInt("id"),
//                        resultSet.getString("username"),
//                        resultSet.getString("password"),
//                        resultSet.getString("national_code")
//                );
//            }
//        } catch (SQLException e) {
//            throw new NotFoundException("admin not found");
//        }
//        return admin;

        try (var session = sessionFactory.openSession()){
            return session.find(Admin.class,id);
        }
    }

    @Override
    public Admin login(String username, String password) {
//        String sql = "select * from admins where username = ? and password = ?";
//        try {
//            PreparedStatement preparedStatement  =MyConnection.getConnection().prepareStatement(sql);
//            preparedStatement.setString(1,username);
//            preparedStatement.setString(2,password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()){
//                return new Admin( resultSet.getInt("id"),
//                        resultSet.getString("username"),
//                        resultSet.getString("password"),
//                        resultSet.getString("national_code")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        String hql = " From entity.Admin a  where dtype = Admin and a.username = :user and a.password = :pass";
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery(hql, Admin.class);
            query.setParameter("user",username);
            query.setParameter("pass",password);
            return query.getSingleResult();
        }
    }
}
