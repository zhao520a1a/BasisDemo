package entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private long id;
	private String username;
	private String gender;
	private int age;
	private Date birthday;
    private IdCard idCard;

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", gender='" + gender + '\'' +
				", age=" + age +
				", birthday=" + birthday +
				", idCard=" + idCard +
				'}';
	}

	public User() {
	}
	public User(String username, String gender) {
		this.username = username;
		this.gender = gender;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


}


