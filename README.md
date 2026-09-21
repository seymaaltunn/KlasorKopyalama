# KlasorKopyalama

Java ve Maven kullanılarak geliştirilen, veritabanındaki evrak kayıtlarını temel alarak arşiv klasörlerini bulmayı ve gerekli klasörleri hedef dizine kopyalamayı kolaylaştıran yardımcı uygulamadır.

## Proje Hakkında

KlasorKopyalama, kurum arşivinde çok sayıda klasör bulunması nedeniyle tüm arşivin taşınması yerine ihtiyaç duyulan evrak klasörlerinin belirlenip kopyalanması amacıyla geliştirilmiştir.

Uygulama SQL Server veritabanındaki evrak kayıtlarından yararlanır, ilgili klasörleri kaynak dizinde arar ve bulunan klasörleri hedef dizine kopyalar.

## Kullanılan Teknolojiler

- Java
- Maven
- Microsoft SQL Server
- JDBC
- Java NIO (`Files`, `Path`)

## Proje Yapısı

```text
KlasorKopyalama
|
+-- src
|   +-- database
|   |   +-- DatabaseConnection.java
|   |
|   +-- model
|   |   +-- Evrak.java
|   |
|   +-- repository
|   |   +-- EvrakRepository.java
|   |
|   +-- service
|   |   +-- KlasorAramaService.java
|   |   +-- KlasorKopyalamaService.java
|   |
|   +-- KlasorKopyalama.java
|   +-- Main.java
|
+-- pom.xml
+-- .gitignore
```

## Temel Bileşenler

### DatabaseConnection

SQL Server veritabanı bağlantısının oluşturulmasından sorumludur.

### Evrak

Veritabanından alınan evrak bilgilerinin uygulama içerisinde temsil edilmesini sağlayan model sınıfıdır.

### EvrakRepository

Veritabanındaki evrak kayıtlarının okunmasıyla ilgili işlemleri gerçekleştirir.

### KlasorAramaService

Evrak kayıtlarından elde edilen bilgilere göre kaynak arşiv içerisinde ilgili klasörlerin bulunmasını sağlar.

### KlasorKopyalamaService

Bulunan klasörlerin ve içeriklerinin hedef dizine kopyalanmasını gerçekleştirir. Dosya ve klasör işlemlerinde Java NIO API'sinden yararlanılır.

## Genel Çalışma Akışı

```text
SQL Server
    |
    v
EvrakRepository
    |
    v
Evrak kayıtları
    |
    v
KlasorAramaService
    |
    v
Kaynak klasörün bulunması
    |
    v
KlasorKopyalamaService
    |
    v
Hedef dizine kopyalama
```

Bu yapı sayesinde arşivde bulunan tüm klasörlerin taşınması yerine yalnızca gerekli kayıtlarla ilişkili klasörler üzerinde işlem yapılabilir.

## Veritabanı Bağlantısı

Projede Microsoft SQL Server JDBC bağlantısı kullanılmaktadır. Mevcut geliştirme ortamındaki bağlantı bilgileri `DatabaseConnection.java` içerisinde tanımlanmıştır.

Projeyi farklı bir bilgisayarda veya farklı bir SQL Server ortamında çalıştırırken sunucu ve veritabanı bilgilerinin ilgili ortama göre düzenlenmesi gerekir.

## Maven

Proje bağımlılıkları ve yapılandırması `pom.xml` üzerinden yönetilir.

Maven yüklü bir ortamda proje klasöründe aşağıdaki komut kullanılabilir:

```bash
mvn clean package
```

Derleme sonucunda oluşan `target` klasörü kaynak kodun bir parçası olmadığı için Git repository'sine dahil edilmez.

## Git Repository Notları

Aşağıdaki geliştirme ve derleme çıktıları `.gitignore` ile repository dışında tutulmaktadır:

```text
.idea/
target/
*.iml
out/
.vscode/
```

## Amaç

Projenin temel amacı, kurumun mevcut arşiv yapısındaki çok sayıda klasör arasından ihtiyaç duyulan evrak klasörlerinin belirlenmesini ve yeni hedef yapıya kontrollü biçimde kopyalanmasını kolaylaştırmaktır.
