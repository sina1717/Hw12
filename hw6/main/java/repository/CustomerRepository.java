package repository;

import Entity.Account;
import Entity.Customer;

public class CustomerRepository implements BaseRepository<Customer> {

    @Override
    public Customer findById(Integer id) {
        try (var session = sessionFactory.openSession()){
            return session.find(Customer.class,id);
        }
    }

    public Customer findByNationalCode(String nationalCode){
        String hql = "from Entity.Customer where nationalCode = :national";
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery(hql,Customer.class);
            query.setParameter("national",nationalCode);
            return query.getSingleResult();
        }
    }
}
