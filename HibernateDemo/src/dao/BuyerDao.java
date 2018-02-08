package dao;

import entity.Buyer;

/**
 * Created by golden on 2017/6/13 0013.
 */
public interface BuyerDao extends BaseDao<Buyer,Integer> {
    /**
     * 通过买家姓名查询到订单信息
     */
    void queryIndentByBuyerName();
}
