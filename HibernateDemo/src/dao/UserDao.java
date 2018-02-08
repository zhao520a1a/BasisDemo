package dao;

import entity.User;

/**
 * Created by golden on 2017/6/13 0013.
 */
public interface UserDao extends BaseDao<User,Long> {

    /**
     * 通过用户Id查询IdCard信息
     */
      void queryIdCardByUserName();
}
