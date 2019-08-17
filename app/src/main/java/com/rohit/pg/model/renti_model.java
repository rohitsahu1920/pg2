package com.rohit.pg.model;

import java.io.Serializable;

public class renti_model implements Serializable {

    private String first_name;
    private String last_name;
    private String gender;
    private String father_name;
    private String mobile;
    private String p_mobile;
    private String occupation;
    private String permanent_address;
    private String current_address;
    private String pg_name;
    private String room_no;
    private String bed_no;
    private byte[] id_image;
    private byte[] profile_image;

    @Override
    public String toString(){
        return first_name;
    }

    public renti_model(String first_name, String last_name, String gender, String father_name, String mobile,
                       String p_mobile, String occupation, String permanent_address, String current_address,
                       String pg_name, String room_no, String bed_no, byte[] id_image, byte[] profile_image)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.father_name = father_name;
        this.mobile = mobile;
        this.p_mobile = p_mobile;
        this.occupation = occupation;
        this.permanent_address = permanent_address;
        this.current_address = current_address;
        this.pg_name = pg_name;
        this.room_no = room_no;
        this.bed_no = bed_no;
        this.id_image = id_image;
        this.profile_image = profile_image;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setP_mobile(String p_mobile) {
        this.p_mobile = p_mobile;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public void setCurrent_address(String current_address) {
        this.current_address = current_address;
    }

    public void setPg_name(String pg_name) {
        this.pg_name = pg_name;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public void setBed_no(String bed_no) {
        this.bed_no = bed_no;
    }

    public void setId_image(byte[] id_image) {
        this.id_image = id_image;
    }

    public void setProfile_image(byte[] profile_image) {
        this.profile_image = profile_image;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getP_mobile() {
        return p_mobile;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public String getCurrent_address() {
        return current_address;
    }

    public String getPg_name() {
        return pg_name;
    }

    public String getRoom_no() {
        return room_no;
    }

    public String getBed_no() {
        return bed_no;
    }

    public byte[] getId_image() {
        return id_image;
    }

    public byte[] getProfile_image() {
        return profile_image;
    }




}
