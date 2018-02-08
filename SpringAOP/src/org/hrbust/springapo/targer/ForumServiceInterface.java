package org.hrbust.springapo.targer;

/**
 * 目标类接口
 * Created by Chris on 2017/3/29.
 */
public interface ForumServiceInterface {
    public void createForum();
    public void removeTopic(String topic) throws Throwable;
}
