public class Kamyon extends Araclar {
    protected String kamyonMarka;

    // Kamyon markası ve diğer bilgilerle yapılandırıcı
    public Kamyon(String sahipAd, String sahipSoyad, String plaka, String kamyonMarka) {
        super(sahipAd, sahipSoyad, plaka);
        this.kamyonMarka = kamyonMarka;
    }

    public String getKamyonMarka() {
        return kamyonMarka;
    }

    @Override
    public double ucretHesapla(int saat) {
        return (saat > 2) ? (saat - 2) * 40 : 0; // İlk 2 saat ücretsiz
    }
}
