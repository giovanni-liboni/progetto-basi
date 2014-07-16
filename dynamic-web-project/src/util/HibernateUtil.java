package util;

import org.hibernate.cfg.Configuration;
import org.hibernate.*;

/**
 * Hibernate Utility class with a convenient method to get Session Factory 
 * object.
 *
 * @author eom
 */

public class HibernateUtil {

      private static final SessionFactory sessionFactory;

      static {
          try {
              // Create the SessionFactory from hibernate.cfg.xml
              sessionFactory = new Configuration().configure().buildSessionFactory();
          } catch (Throwable ex) {
              // Make sure you log the exception, as it might be swallowed
              System.err.println("Initial SessionFactory creation failed." + ex);
              throw new ExceptionInInitializerError(ex);
          }
      }

      public static SessionFactory getSessionFactory() {
          return sessionFactory;
      }
}
