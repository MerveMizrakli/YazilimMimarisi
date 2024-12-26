import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Otopark kapasitesi
        int kapasite = 10;
        Otopark otopark = new Otopark(kapasite);

        // Gözlemciler
        Musteriler musteri1 = new Musteriler();
        musteri1.User("Ahmet");
        Musteriler musteri2 = new Musteriler();
        musteri2.User("Ayse");
        otopark.Gozlemciekle(musteri1);
        otopark.Gozlemciekle(musteri2);

        // CRUD işlemleri için nesne
        CRUD crud = new CRUD();

        // Araç fabrika sınıfı
        Factory factory = new Factory();

        Scanner scanner = new Scanner(System.in);
        boolean devam = true;

        while (devam) {
            System.out.println("\nOtopark Yönetim Sistemi");
            System.out.println("1. Araç Ekle");
            System.out.println("2. Araç Çıkar");
            System.out.println("3. Araçları Listele");
            System.out.println("4. Araç Ara");
            System.out.println("5. Otopark Durumunu Göster");
            System.out.println("6. Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine(); // Satır sonunu temizle

            switch (secim) {
                case 1:
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

                    Araclar yeniArac = factory.createArac(aracTur, sahipAd, sahipSoyad, plaka, ekBilgi);

                    if (yeniArac != null) {
                        crud.aracekle(yeniArac);
                        otopark.aracEkle();
                        System.out.println("Araç başarıyla eklendi.");
                    } else {
                        System.out.println("Geçersiz araç türü!");
                    }
                    break;

                case 2:
                    System.out.println("Çıkarılacak aracın plakasını girin: ");
                    String silinecekPlaka = scanner.nextLine();

                    if (crud.aracbul(silinecekPlaka) != null) {
                        crud.aracsil(silinecekPlaka);
                        otopark.removeCar();
                        System.out.println("Araç başarıyla çıkarıldı.");
                    } else {
                        System.out.println("Araç bulunamadı!");
                    }
                    break;

                case 3:
                    System.out.println("Otoparktaki araçlar: ");
                    for (Araclar arac : crud.listAraclar()) {
                        System.out.println("Tür: " + arac.getClass().getSimpleName() + ", Sahibi: " + arac.getSahipBilgileri() + ", Plaka: " + arac.getPlaka());
                    }
                    break;

                case 4:
                    System.out.println("Aranacak aracın plakasını girin: ");
                    String arananPlaka = scanner.nextLine();

                    Araclar bulunanArac = crud.aracbul(arananPlaka);
                    if (bulunanArac != null) {
                        System.out.println("Araç bulundu: " + bulunanArac.getClass().getSimpleName() + ", Sahibi: " + bulunanArac.getSahipBilgileri() + ", Plaka: " + bulunanArac.getPlaka());
                    } else {
                        System.out.println("Araç bulunamadı.");
                    }
                    break;

                case 5:
                    System.out.println("Otopark durumu: " + otopark.DoluAlanlar + "/" + kapasite);
                    break;

                case 6:
                    devam = false;
                    System.out.println("Çıkış yapılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim, tekrar deneyin.");
                    break;
            }
        }

        scanner.close();
    }
}







