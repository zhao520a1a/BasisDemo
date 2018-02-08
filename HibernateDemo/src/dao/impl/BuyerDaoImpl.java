package dao.impl;

import dao.BuyerDao;
import entity.Buyer;
import entity.Indent;
import org.hibernate.HibernateException;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by golden on 2017/6/11 0011.
 */
public class BuyerDaoImpl extends BaseDaoImpl<Buyer,Integer> implements BuyerDao {


    @Test
    @Override
    public void queryIndentByBuyerName() {
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            query = session.createQuery("from Buyer where buyerName='Golden'");
            List<Buyer> indentList = query.list();
            for (int i = 0; i < indentList.size(); i++) {
                Buyer buyer =  indentList.get(i);
                Set<Indent>  indentSet =  buyer.getIndentList();
                System.out.println( "通过买家姓名查询到订单信息:" );
                for(Indent indent:indentSet){
                    System.out.println(indent);
                }
            }
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }



}
