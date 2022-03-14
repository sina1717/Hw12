package repository;

import eceptions.NotFoundException;
import eceptions.SaveException;
import entity.Customer;
import entity.Order;
import org.hibernate.SessionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements CustomerInterface{
    private static SessionFactory sessionFactory = SingletonSessionFactory.getInstance();
    @Override
    public int save(Customer customer) {
//        String sql ="insert into customer (username, password, address) values (?,?,?)";
//        try {
//            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1,customer.getUsername());
//            preparedStatement.setString(2,customer.getPassword());
//            preparedStatement.setString(3,customer.getAddress());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            resultSet.next();
//            return resultSet.getInt(1);
//        } catch (SQLException e) {
//            throw new SaveException("customer can not save");
//        }
        try(var session = sessionFactory.openSession()){
            try {
                session.beginTransaction();
                session.save(customer);
                session.getTransaction().commit();
                return customer.getId();
            }catch (Exception e){
                session.getTransaction().rollback();
                throw new SaveException("customer can not save");
            }

        }
    }

    @Override
    public void update(Customer customer) {
//        String sql = "update customer set username = ? , password = ? , address = ? where id = ? ";
//        try {
//            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
//            preparedStatement.setString(1,customer.getUsername());
//            preparedStatement.setString(2,customer.getPassword());
//            preparedStatement.setString(3,customer.getAddress());
//            preparedStatement.setInt(4,customer.getId());
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try(var session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(customer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new SaveException("customer can not save");
            }
        }
    }

    @Override
    public List<Customer> findAll() {
        String sql = "select * from users";
        List<Customer> customerList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customerList.add(new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public void delete(int id) {
//        String  sql = "delete from customer where id = ?";
//        try {
//            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
//            preparedStatement.setInt(1,id);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try(var session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.find(Customer.class,id);
                session.delete(session.find(Customer.class,id));
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
//                throw new SaveException("customer can not save");
            }
        }
    }

    @Override
    public Customer findById(int id) {
//        String sql = "select * from customer where id = ? ";
//        Customer customer = null;
//        try {
//            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                customer = new Customer(
//                        resultSet.getInt("id"),
//                        resultSet.getString("username"),
//                        resultSet.getString("password"),
//                        resultSet.getString("address")
//                );
//            }
//        } catch (SQLException e) {
//            throw new NotFoundException("customer not found");
//        }
//        return customer;
        try(var session = sessionFactory.openSession()) {
                return session.find(Customer.class,id);
        }

    }

    @Override
    public List<Order> findShoppingCardByUserId(int id) {
        String sql = "select * from orders where  customer_id = ? ";
        List<Order> orderList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                orderList.add(new Order(
                        resultSet.getInt("id"),
                        new ProductRepository().findById(resultSet.getInt("product_id")),
                        new CustomerRepository().findById(resultSet.getInt("customer_id")),
                        new ShoppingCardRepository().findById(resultSet.getInt("shopping_card_id"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Customer login(String username, String password) {
        String sql = "select * from users where username = ? and password = ?";
        try {
            PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("address")
                );
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
