package dao.impl;

import dao.BaseDao;
import org.hibernate.*;
import util.HibernateUtil1;

import java.io.Serializable;
import java.util.List;

/**
 * Created by golden on 2017/6/13 0013.
 */
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

    SessionFactory sf = HibernateUtil1.getSessionFactory();
    Session session = HibernateUtil1.getSession();
    Transaction ts = null;
    Query query = null;


    @Override
    public void save(T entity) {
        try {
            ts = session.beginTransaction();
            session.save(entity);
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Override
    public void update(T entity) {
        try {
            ts = session.beginTransaction();
            session.update(entity);
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Override
    public void saveOrupdate(T entity) {
        try {
            ts = session.beginTransaction();
            session.saveOrUpdate(entity);
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }


    @Override
    public void update(String hql, Object[] params) {
        try {
            ts = session.beginTransaction();
            Query query = session.createQuery(hql);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
            query.executeUpdate();
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }


    @Override
    public void delete(T entity) {
        try {
            ts = session.beginTransaction();
            session.delete(entity);
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Override
    public void deleteById(Class<T> entityClass, PK id) {
        try {
            ts = session.beginTransaction();
            session.delete(findById1(entityClass, id));
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Override
    public T findById(Class<T> entityClass, PK id) {
        T t = null;
        try {
            ts = session.beginTransaction();
            t = (T) session.get(entityClass, id);
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
        return t;
    }

    public T findById1  ( Class<T>  entityClass, PK id) {
        return  (T) session.get(entityClass, id);

    }


    @Override
    public List<T> findAll(Class<T> entityClass) {
        List<T> tList = null;
        try {
            ts = session.beginTransaction();
            Criteria criteria = session.createCriteria(entityClass);
            tList = criteria.list();
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
        return tList;
    }

}
