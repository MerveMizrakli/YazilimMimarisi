public class Karavan extends Araclar {
    protected String karavanBuyukluk;

    // Karavan büyüklüğü ve diğer bilgilerle yapılandırıcı
    public Karavan(String sahipAd, String sahipSoyad, String plaka, String karavanBuyukluk) {
        super(sahipAd, sahipSoyad, plaka);
        this.karavanBuyukluk = karavanBuyukluk;
    }

    public String getKaravanBuyukluk() {
        return karavanBuyukluk;
    }

    @Override
    public double ucretHesapla(int gun) {
        return (gun) * 1000; // Karavanlar için günlük ücret
    }
}
