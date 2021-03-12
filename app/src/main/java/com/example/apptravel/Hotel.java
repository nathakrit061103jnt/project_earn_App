package com.example.apptravel;

import java.io.Serializable;
public class Hotel implements Serializable {
    private String h_id;
    private String h_name;
    private String h_detail;
    private String h_profile;
    private String h_img1;
    private String h_img2;
    private String h_img3;
    private String h_price;
    private String h_address;
    private String h_tel;

    public Hotel(
            String h_id,
            String h_name,
            String h_detail,
            String h_profile,
            String h_img1,
            String h_img2,
            String h_img3,
            String h_price,
            String h_address,
            String h_tel
    ) {
        this.h_id = h_id;
        this.h_name = h_name;
        this.h_detail = h_detail;
        this.h_profile = h_profile;
        this.h_img1 = h_img1;
        this.h_img2 = h_img2;
        this.h_img3 = h_img3;
        this.h_price = h_price;
        this.h_address = h_address;
        this.h_tel = h_tel;
    }



    // Getter Methods

    public String getH_id() {
        return h_id;
    }

    public String getH_name() {
        return h_name;
    }

    public String getH_detail() {
        return h_detail;
    }

    public String getH_profile() {
        return h_profile;
    }

    public String getH_img1() {
        return h_img1;
    }

    public String getH_img2() {
        return h_img2;
    }

    public String getH_img3() {
        return h_img3;
    }

    public String getH_price() {
        return h_price;
    }

    public String getH_address() {
        return h_address;
    }

    public String getH_tel() {
        return h_tel;
    }

    // Setter Methods

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public void setH_detail(String h_detail) {
        this.h_detail = h_detail;
    }

    public void setH_profile(String h_profile) {
        this.h_profile = h_profile;
    }

    public void setH_img1(String h_img1) {
        this.h_img1 = h_img1;
    }

    public void setH_img2(String h_img2) {
        this.h_img2 = h_img2;
    }

    public void setH_img3(String h_img3) {
        this.h_img3 = h_img3;
    }

    public void setH_price(String h_price) {
        this.h_price = h_price;
    }

    public void setH_address(String h_address) {
        this.h_address = h_address;
    }

    public void setH_tel(String h_tel) {
        this.h_tel = h_tel;
    }
}
