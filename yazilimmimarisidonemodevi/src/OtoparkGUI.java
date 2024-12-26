import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OtoparkGUI {
    private static CRUD crud = new CRUD();
    private static Otopark otopark = new Otopark(10);
    private static Factory factory = new Factory();
    private static Connection connection = Singleton.getInstance().getConnection();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Otopark Yönetim Sistemi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton aracEkleButton = new JButton("Araç Ekle");
        JButton aracCikarButton = new JButton("Araç Çıkar");
        JButton aracListeleButton = new JButton("Araçları Listele");
        JButton aracAraButton = new JButton("Araç Ara");
        JButton otoparkDurumButton = new JButton("Otopark Durumu");

        panel.add(aracEkleButton);
        panel.add(aracCikarButton);
        panel.add(aracListeleButton);
        panel.add(aracAraButton);
        panel.add(otoparkDurumButton);

        frame.add(panel);

        aracEkleButton.addActionListener(e -> aracEkleGUI());
        aracCikarButton.addActionListener(e -> aracCikarGUI());
        aracListeleButton.addActionListener(e -> aracListeleGUI());
        aracAraButton.addActionListener(e -> aracAraGUI());
        otoparkDurumButton.addActionListener(e -> otoparkDurumGUI());

        frame.setVisible(true);
    }

    private static void aracEkleGUI() {
        JFrame ekleFrame = new JFrame("Araç Ekle");
        ekleFrame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        JTextField aracTurField = new JTextField();
        JTextField sahipAdField = new JTextField();
        JTextField sahipSoyadField = new JTextField();
        JTextField plakaField = new JTextField();
        JTextField ekBilgiField = new JTextField();

        panel.add(new JLabel("Araç Türü:"));
        panel.add(aracTurField);
        panel.add(new JLabel("Sahip Adı:"));
        panel.add(sahipAdField);
        panel.add(new JLabel("Sahip Soyadı:"));
        panel.add(sahipSoyadField);
        panel.add(new JLabel("Plaka:"));
        panel.add(plakaField);
        panel.add(new JLabel("Ek Bilgi:"));
        panel.add(ekBilgiField);

        JButton ekleButton = new JButton("Ekle");
        panel.add(ekleButton);

        ekleFrame.add(panel);
        ekleFrame.setVisible(true);

        ekleButton.addActionListener(e -> {
            String aracTur = aracTurField.getText();
            String sahipAd = sahipAdField.getText();
            String sahipSoyad = sahipSoyadField.getText();
            String plaka = plakaField.getText();
            String ekBilgi = ekBilgiField.getText();

            Araclar yeniArac = factory.createArac(aracTur, sahipAd, sahipSoyad, plaka, ekBilgi);
            if (yeniArac != null) {
                crud.aracekle(yeniArac);
                otopark.aracEkle();
                JOptionPane.showMessageDialog(ekleFrame, "Araç başarıyla eklendi.");
                try {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO araclar (tur, sahipAd, sahipSoyad, plaka, ekBilgi) VALUES (?, ?, ?, ?, ?)");
                    ps.setString(1, aracTur);
                    ps.setString(2, sahipAd);
                    ps.setString(3, sahipSoyad);
                    ps.setString(4, plaka);
                    ps.setString(5, ekBilgi);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(ekleFrame, "Geçersiz araç türü.");
            }
        });
    }

    private static void aracCikarGUI() {
        String plaka = JOptionPane.showInputDialog("Çıkarılacak aracın plakası:");
        if (crud.aracbul(plaka) != null) {
            crud.aracsil(plaka);
            otopark.removeCar();
            JOptionPane.showMessageDialog(null, "Araç başarıyla çıkarıldı.");
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM araclar WHERE plaka = ?");
                ps.setString(1, plaka);
                ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Araç bulunamadı.");
        }
    }

    private static void aracListeleGUI() {
        StringBuilder sb = new StringBuilder("Otoparktaki araçlar:\n");
        for (Araclar arac : crud.listAraclar()) {
            sb.append("Tür: ").append(arac.getClass().getSimpleName())
                    .append(", Sahibi: ").append(arac.getSahipBilgileri())
                    .append(", Plaka: ").append(arac.getPlaka())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void aracAraGUI() {
        String plaka = JOptionPane.showInputDialog("Aranacak aracın plakası:");
        Araclar arac = crud.aracbul(plaka);
        if (arac != null) {
            JOptionPane.showMessageDialog(null, "Araç bulundu: " + arac.getClass().getSimpleName() + ", Sahibi: " + arac.getSahipBilgileri() + ", Plaka: " + arac.getPlaka());
        } else {
            JOptionPane.showMessageDialog(null, "Araç bulunamadı.");
        }
    }

    private static void otoparkDurumGUI() {
       JOptionPane.showMessageDialog(null, "Otopark durumu: " + otopark.DoluAlanlar + "/" + otopark.getKapasite());
    }
}
