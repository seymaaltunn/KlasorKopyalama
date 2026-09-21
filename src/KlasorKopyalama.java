import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class KlasorKopyalama {

    public static void klasoruKopyala(
            File kaynak,
            File hedef
    ) throws IOException {

        if (!kaynak.exists()) {
            throw new IOException(
                    "Kaynak klasör bulunamadı: "
                            + kaynak.getAbsolutePath()
            );
        }

        if (!kaynak.isDirectory()) {
            throw new IOException(
                    "Kaynak bir klasör değil."
            );
        }

        // h.klasör yoksa olustur
        if (!hedef.exists()) {

            boolean olusturuldu = hedef.mkdirs();

            if (!olusturuldu) {
                throw new IOException(
                        "Hedef klasör oluşturulamadı: "
                                + hedef.getAbsolutePath()
                );
            }
        }

        File[] dosyalar = kaynak.listFiles();

        if (dosyalar == null) {
            throw new IOException(
                    "Kaynak klasörün içeriği okunamadı."
            );
        }

        // k.klasör içini dolaş
        for (File dosya : dosyalar) {

            File yeniHedef = new File(
                    hedef,
                    dosya.getName()
            );

            if (dosya.isDirectory()) {

                // alt.klasör için
                klasoruKopyala(
                        dosya,
                        yeniHedef
                );

            } else if (izinVerilenDosyaMi(dosya)) {


                Files.copy(
                        dosya.toPath(),
                        yeniHedef.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );

                System.out.println(
                        "Kopyalandı: " + dosya.getName()
                );

            } else {

                System.out.println(
                        "Atlandı: " + dosya.getName()
                );
            }
        }
    }

    private static boolean izinVerilenDosyaMi(File dosya) {

        String dosyaAdi = dosya.getName().toLowerCase();

        return dosyaAdi.endsWith(".txt")
                || dosyaAdi.endsWith(".html")
                || dosyaAdi.endsWith(".htm");
    }
}