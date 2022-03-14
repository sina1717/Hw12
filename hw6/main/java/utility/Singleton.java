package utility;



import Entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Singleton {

    private Singleton() {}


        private static SessionFactory INSTANCE;

        static {
            StandardServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

            // registry is useful for creating SessionFactory
            // SessionFactory is a heavyweight object.
            // SessionFactory is thread safe.
            // SessionFactory is immutable.
            Metadata metadata =
                    new MetadataSources(serviceRegistry)
                            .addAnnotatedClass(Customer.class)
                            .addAnnotatedClass(Employee.class)
                            .addAnnotatedClass(Account.class)
                            .addAnnotatedClass(BankBranch.class)
                            .addAnnotatedClass(CreditCard.class)
                            .buildMetadata();

            INSTANCE = metadata
                    .getSessionFactoryBuilder()
                    .build();
        }


    public static SessionFactory getInstance() {
        return INSTANCE;
    }
}
