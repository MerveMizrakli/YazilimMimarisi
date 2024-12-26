import java.util.ArrayList;
import java.util.List;

public class Otopark implements Observer1 {
    private List<Observer1> observers; // Gözlemciler listesi
    private int oKapasite; // Otopark kapasitesi
    public int DoluAlanlar; // Dolu alan sayısı

    public Otopark(int oKapasite) {
        this.oKapasite = oKapasite;
        this.DoluAlanlar = 0;
        this.observers = new ArrayList<>();
    }

    public int getKapasite() {
        return oKapasite;
    }
    public void aracEkle() {
        if (DoluAlanlar < oKapasite) {
            DoluAlanlar++;
            System.out.println("Araç eklendi. Dolu alan: " + DoluAlanlar);
            Guncelleme(); // Gözlemcilere bildir
        } else {
            System.out.println("Otopark dolu!");
        }
    }
    public void removeCar() {
        if (DoluAlanlar > 0) {
            DoluAlanlar--;
            System.out.println("Araç çıkarıldı. Dolu alan: " + DoluAlanlar);
            Guncelleme(); // Gözlemcilere bildir
        } else {
            System.out.println("Otopark boş durumda çıkarılacak araba bulunamadı!");
        }
    }
    @Override
    public void Gozlemciekle(Observer1 observer) {
        observers.add(observer);
    }
    @Override
    public void Gozlemcisil(Observer1 observer) {
        observers.remove(observer);
    }

    @Override
    public void Guncelleme(String mesaj) {

    }

    @Override
    public void Guncelleme() {
        String mesaj = "Otopark Durumu: " + DoluAlanlar + "/" + oKapasite;
    }


}
