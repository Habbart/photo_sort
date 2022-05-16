package renamer_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    /**
     * Создает папку, если директория с таким названием не существуетю
     *
     * @param path
     * @return
     */
    public static Path createPackageIfNotExist(Path path) {
        System.out.println("начало работы createPackageIfNotExist: " + path.toString());
        if (!Files.exists(path)) {
            Path directories = null;
            try {
                directories = Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("создали директорию createPackageIfNotExist: " + directories);

            return directories;
        }

        return path;
    }

    /**
     * Возвращает список путей всех файлов в указаном пути
     * @param path
     * @return
     */
    public static List<Path> pathsOfAllFiles(Path path) {
        List<Path> collect = null;
        try (Stream<Path> paths = Files.walk(path)) {
            collect = paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return collect;
    }
}
