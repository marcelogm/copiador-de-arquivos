package model;

import lombok.*;

import java.nio.file.Path;

@Data
@AllArgsConstructor
@Generated
@EqualsAndHashCode
public class FileToOrder {
    private String name;
    private Path path;
    private Integer count;
}
