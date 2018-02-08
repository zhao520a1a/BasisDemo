package util;

import dao.UserDao;
import dao.impl.BaseDaoImpl;
import entity.User;
import org.hibernate.*;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserDaoDemo extends BaseDaoImpl<User,Long> implements UserDao {

    SessionFactory sf = HibernateUtil1.getSessionFactory();
    Session session = HibernateUtil1.getSession();
    Transaction ts = null;
    Query query = null;

    @Test
    public void saveUser() throws ParseException {
        User u = new User();  // 此时u是瞬时(transient)对象，处于临时状态；
        u.setUsername("zhaojinxin");
        u.setAge(100);
        u.setGender("男");
        u.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1995-09-28"));
        Transaction ts = null;
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            session.save(u); //save()之后，u变成了持久（persist）对象，处于持久化状态；
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Test
    public void updateUser() throws ParseException {
        User u = null;
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            u = (User) session.get(User.class, new Long(17));  //立即加载，此时u处于持久化状态；
            System.out.println("修改前：" + u);
            u.setUsername("xinjinzhao");
            session.update(u);  //updata()之后，u处于持久化状态；
            System.out.println("修改后：" + u);
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Test
    public void testDeleteUser() throws ParseException {
        System.out.println("删除前，记录列表：");
        queryUser();
        User u = null;
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            u = (User) session.load(User.class, new Long(1)); //懒加载, u处于持久化状态；
            session.delete(u); //delete()之后，u处于临时状态
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
        System.out.println("删除后，记录列表：");
        queryUser();
    }

    /*测试托管对象*/
    public void testDetachedObject() throws ParseException {
        User u = new User();
        u.setUsername("wang");
        u.setAge(23);
        u.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1996-05-02"));

        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            session.save(u); // u is persist
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
        //u是托管（detached）对象, 不会自动发sql语句
        u.setUsername("xiaoLi");
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            session.saveOrUpdate(u); //u是持久（persist）对象
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }

    }



    /**
     * 简单查询：查询所有记录
     */
    @Test
    public void queryUser() {
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            query = session.createQuery("from User ");
            List users = query.list();
            for (int i = 0; i < users.size(); i++) {
                User u = (User) users.get(i);  //u处于持久化状态；
                System.out.println(u);
            }
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    /**
     * 属性查询:
     */
    @Test
    public void queryUser1() {
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            query = session.createQuery("select u.username,u.gender from User u "); //将多个返回结果封装成一个对象数组，
            List users = query.list();
            for (int i = 0; i < users.size(); i++) {
                Object[] objs = (Object[]) users.get(i);
                System.out.println("姓名：" + objs[0] + "性别：" + objs[1]);
            }
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    /**
     * 实例化查询
     * 注意，这个返回的User实例并未与数据库有任何关联，可以试着取得id属性，可以发现它的值是0，如果试图使用Session的saveOrupdate()方法，则会新增一条记录而不是更新原有的记录。
     */
    @Test
    public void queryUser2() {
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            query = session.createQuery("select new User(u.username,u.gender) from User u "); //将返回结果封装成一个对象（瞬时对象），方便访问
            List users = query.list();
            for (int i = 0; i < users.size(); i++) {
                User user = (User) users.get(i);
                System.out.println("姓名：" + user.getUsername() + "性别：" + user.getGender());
            }
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    /** HQL统计函数查询：count(),min(),max(),sum(),avg()*/
    @Test
    public void QueryUser3() {
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
//           query = session.createQuery("select count(*) from User u ");
//           query = session.createQuery("select min(u.age) from User u ");
            query = session.createQuery("select avg(u.age) from User u ");
            Object retNum = query.uniqueResult();  //查询返回的集合中只有一个对象时，就可以使用
            System.out.println("平均年龄：" + retNum);
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    /**
     * 嵌套子查询：查找出大于平均年龄的用户
     */
    @Test
    public void QueryUser4() {
        User user = null;
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            Query query = session.createQuery("from User u where u.age>(select avg(age) from User)");
            List users = query.list();
            System.out.println("大于平均年龄的用户列表：");
            for(int i=0;i<users.size();i++)
            {
                user =(User)users.get(i);
                System.out.println(i+"--姓名："+user.getUsername()+"--年龄"+user.getAge());
            }
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    /**
     * 模糊查询
     */
    @Test
    public void QueryUserByLike() {
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            query = session.createQuery("" +
                    " from User u where  u.username like '%o%'");
            List list = query.list();
            for (int i = 0; i < list.size(); i++) {
                User user = (User) list.get(i);
                System.out.println(user.getUsername());
            }
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    /*根据用户性别分类后 将平均年龄和相应的性别输出*/
    @Test
    public void QueryUserByAvg() {
        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            query = session.createQuery(" select avg(u.age),u.gender from User u group by u.gender");
            List list = query.list();
            for (int i = 0; i < list.size(); i++) {
                Object[] objs = (Object[]) list.get(i);
                System.out.println(objs[0] + "---" + objs[1]);
            }
            ts.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (ts != null) {
                ts.rollback();
            }
        }
    }


    /*根据分页输出*/
    @Test
    public void QueryUserByPage() {
        int pageSize = 3;//每页显示的数量
        int pageNo; //页码
        int pageCount;  //总页数
        long count;  //总记录数

        try {
            session = sf.getCurrentSession();
            ts = session.beginTransaction();
            query = session.createQuery("select distinct count (*) from User ");
            count = (Long) query.uniqueResult();
            pageCount = (int) (count % pageSize == 0 ? count / pageSize : count / pageSize + 1);//总页码
            query = session.createQuery("from User ");
            for (pageNo = 1; pageNo <= pageCount; pageNo++) {
                System.out.println("第" + pageNo + "页：");
                query.setFirstResult((pageNo - 1) * pageSize);//设置从哪一行记录开始读取
                query.setMaxResults(pageSize);//设置读取多少行记录
                List list = query.list();
                for (int i = 0; i < list.size(); i++) {
                    User user = (User) list.get(i);
                    System.out.println(user.getUsername() + "---" + user.getGender() + "---" + user.getAge() + "---" + user.getBirthday());
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



    /**
     * 通过用户Id查询IdCard信息
     */
    @Test
    public void queryIdCardByUserName() {
        try {
            session = sf.getCurrentSession();
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
