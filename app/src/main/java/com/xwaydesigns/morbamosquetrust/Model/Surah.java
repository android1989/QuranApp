package com.xwaydesigns.morbamosquetrust.Model;

public class Surah
{
    String surah_no;
    String surah_name;
    String eng;
    String aayah_count;

    public Surah(String surah_no, String surah_name, String eng, String aayah_count) {
        this.surah_no = surah_no;
        this.surah_name = surah_name;
        this.eng = eng;
        this.aayah_count = aayah_count;
    }

    public String getSurah_no() {
        return surah_no;
    }

    public void setSurah_no(String surah_no) {
        this.surah_no = surah_no;
    }

    public String getSurah_name() {
        return surah_name;
    }

    public void setSurah_name(String surah_name) {
        this.surah_name = surah_name;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getAayah_count() {
        return aayah_count;
    }

    public void setAayah_count(String aayah_count) {
        this.aayah_count = aayah_count;
    }

}
