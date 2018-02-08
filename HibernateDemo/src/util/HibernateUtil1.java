package util;

/**
 * 单例模式：创建SessionFactory对象，获得Session对象；
 * 使用 getCurrentSession()来保证使用Session时的线程安全；
 * Created by golden on 2017/4/27 0027.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil1 {
    private static Configuration cfg;
    private static SessionFactory factory; //它是线程安全的

    static {
        cfg = new Configuration().configure();
//    Hibernate4中过期了：    factory = cfg.buildSessionFactory();
        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        factory = cfg.buildSessionFactory(sr);
    }

    private HibernateUtil1() {
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
//        return factory.openSession();  /*线程不安全,需手动关闭*/
        return factory.getCurrentSession();  /*线程安全：一个线程--一个Session,需要在配置文件中配置，不需要手动关闭*/
    }

    public static void closeSession(Session session) {
        if (session != null) {
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}
