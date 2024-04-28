package org.example.bookplango;

public class Hotel_Dashboard {
    Integer price;
    String room_type,ac_non_ac,status;

    public Hotel_Dashboard(Integer price, String room_type, String ac_non_ac, String status) {
        this.price = price;
        this.room_type = room_type;
        this.ac_non_ac = ac_non_ac;
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getAc_non_ac() {
        return ac_non_ac;
    }

    public void setAc_non_ac(String ac_non_ac) {
        this.ac_non_ac = ac_non_ac;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
