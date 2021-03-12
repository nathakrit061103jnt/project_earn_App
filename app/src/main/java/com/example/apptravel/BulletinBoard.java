package com.example.apptravel;

import java.io.Serializable;

public class BulletinBoard implements Serializable {
    private String bb_id;
    private String bb_name;
    private String bb_detail;
    private String bb_date;
    private  String bb_image;

    public BulletinBoard(String bb_id, String bb_name, String bb_detail, String bb_date, String bb_image) {
        this.bb_id = bb_id;
        this.bb_name = bb_name;
        this.bb_detail = bb_detail;
        this.bb_date = bb_date;
        this.bb_image=bb_image;
    }


    // Getter Methods

    public String getBb_id() {
        return bb_id;
    }

    public String getBb_name() {
        return bb_name;
    }

    public String getBb_detail() {
        return bb_detail;
    }

    public String getBb_date() {
        return bb_date;
    }

    public String getBb_image() {
        return bb_image;
    }

    // Setter Methods

    public void setBb_id(String bb_id) {
        this.bb_id = bb_id;
    }

    public void setBb_name(String bb_name) {
        this.bb_name = bb_name;
    }

    public void setBb_detail(String bb_detail) {
        this.bb_detail = bb_detail;
    }

    public void setBb_date(String bb_date) {
        this.bb_date = bb_date;
    }

    public void setBb_image(String bb_image) {
        this.bb_image = bb_image;
    }
}