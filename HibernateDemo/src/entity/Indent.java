package entity;

import java.sql.Timestamp;

/**
 * Created by golden on 2017/6/11 0011.
 */
public class Indent {
    private int indentId;
    private String indentNo;
    private Double totalPrice;
    private String content;
    private Integer isPayoff;
    private Integer isSales;
    private Timestamp submitTime;
    private Timestamp consignmentTime;
    private Buyer buyer;

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public int getIndentId() {
        return indentId;
    }

    public void setIndentId(int indentId) {
        this.indentId = indentId;
    }

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsPayoff() {
        return isPayoff;
    }

    public void setIsPayoff(Integer isPayoff) {
        this.isPayoff = isPayoff;
    }

    public Integer getIsSales() {
        return isSales;
    }

    public void setIsSales(Integer isSales) {
        this.isSales = isSales;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    public Timestamp getConsignmentTime() {
        return consignmentTime;
    }

    public void setConsignmentTime(Timestamp consignmentTime) {
        this.consignmentTime = consignmentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Indent indent = (Indent) o;

        if (indentId != indent.indentId) return false;
        if (indentNo != null ? !indentNo.equals(indent.indentNo) : indent.indentNo != null) return false;
        if (totalPrice != null ? !totalPrice.equals(indent.totalPrice) : indent.totalPrice != null) return false;
        if (content != null ? !content.equals(indent.content) : indent.content != null) return false;
        if (isPayoff != null ? !isPayoff.equals(indent.isPayoff) : indent.isPayoff != null) return false;
        if (isSales != null ? !isSales.equals(indent.isSales) : indent.isSales != null) return false;
        if (submitTime != null ? !submitTime.equals(indent.submitTime) : indent.submitTime != null) return false;
        if (consignmentTime != null ? !consignmentTime.equals(indent.consignmentTime) : indent.consignmentTime != null)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Indent{" +
                "indentId=" + indentId +
                ", indentNo='" + indentNo + '\'' +
                ", totalPrice=" + totalPrice +
                ", content='" + content + '\'' +
                ", isPayoff=" + isPayoff +
                ", isSales=" + isSales +
                ", submitTime=" + submitTime +
                ", consignmentTime=" + consignmentTime +
                ", buyer=" + buyer +
                '}';
    }

    @Override
    public int hashCode() {
        int result = indentId;
        result = 31 * result + (indentNo != null ? indentNo.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (isPayoff != null ? isPayoff.hashCode() : 0);
        result = 31 * result + (isSales != null ? isSales.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (consignmentTime != null ? consignmentTime.hashCode() : 0);
        return result;
    }

    public Buyer getBuyerByBuyerId() {
        return buyer;
    }

    public void setBuyerByBuyerId(Buyer buyer) {
        this.buyer = buyer;
    }
}
