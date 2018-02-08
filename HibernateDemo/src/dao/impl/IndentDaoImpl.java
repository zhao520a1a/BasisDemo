package dao.impl;

import dao.IndentDao;
import entity.Indent;
import org.hibernate.HibernateException;
import org.junit.Test;

/**
 * Created by golden on 2017/6/11 0011.
 */
public class IndentDaoImpl extends BaseDaoImpl<Indent,Integer> implements IndentDao {


    @Test
    @Override
    public void queryBuyerByIndentNO() {
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            query = session.createQuery("from Indent where indentNo='HYD-1-54'");
            Indent indent = (Indent) query.uniqueResult();
            System.out.println("通过订单编号查询到买家信息:\n" + indent.getBuyer());
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }
}
