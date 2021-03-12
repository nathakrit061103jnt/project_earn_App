package com.example.apptravel;

import java.io.Serializable;

public class Attraction implements Serializable {
    private String at_id;
    private String at_name;
    private String at_location;
    private String at_detial;
    private String at_p;
    private String at_profile;
    private String at_img1;
    private String at_img2;
    private String at_img3;

    public Attraction(
            String at_id,
            String at_name,
            String at_location,
            String at_detial,
            String at_p,
            String at_profile,
            String at_img1,
            String at_img2,
            String at_img3
    ) {
        this.at_id = at_id;
        this.at_name = at_name;
        this.at_location = at_location;
        this.at_detial = at_detial;
        this.at_p = at_p;
        this.at_profile = at_profile;
        this.at_img1 = at_img1;
        this.at_img2 = at_img2;
        this.at_img3 = at_img3;
    }


    // Getter Methods

    public String getAt_id() {
        return at_id;
    }

    public String getAt_name() {
        return at_name;
    }

    public String getAt_location() {
        return at_location;
    }

    public String getAt_detial() {
        return at_detial;
    }

    public String getAt_p() {
        return at_p;
    }

    public String getAt_profile() {
        return at_profile;
    }

    public String getAt_img1() {
        return at_img1;
    }

    public String getAt_img2() {
        return at_img2;
    }

    public String getAt_img3() {
        return at_img3;
    }

    // Setter Methods

    public void setAt_id(String at_id) {
        this.at_id = at_id;
    }

    public void setAt_name(String at_name) {
        this.at_name = at_name;
    }

    public void setAt_location(String at_location) {
        this.at_location = at_location;
    }

    public void setAt_detial(String at_detial) {
        this.at_detial = at_detial;
    }

    public void setAt_p(String at_p) {
        this.at_p = at_p;
    }

    public void setAt_profile(String at_profile) {
        this.at_profile = at_profile;
    }

    public void setAt_img1(String at_img1) {
        this.at_img1 = at_img1;
    }

    public void setAt_img2(String at_img2) {
        this.at_img2 = at_img2;
    }

    public void setAt_img3(String at_img3) {
        this.at_img3 = at_img3;
    }
}
