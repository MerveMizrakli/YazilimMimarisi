import java.util.ArrayList;
import java.util.List;
public interface Observer1 {
    void Gozlemciekle(Observer1 observer); // Gözlemci ekleme
    void Gozlemcisil(Observer1 observer); // Gözlemci silme
    void Guncelleme(String mesaj); // Tüm gözlemcilere bildirim
    void Guncelleme(); // Tüm gözlemcilere bildirim
}
