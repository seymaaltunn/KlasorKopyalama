package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class KlasorAramaService {

    public Optional<Path> klasorBul(Path anaDizin, String klasorAdi) {

        if (anaDizin == null || !Files.exists(anaDizin)) {
            System.out.println("Ana dizin bulunamadı: " + anaDizin);
            return Optional.empty();
        }

        if (klasorAdi == null || klasorAdi.isBlank()) {
            System.out.println("Klasör adı boş olamaz.");
            return Optional.empty();
        }

        try (Stream<Path> yollar = Files.walk(anaDizin)) {

            return yollar
                    .filter(Files::isDirectory)
                    .filter(yol ->
                            yol.getFileName() != null &&
                                    yol.getFileName()
                                            .toString()
                                            .equalsIgnoreCase(klasorAdi))
                    .findFirst();

        } catch (IOException e) {
            System.out.println("Klasör araması sırasında hata oluştu.");
            e.printStackTrace();
            return Optional.empty();
        }
    }
}