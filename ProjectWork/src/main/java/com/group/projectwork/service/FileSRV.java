package com.group.projectwork.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSRV {
	
    @Value("${file.basePath}")
    private String basePath;

    public String saveFile(String cartellaDest, String nomeFile, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // Pulizia path e nome file da caratteri
                                                                             // speciali
        fileName = fileName.replace(" ", "");

        String cartellaOut = "/" + cartellaDest; // Percorso relativo del file
        cartellaDest = basePath + cartellaDest; // Percorso interno su cui il file verrà scritto

        // Costruisco il puntamento alla cartella di destinazione
        Path destinazione = Paths.get(cartellaDest);

        if (!Files.exists(destinazione)) { // Crea la destinazione se non esiste
            Files.createDirectories(destinazione);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = destinazione.resolve(fileName); // Risolve il percorso completo di nome file
            // Scrive su FS sovvrascrivendo eventuale file con lo stesso nome
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return cartellaOut + "/" + fileName;

        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + nomeFile, ioe);
        }
    }
}
