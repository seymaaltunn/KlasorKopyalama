import model.Evrak;
import repository.EvrakRepository;
import service.KlasorAramaService;
import service.KlasorKopyalamaService;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        System.out.println("Program başladı.");

        EvrakRepository evrakRepository = new EvrakRepository();

        KlasorKopyalamaService kopyalamaService =
                new KlasorKopyalamaService();

        List<Evrak> evraklar =
                evrakRepository.tumEvraklariGetir();

        KlasorAramaService klasorAramaService =
                new KlasorAramaService();



        Path anaDizin = Path.of("C:\\HukukEvraklari");

        System.out.println(
                "Bulunan evrak sayısı: " + evraklar.size()
        );

        for (Evrak evrak : evraklar) {

            Optional<Path> bulunanKlasor =
                    klasorAramaService.klasorBul(
                            anaDizin,
                            evrak.getKlasorAdi()
                    );

            if (bulunanKlasor.isPresent()) {

                Path hedef = Path.of(
                        "C:\\KopyalananEvraklar",
                        String.valueOf(evrak.getEvrakId()),
                        evrak.getKlasorAdi()
                );

                try {

                    kopyalamaService.klasoruKopyala(
                            bulunanKlasor.get(),
                            hedef
                    );

                    System.out.println(
                            "Kopyalandı : "
                                    + bulunanKlasor.get()
                                    + " -> "
                                    + hedef
                    );

                } catch (Exception e) {

                    System.out.println(
                            "Kopyalama hatası : "
                                    + evrak.getKlasorAdi()
                    );

                    e.printStackTrace();
                }

            } else {

                System.out.println(
                        "Bulunamadı : "
                                + evrak.getKlasorAdi()
                );
            }
        }

        System.out.println("Program bitti.");
    }
}