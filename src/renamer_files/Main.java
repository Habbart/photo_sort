package renamer_files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


/*  Есть список файлов с датами в названии. Даты перемешаны по годам и месяцам.
    Файлы не могут быть вложены в другие папки, кроме изачальной.
    Надо файлы сложить в папки с соответствующим названием. Например файл с названием IMG_20210523_211249 должен быть положен в папку MAY 2021.
    Папки создаются в той же папке в которой лежат все файлы.

    1. Читаем из консоли ссылку на папку с файлами
    2. Берем все файлы по очереди
    3. Парсим название файла. Год - первые четыре цифры, месяц - вторые четыре цифры
    4. Проверяем, есть ли папка с таким годом
        4.1 если есть - проверяем есть ли папка с таким месяцем и годом
            4.1.1 есть есть - перемещаем файл туда
            4.1.2 если нет - создаем папку и перемещаем файл
        4.2 если нет - создаем соответствующую папку,  в ней создаем подпапку и перемещаем файл

    5. Profit
 */

public class Main {

    public static void main(String[] args) {

        //C:\JAVA POG\тестовые\test_data
        FileNameParser fileNameParser = new FileNameParser();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            //Читаем из консоли ссылку на папку с файлами
            String readLine = reader.readLine();

            Path path = Paths.get(readLine);

            List<Path> filesPaths = Utils.pathsOfAllFiles(path);

            filesPaths.forEach(file -> {
                Path pathTemp = fileNameParser.parseFileName(file);
                pathTemp = Utils.createPackageIfNotExist(pathTemp);
                try {
                    Files.move(file, pathTemp.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
