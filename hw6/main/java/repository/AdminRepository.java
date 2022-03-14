package repository;

import Entity.Account;
import Entity.Employee;

public class AdminRepository implements BaseRepository<Employee> {
    @Override
    public Employee findById(Integer id) {
        try (var session = sessionFactory.openSession()){
            return session.find(Employee.class,id);
        }
    }

    public Employee login (String username , String password){
        var hql = "from Entity.Employee where username = : user and password = :pass";
        try (var session = sessionFactory.openSession()){
            var query =session.createQuery(hql,Employee.class);
            query.setParameter("user",username);
            query.setParameter("pass",password);
            return query.getSingleResult();
        }
    }
}
