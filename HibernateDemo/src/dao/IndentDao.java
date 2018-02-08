package dao;

import entity.Indent;

/**
 * Created by golden on 2017/6/13 0013.
 */
public interface IndentDao  extends BaseDao<Indent,Integer>{

    /**
     * 通过订单关联查询到买家信息
     */
    void queryBuyerByIndentNO();
}
