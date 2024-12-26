// package models;
public class Araba extends Araclar {
    protected String arabaMarka;

    // Araba markası ve diğer bilgilerle yapılandırıcı
    public Araba(String sahipAd, String sahipSoyad, String plaka, String arabaMarka) {
        super(sahipAd, sahipSoyad, plaka);
        this.arabaMarka = arabaMarka;
    }

    public String getArabaMarka() {
        return arabaMarka;
    }

    @Override
    public double ucretHesapla(int saat) {
        return (saat > 2) ? (saat - 2) * 20 : 0; // İlk 2 saat ücretsiz
    }
}
