package com.info.flagquiz;



public class Flags {
    private int flag_id;
    private String flag_name;
    private String flag_photo;

    public Flags() {
    }

    public Flags(int flag_id, String flag_name, String flag_photo) {
        this.flag_id = flag_id;
        this.flag_name = flag_name;
        this.flag_photo = flag_photo;
    }

    public int getFlag_id() {
        return flag_id;
    }

    public void setFlag_id(int flag_id) {
        this.flag_id = flag_id;
    }

    public String getFlag_name() {
        return flag_name;
    }

    public void setFlag_name(String flag_name) {
        this.flag_name = flag_name;
    }

    public String getFlag_photo() {
        return flag_photo;
    }

    public void setFlag_photo(String flag_photo) {
        this.flag_photo = flag_photo;
    }
}
