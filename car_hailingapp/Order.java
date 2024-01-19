package com.example.car_hailingapp;

import java.util.Date;

public class Order {
    private String Onumber;//订单的编号
    private String Pnumber;//该订单乘客编号
    private String Dnumber;//该订单司机编号
    private Date starttime;//订单开始时间
    private Date endtime;//订单结束时间
    private float price;//订单的价格
    private float km;//订单的距离
    private String startaddress;//订单起始地
    private String destination;//订单目的地
    private String evaluate;//订单评价


    public Order() {
        super();
    }
    public Order(String onumber, String Pnumber, String Dnumber, Date starttime, Date endtime, float km, String startaddress, String destination, float price, String evaluate) {
        super();
        Onumber = onumber;
        this.destination = destination;
        this.startaddress = startaddress;
        this.Pnumber = Pnumber;
        this.Dnumber = Dnumber;
        this.starttime = starttime;
        this.endtime = endtime;
        this.km = km;
        this.evaluate = evaluate;
        this.price = price;
    }

    public String getOnumber() {
        return Onumber;
    }
    public void setOnumber(String onumber) {
        Onumber = onumber;
    }
    public String getEvaluate() {
        return evaluate;
    }
    public void setEvaluate(String evaluate) {
        this.evaluate=evaluate;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getPnumber() {
        return Pnumber;
    }
    public void setPnumber(String Pnumber) {
        this.Pnumber = Pnumber;
    }
    public String  getDnumber() {
        return Dnumber;
    }
    public void setDnumber(String Dnumber) {
        this.Dnumber = Dnumber;
    }
    public Date getStarttime() {
        return starttime;
    }
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    public Date getEndtime() {
        return endtime;
    }
    public void setEndtimetime(Date endtime) {
        this.endtime = endtime;
    }
    public float  getKm() {
        return km;
    }
    public void setKm(float km) {
        this.km = km;
    }
    public String  getStartaddress() {
        return startaddress;
    }
    public void setStartaddress(String startaddress) {
        this.startaddress = startaddress;
    }
    public String  getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (Onumber == null) {
            if (other.Onumber != null)
                return false;
        } else if (!Onumber.equals(other.Onumber))
            return false;
        if (evaluate == null) {
            if (other.evaluate != null)
                return false;
        } else if (!evaluate.equals(other.evaluate))
            return false;
        if (price == 0) {
            if (other.price != 0)
                return false;
        } else if (!(price==other.price))
            return false;
        if (Pnumber == null) {
            if (other.Pnumber != null)
                return false;
        } else if (!Pnumber.equals(other.Pnumber))
            return false;
        if (Dnumber == null) {
            if (other.Dnumber != null)
                return false;
        } else if (!Dnumber.equals(other.Dnumber))
            return false;
        if (starttime == null) {
            if (other.starttime != null)
                return false;
        } else if (!starttime.equals(other.starttime))
            return false;
        if (endtime == null) {
            if (other.endtime != null)
                return false;
        } else if (!endtime.equals(other.endtime))
            return false;
        if (km == 0) {
            if (other.km != 0)
                return false;
        } else if (!(km==other.km))
            return false;
        if (startaddress == null) {
            if (other.startaddress != null)
                return false;
        } else if (!startaddress.equals(other.startaddress))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Order [Onumber=" + Onumber + ", Evaluate=" + evaluate + ", price=" + price + "]";
    }
}
