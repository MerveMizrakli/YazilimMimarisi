//package models;

public abstract class Araclar {
    protected String sahipAd;
    protected String sahipSoyad;
    protected String plaka;

    // Yapıcı metod
    public Araclar(String sahipAd, String sahipSoyad, String plaka) {
        this.sahipAd = sahipAd;
        this.sahipSoyad = sahipSoyad;
        this.plaka = plaka;
    }

    // Getiriciler
    public String getSahipBilgileri() {
        return sahipAd + " " + sahipSoyad;
    }

    public String getPlaka() {
        return plaka;
    }

    // Ücret hesaplama (bu metod her türev sınıf tarafından override edilmelidir)
    public abstract double ucretHesapla(int saat);
}
