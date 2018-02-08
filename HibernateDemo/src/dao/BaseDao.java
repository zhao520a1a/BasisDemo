package dao;

import java.util.List;

/**
 * Created by golden on 2017/6/13 0013.
 */

/**
 * @param <T>  泛型，指实体类  type
 * @param <PK> 泛型，指实体类主键的数据类型，如Integer,Long
 */
public interface BaseDao<T, PK> {
    /**
     * 保存指定实体类
     * @param entity
     */
    void save(T entity);
    /**
     * 保存指定实体类
     * @param entity
     */
    void update(T entity);
    /**
     * 更新或保存指定实体
     * @param entity 实体类
     */
    void saveOrupdate(T entity);

    /**
     * 更新实体
     * 可用于添加、修改、删除操作
     * @param hql    更新的HQL语句
     * @param params 参数,可有项目或多项目,代替Hql中的"?"号
     */
    void update(final String hql, final Object[] params);

    /**
     * 删除指定实体
     * @param entity
     */
    void delete(T entity);

    /**
     * 删除实体
     * @param entityClass 实体类名
     * @param id          实体的ID
     */
    void deleteById(Class<T> entityClass, PK id);

    /**
     * 获取所有实体集合
     * @param entityClass
     * @return 实体类名
     */
    List<T> findAll(Class<T> entityClass);

    /**
     * 查找指定PK实体类对象
     * @param entityClass 实体Class
     * @param id          实体PK
     * @return 实体对象
     */
    T findById(Class<T> entityClass, PK id);
}