package repository;

import entity.Admin;
import entity.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SingletonSessionFactory {
    private static SessionFactory INSTANCE;

    static {
        StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata =
                new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(Customer.class)
                        .addAnnotatedClass(Admin.class)
                        .buildMetadata();

        INSTANCE = metadata
                .getSessionFactoryBuilder()
                .build();
    }


    public static SessionFactory getInstance() {
        return INSTANCE;
    }
}
