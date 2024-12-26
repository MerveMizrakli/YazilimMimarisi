import java.util.Scanner;

public class Factory {

    // Araç türüne göre nesne oluşturan metot
    public Araclar createArac(String aracTur, String sahipAd, String sahipSoyad, String plaka, String ekBilgi) {
        if (aracTur.equalsIgnoreCase("Araba")) {
            return new Araba(sahipAd, sahipSoyad, plaka, ekBilgi);
        } else if (aracTur.equalsIgnoreCase("Kamyon")) {
            return new Kamyon(sahipAd, sahipSoyad, plaka, ekBilgi);
        } else if (aracTur.equalsIgnoreCase("Karavan")) {
            return new Karavan(sahipAd, sahipSoyad, plaka, ekBilgi);
        }
        return null; // Geçersiz tür girildiğinde null döner
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factory factory = new Factory();

        System.out.println("Araç türünü girin (Araba, Kamyon, Karavan): ");
        String aracTur = scanner.nextLine();

        System.out.println("Sahibin adı: ");
        String sahipAd = scanner.nextLine();

        System.out.println("Sahibin soyadı: ");
        String sahipSoyad = scanner.nextLine();

        System.out.println("Plaka numarasını girin: ");
        String plaka = scanner.nextLine();

        String ekBilgi = "";
        if (aracTur.equalsIgnoreCase("Araba")) {
            System.out.println("Araba markasını girin: ");
            ekBilgi = scanner.nextLine();
        } else if (aracTur.equalsIgnoreCase("Kamyon")) {
            System.out.println("Kamyon markasını girin: ");
            ekBilgi = scanner.nextLine();
        } else if (aracTur.equalsIgnoreCase("Karavan")) {
            System.out.println("Karavan büyüklüğünü girin (küçük, orta, büyük): ");
            ekBilgi = scanner.nextLine();
        }

        // Fabrika üzerinden araç yarat
        Araclar arac = factory.createArac(aracTur, sahipAd, sahipSoyad, plaka, ekBilgi);

        if (arac != null) {
            System.out.println("Oluşturulan araç:");
            System.out.println("Tür: " + aracTur);
            System.out.println("Sahibi: " + arac.getSahipBilgileri());
            System.out.println("Plaka: " + arac.getPlaka());
            System.out.println("Ücret: " + arac.ucretHesapla(5)); // 5 saat için örnek ücret hesaplama
        } else {
            System.out.println("Geçersiz araç türü girildi.");
        }

        scanner.close();
    }
}

