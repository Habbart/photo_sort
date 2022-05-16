package renamer_files;

import java.nio.file.Path;

public class FileNameParser {

    /**
     * Метод парсит имя файла следующего формата: *yyyy*mm*dd
     * Возвращает путь до папки с указанием года, а внутри с указанием месяца + года.
     * Пример: IMG_20210523_211249 => .../2021/MAY 2021
     *
     * @param filePath файла
     * @return путь до директории
     */
    public Path parseFileName(Path filePath) {

        System.out.println("начало работы FileNameParser.parseFileName: " + filePath.toString());
        //убираем все НЕцифры
        String fileName = filePath.getFileName().toString().replaceAll("\\D", "");
        //берём год и формируем название подпапки
        String year = fileName.substring(0, 4);
        String fullName = nameOfMonth(fileName.substring(4, 6)) + " " + fileName.substring(0, 4);

        Path result = Path.of(filePath.getParent().toString() + "\\" + year + "\\" + fullName);

        System.out.println("конец работы FileNameParser.parseFileName: " + result);

        return result;
    }


    private String nameOfMonth(String numberOfMonth) {

        int month = Integer.parseInt(numberOfMonth);

        if (month <= 0 || month > 12) throw new IllegalArgumentException("Месяц больше 12 или меньше 0");

        return MonthInRussian.values()[month - 1].toString();
    }

}
