package entity;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by golden on 2017/6/11 0011.
 */
public class Buyer {
    private int buyerId;
    private String buyerName;
    private String buyerPassword;
    private String realname;
    private String sex;
    private String email;
    private String phone;
    private String address;
    private Timestamp regTime;
    private Integer score;
    private Set<Indent> indentList;

    @Override
    public String toString() {
        return "Buyer{" +
                "buyerId=" + buyerId +
                ", buyerName='" + buyerName + '\'' +
                ", buyerPassword='" + buyerPassword + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", regTime=" + regTime +
                ", score=" + score +
                ", indentList=" + indentList +
                '}';
    }

    public Set<Indent> getIndentList() {
        return indentList;
    }

    public void setIndentList(Set<Indent> indentList) {
        this.indentList = indentList;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPassword() {
        return buyerPassword;
    }

    public void setBuyerPassword(String buyerPassword) {
        this.buyerPassword = buyerPassword;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buyer buyer = (Buyer) o;

        if (buyerId != buyer.buyerId) return false;
        if (buyerName != null ? !buyerName.equals(buyer.buyerName) : buyer.buyerName != null) return false;
        if (buyerPassword != null ? !buyerPassword.equals(buyer.buyerPassword) : buyer.buyerPassword != null)
            return false;
        if (realname != null ? !realname.equals(buyer.realname) : buyer.realname != null) return false;
        if (sex != null ? !sex.equals(buyer.sex) : buyer.sex != null) return false;
        if (email != null ? !email.equals(buyer.email) : buyer.email != null) return false;
        if (phone != null ? !phone.equals(buyer.phone) : buyer.phone != null) return false;
        if (address != null ? !address.equals(buyer.address) : buyer.address != null) return false;
        if (regTime != null ? !regTime.equals(buyer.regTime) : buyer.regTime != null) return false;
        if (score != null ? !score.equals(buyer.score) : buyer.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = buyerId;
        result = 31 * result + (buyerName != null ? buyerName.hashCode() : 0);
        result = 31 * result + (buyerPassword != null ? buyerPassword.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (regTime != null ? regTime.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}
