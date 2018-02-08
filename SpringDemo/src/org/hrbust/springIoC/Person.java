package org.hrbust.springIoC;

import java.util.List;
import java.util.Map;

/**
 * Created by golden on 2017/4/23 0023.
 */
public class Person {

    private String name;
    private int age;
    private Map<Integer, Person> friends;
    private List<String> favorites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<Integer, Person> getFriends() {
        return friends;
    }

    public void setFriends(Map<Integer, Person> friends) {
        this.friends = friends;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }


    @Override
    public String toString() {
        StringBuffer myFavorites = new StringBuffer("");
        for(String s :getFavorites()){
            myFavorites.append( s + " ");
        }



        return  "名字：" + getName() +"\n年龄：" + getAge() + "\n喜好："+ myFavorites.toString() + "\n朋友:" + getFriends().get(1).getName();   }
}
