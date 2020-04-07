/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;
import org.hibernate.*;
import org.hibernate.cfg.*;


/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author j.sonko
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
    //        log.error("Initial SessionFactory creation failed." ,ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
public static final ThreadLocal session = new ThreadLocal();
    public static Session currentSession()
    {
      Session s =(Session)session.get();
      if (s==null)
      {
          s=sessionFactory.openSession();
          session.set(s);
      }
        return s;
    }
public static void closeSession()
{
    Session s = (Session) session.get();
    if (s!=null)
        s.close();
    session.set(null);
}
}
