package dao.impl;

import dao.UserDao;
import entity.User;
import org.hibernate.HibernateException;
import org.junit.Test;

public class UserDaoImpl  extends BaseDaoImpl<User,Long> implements UserDao {


    @Test
    @Override
    public void queryIdCardByUserName() {
        try {
            ts = session.beginTransaction();
            query = session.createQuery("from User where id=15");
              User user = (User) query.uniqueResult();
            System.out.println( "通过用户姓名查询IdCardNo为：\n" + user.getIdCard().getCardNo() );
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }


}
