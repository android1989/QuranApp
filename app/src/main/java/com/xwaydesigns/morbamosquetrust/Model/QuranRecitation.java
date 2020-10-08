package com.xwaydesigns.morbamosquetrust.Model;

public class QuranRecitation
{
    String sno;
    String surah_no;
    String surah_name;
    String aayah_no;
    String aayah_arabic;
    String aayah_eng;

    public QuranRecitation(String sno, String surah_no, String surah_name, String aayah_no, String aayah_arabic, String aayah_eng) {
        this.sno = sno;
        this.surah_no = surah_no;
        this.surah_name = surah_name;
        this.aayah_no = aayah_no;
        this.aayah_arabic = aayah_arabic;
        this.aayah_eng = aayah_eng;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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

    public String getAayah_no() {
        return aayah_no;
    }

    public void setAayah_no(String aayah_no) {
        this.aayah_no = aayah_no;
    }

    public String getAayah_arabic() {
        return aayah_arabic;
    }

    public void setAayah_arabic(String aayah_arabic) {
        this.aayah_arabic = aayah_arabic;
    }

    public String getAayah_eng() {
        return aayah_eng;
    }

    public void setAayah_eng(String aayah_eng) {
        this.aayah_eng = aayah_eng;
    }
}
