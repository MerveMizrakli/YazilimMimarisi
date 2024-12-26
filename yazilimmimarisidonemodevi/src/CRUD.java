//import Araclar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CRUD {
    private List<Araclar> araclar = new ArrayList<>();
    public void aracekle(Araclar arac) {
        String query = "INSERT INTO araclar (tur, sahipAd, sahipSoyad, plaka, ekBilgi) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Singleton.getInstance().getConnection().prepareStatement(query)) {
            ps.setString(1, arac.getClass().getSimpleName());
            ps.setString(2, arac.getSahipBilgileri().split(" ")[0]);
            ps.setString(3, arac.getSahipBilgileri().split(" ")[1]);
            ps.setString(4, arac.getPlaka());
            if (arac instanceof Araba) {
                ps.setString(5, ((Araba) arac).getArabaMarka());
            } else if (arac instanceof Kamyon) {
                ps.setString(5, ((Kamyon) arac).getKamyonMarka());
            } else if (arac instanceof Karavan) {
                ps.setString(5, ((Karavan) arac).getKaravanBuyukluk());
            } else {
                ps.setString(5, null);
            }
            ps.executeUpdate();
            System.out.println("Araç veritabanına eklendi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void aracsil(String plaka) {
        String query = "DELETE FROM araclar WHERE plaka = ?";
        try (PreparedStatement ps = Singleton.getInstance().getConnection().prepareStatement(query)) {
            ps.setString(1, plaka);
            ps.executeUpdate();
            System.out.println("Araç veritabanından silindi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Araclar aracbul(String plaka) {
        String query = "SELECT * FROM araclar WHERE plaka = ?";
        try (PreparedStatement ps = Singleton.getInstance().getConnection().prepareStatement(query)) {
            ps.setString(1, plaka);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String tur = rs.getString("tur");
                String sahipAd = rs.getString("sahipAd");
                String sahipSoyad = rs.getString("sahipSoyad");
                String ekBilgi = rs.getString("ekBilgi");

                if (tur.equalsIgnoreCase("Araba")) {
                    return new Araba(sahipAd, sahipSoyad, plaka, ekBilgi);
                } else if (tur.equalsIgnoreCase("Kamyon")) {
                    return new Kamyon(sahipAd, sahipSoyad, plaka, ekBilgi);
                } else if (tur.equalsIgnoreCase("Karavan")) {
                    return new Karavan(sahipAd, sahipSoyad, plaka, ekBilgi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Araclar> listAraclar() {
        List<Araclar> araclar = new ArrayList<>();
        String query = "SELECT * FROM araclar";
        try (PreparedStatement ps = Singleton.getInstance().getConnection().prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String tur = rs.getString("tur");
                String sahipAd = rs.getString("sahipAd");
                String sahipSoyad = rs.getString("sahipSoyad");
                String plaka = rs.getString("plaka");
                String ekBilgi = rs.getString("ekBilgi");

                Araclar arac = null;
                if (tur.equalsIgnoreCase("Araba")) {
                    arac = new Araba(sahipAd, sahipSoyad, plaka, ekBilgi);
                } else if (tur.equalsIgnoreCase("Kamyon")) {
                    arac = new Kamyon(sahipAd, sahipSoyad, plaka, ekBilgi);
                } else if (tur.equalsIgnoreCase("Karavan")) {
                    arac = new Karavan(sahipAd, sahipSoyad, plaka, ekBilgi);
                }
                araclar.add(arac);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return araclar;
    }


}
