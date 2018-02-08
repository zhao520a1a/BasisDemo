package dao;

import entity.IdCard;

/**
 * Created by golden on 2017/6/13 0013.
 */
public interface IdCardDao  extends BaseDao<IdCard,Long> {
    /**
     * 通过IdCardNo查询用户信息
     */
    void queryUserByIdCardNo();

}
