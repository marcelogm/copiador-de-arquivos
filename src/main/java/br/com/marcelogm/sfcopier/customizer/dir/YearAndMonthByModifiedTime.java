package br.com.marcelogm.sfcopier.customizer.dir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class YearAndMonthByModifiedTime implements DirectoryBuilder {

    @Override
    public String apply(Path path) {
        try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            LocalDateTime date = LocalDateTime.ofInstant(attr.lastModifiedTime().toInstant(), ZoneId.systemDefault());
            return date.getYear() + File.separator + date.getMonth().getValue() + " - " + date.getMonth().toString().toLowerCase() + File.separator;
        } catch (Exception e) {
            return "";
        }
    }
}
