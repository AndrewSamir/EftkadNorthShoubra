package com.samir.andrew.eftkadnorthshoubra.singleton;

/**
 * Created by ksi on 03-Jul-17.
 */

public class SingletonSearchResult {

    private static SingletonSearchResult mInstance = null;

    private String father, member_baptism_data, member_birthdate, member_block_no, member_discription_in_family,
            member_facebook_link, member_flat_no, member_floor_no, member_graduation_year, member_job, member_mail,
            member_marriage_date, member_mobile_1, member_mobile_2, member_name, member_national_id, member_phone_1,
            member_phone_2, member_qualification, member_social_status, notes,member_street,member_church,member_area,key;

    private SingletonSearchResult() {
    }

    public static SingletonSearchResult getInstance() {
        if (mInstance == null) {
            mInstance = new SingletonSearchResult();
        }
        return mInstance;
    }

    public static SingletonSearchResult getmInstance() {
        return mInstance;
    }

    public static void setmInstance(SingletonSearchResult mInstance) {
        SingletonSearchResult.mInstance = mInstance;
    }

    public String getMember_street() {
        return member_street;
    }

    public void setMember_street(String member_street) {
        this.member_street = member_street;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMember_baptism_data() {
        return member_baptism_data;
    }

    public void setMember_baptism_data(String member_baptism_data) {
        this.member_baptism_data = member_baptism_data;
    }

    public String getMember_birthdate() {
        return member_birthdate;
    }

    public void setMember_birthdate(String member_birthdate) {
        this.member_birthdate = member_birthdate;
    }

    public String getMember_block_no() {
        return member_block_no;
    }

    public void setMember_block_no(String member_block_no) {
        this.member_block_no = member_block_no;
    }

    public String getMember_discription_in_family() {
        return member_discription_in_family;
    }

    public void setMember_discription_in_family(String member_discription_in_family) {
        this.member_discription_in_family = member_discription_in_family;
    }

    public String getMember_facebook_link() {
        return member_facebook_link;
    }

    public void setMember_facebook_link(String member_facebook_link) {
        this.member_facebook_link = member_facebook_link;
    }

    public String getMember_flat_no() {
        return member_flat_no;
    }

    public void setMember_flat_no(String member_flat_no) {
        this.member_flat_no = member_flat_no;
    }

    public String getMember_floor_no() {
        return member_floor_no;
    }

    public void setMember_floor_no(String member_floor_no) {
        this.member_floor_no = member_floor_no;
    }

    public String getMember_graduation_year() {
        return member_graduation_year;
    }

    public void setMember_graduation_year(String member_graduation_year) {
        this.member_graduation_year = member_graduation_year;
    }

    public String getMember_job() {
        return member_job;
    }

    public void setMember_job(String member_job) {
        this.member_job = member_job;
    }

    public String getMember_mail() {
        return member_mail;
    }

    public void setMember_mail(String member_mail) {
        this.member_mail = member_mail;
    }

    public String getMember_marriage_date() {
        return member_marriage_date;
    }

    public void setMember_marriage_date(String member_marriage_date) {
        this.member_marriage_date = member_marriage_date;
    }

    public String getMember_mobile_1() {
        return member_mobile_1;
    }

    public void setMember_mobile_1(String member_mobile_1) {
        this.member_mobile_1 = member_mobile_1;
    }

    public String getMember_mobile_2() {
        return member_mobile_2;
    }

    public void setMember_mobile_2(String member_mobile_2) {
        this.member_mobile_2 = member_mobile_2;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_national_id() {
        return member_national_id;
    }

    public void setMember_national_id(String member_national_id) {
        this.member_national_id = member_national_id;
    }

    public String getMember_phone_1() {
        return member_phone_1;
    }

    public void setMember_phone_1(String member_phone_1) {
        this.member_phone_1 = member_phone_1;
    }

    public String getMember_phone_2() {
        return member_phone_2;
    }

    public void setMember_phone_2(String member_phone_2) {
        this.member_phone_2 = member_phone_2;
    }

    public String getMember_qualification() {
        return member_qualification;
    }

    public void setMember_qualification(String member_qualification) {
        this.member_qualification = member_qualification;
    }

    public String getMember_social_status() {
        return member_social_status;
    }

    public void setMember_social_status(String member_social_status) {
        this.member_social_status = member_social_status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMember_church() {
        return member_church;
    }

    public void setMember_church(String member_church) {
        this.member_church = member_church;
    }

    public String getMember_area() {
        return member_area;
    }

    public void setMember_area(String member_area) {
        this.member_area = member_area;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
