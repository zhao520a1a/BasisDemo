package org.hrbust.springapo.targer;

/**
 * 目标实现类
 * Created by Chris on 2017/3/29.
 */

public class ForumService implements ForumServiceInterface{

    private ForumDao forumDao = new ForumDao();
    private TopicDao topicDao = new TopicDao();

    @Override
    public void createForum() {
        this.forumDao.create();
    }

    @Override
    public void removeTopic(String topicId) throws Throwable{
        this.topicDao.remove(topicId);

        // 此处抛出异常会阻止后置增强执行，触发异常增强执行
        //throw new Exception("thrown from removeTopic");
    }
}
