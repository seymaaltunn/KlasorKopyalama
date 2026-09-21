package service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class KlasorKopyalamaService {

    public void klasoruKopyala(Path kaynak, Path hedef) throws IOException {

        Files.walkFileTree(kaynak, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir,
                                                     BasicFileAttributes attrs)
                    throws IOException {

                Path hedefKlasor =
                        hedef.resolve(kaynak.relativize(dir));

                Files.createDirectories(hedefKlasor);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attrs)
                    throws IOException {

                Path hedefDosya =
                        hedef.resolve(kaynak.relativize(file));

                Files.copy(
                        file,
                        hedefDosya,
                        StandardCopyOption.REPLACE_EXISTING
                );

                return FileVisitResult.CONTINUE;
            }
        });
    }
}