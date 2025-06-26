# Otopark Yönetim Sistemi

Bu proje, araçların otoparkta takibini yapmayı sağlayan bir *Otopark Yönetim Sistemi* uygulamasıdır. Araç türlerini, sahip bilgilerini ve otopark durumunu yönetmek için GUI (Swing) ve CRUD işlemleri ile veritabanı entegrasyonu içerir.

## Proje Ekibi
Bu proje, Merve MIZRAKLI tarafından geliştirilmiştir.

## Özellikler

- *Araç Türleri:* Araba, Kamyon, Karavan
- *CRUD İşlemleri:*
  - Araç ekleme
  - Araç silme
  - Araç arama
  - Otoparktaki tüm araçları listeleme
- *Otopark Durumu Gösterimi:*
  - Boş ve dolu alanlar
  - Otopark kapasitesi
- *Swing GUI ile Kullanıcı Arayüzü*
- *Observer Design Pattern* kullanılarak otopark durumunun gözlemlenmesi

## Kurulum

### Gereksinimler
- *Java 8+*
- *MySQL Veritabanı*
- *JDBC Driver*



## Kullanım

1. Program başlatıldığında Swing tabanlı bir kullanıcı arayüzü açılır.
2. Menüden aşağıdaki işlemleri gerçekleştirebilirsiniz:
   - Araç ekleme, çıkarma, listeleme ve arama
   - Otopark durumu görüntüleme
3. Konsolda veya GUI'de gerekli çıktılar gösterilecektir.

## Mimari

- *Factory Design Pattern:* Araç nesneleri (Araba, Kamyon, Karavan) dinamik olarak oluşturulur.
- *Observer Design Pattern:* Otopark doluluk durumu gözlemcilere bildirilir.
- *Singleton Design Pattern:* Veritabanı bağlantısı tek bir örnekle yönetilir.
- *Abstract Class ve Interface Kullanımı:* Araç türleri için temel sınıf ve davranışlar tanımlandı.

## Dosya Yapısı

- **Main.java:** Programın başlangıç noktası.
- **Araclar.java:** Araçların temel sınıfı.
- **Araba.java, Kamyon.java, Karavan.java:** Araç türleri.
- **CRUD.java:** Veritabanı işlemleri.
- **OtoparkGUI.java:** Swing tabanlı kullanıcı arayüzü.
- **Singleton.java:** Veritabanı bağlantısını yöneten sınıf.
