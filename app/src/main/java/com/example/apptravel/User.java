package com.example.apptravel;


public class User {

    private int u_id;
    private String u_email, u_tel, u_name, u_address;

    public User(int u_id, String u_email, String u_tel, String u_name, String u_address) {
        this.u_id = u_id;
        this.u_email = u_email;
        this.u_tel = u_tel;
        this.u_name = u_name;
        this.u_address = u_address;
    }

    public int getUid() {
        return u_id;
    }

    public void setUid(int u_id) {
        this.u_id = u_id;
    }

    public String getEmail() {
        return u_email;
    }

    public void setEmail(String u_email) {
        this.u_email = u_email;
    }

    public String getTel() {
        return u_tel;
    }

    public void setTel(String u_tel) {
        this.u_tel = u_tel;
    }

    public String getName() {
        return u_name;
    }

    public void setName(String u_name) {
        this.u_name = u_name;
    }

    public String getAddress() {
        return u_address;
    }

    public void setAddress(String u_address) {
        this.u_address = u_address;
    }

}
