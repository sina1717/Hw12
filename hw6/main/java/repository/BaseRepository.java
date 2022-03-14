package repository;

import org.hibernate.SessionFactory;
import utility.Singleton;

public interface BaseRepository<T> {
     SessionFactory sessionFactory = Singleton.getInstance();

    default T save(T t){
        try(var session =sessionFactory.openSession() ) {
            try {
                session.beginTransaction();
                session.save(t);
                session.getTransaction().commit();
            }catch (Exception e){
                session.getTransaction().rollback();
            }
        }
        return t;
    }
    default void update(T t){
        try(var session =sessionFactory.openSession() ) {
            try {
                session.beginTransaction();
                session.update(t);
                session.getTransaction().commit();
            }catch (Exception e){
                session.getTransaction().rollback();
            }
        }
    }

    default void delete(T t){
        try(var session =sessionFactory.openSession() ) {
            try {
                session.beginTransaction();
                session.delete(t);
                session.getTransaction().commit();
            }catch (Exception e){
                session.getTransaction().rollback();
            }
        }
    }


     T findById(Integer id);

}
