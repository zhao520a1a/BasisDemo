package entity;

/**
 * Created by golden on 2017/6/11 0011.
 */
public class IdCard {
    private long id;
    private String cardNo;
    private User user;

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "IdCard{" +
                "id=" + id +
                ", cardNo='" + cardNo + '\'' +
                ", user=" + user +
                '}';
    }

    public void setUser(User user) {
        this.user = user;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdCard idCard = (IdCard) o;

        if (id != idCard.id) return false;
        if (cardNo != null ? !cardNo.equals(idCard.cardNo) : idCard.cardNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (cardNo != null ? cardNo.hashCode() : 0);
        return result;
    }
}
