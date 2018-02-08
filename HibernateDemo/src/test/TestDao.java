package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by golden on 2017/6/13 0013.
 */
public class TestDao {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void testInsert() throws ParseException {
        User u = new User();
        u.setUsername("111");
        u.setAge(100);
        u.setGender("男");
        u.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1995-09-28"));
        userDao.save(u);
    }

    @Test
    public void testUpdate() throws ParseException {
        User u = new User();
        u.setId(16);
        u.setUsername("222");
        u.setAge(33);
        userDao.update(u);
//        String hql = "update User u set u.username ='sSs'   where u.username=? and u.gender=?";
//        String[] params = {"Mike","男"};
//        userDao.update(hql,params);
    }

    @Test
    public void testDelete() throws ParseException {
        User u = new User();
        u.setId(19);
        userDao.delete(u);
    }

    @Test
    public void testDeleteById() throws ParseException {
        userDao.deleteById(User.class, new Long(16));
    }

    @Test
    public void testFindById() throws ParseException {
        System.out.println(userDao.findById(User.class, new Long(16)));
    }

    @Test
    public void testFindAll() throws ParseException {
        List<User> userList = userDao.findAll(User.class);
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).getUsername());
        }
    }


}
