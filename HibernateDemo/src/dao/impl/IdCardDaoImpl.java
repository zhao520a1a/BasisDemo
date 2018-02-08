package dao.impl;

import dao.IdCardDao;
import entity.IdCard;
import org.hibernate.HibernateException;
import org.junit.Test;

/**
 * Created by golden on 2017/6/12 0012.
 */
public class IdCardDaoImpl extends BaseDaoImpl<IdCard,Long> implements IdCardDao {


    /**
     * 通过IdCardNo查询用户信息
     */
    @Test
    @Override
    public void queryUserByIdCardNo() {
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            query = session.createQuery("from IdCard where  cardNo='1414010315'");
            IdCard idCard = (IdCard) query.uniqueResult();
            System.out.println("通过IdCardNo查询用户姓名：\n" + idCard.getUser().getUsername());
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }
}
