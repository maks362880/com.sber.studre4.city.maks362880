import model.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main {

    private static final String FILE_NAME = "resources/city_ru.csv";

    public static void main(String[] args) {
        var cityList = new ArrayList<City>();
        try (var scanner = new Scanner(new File(FILE_NAME)).useDelimiter("[;]")) {
            scanner.next();
            while (scanner.hasNext()) {
                var name = scanner.next();
                var region = scanner.next();
                var district = scanner.next();
                var population = scanner.nextInt();
                var tempFoundation = scanner.next();
                var foundation = tempFoundation.split("(\\r\\n|\\r|\\n)");
                cityList.add(new City(name, region, district, population, foundation[0]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        /**
         * Определить количество городов в разрезе регионов;
         */

        HashMap<String, Integer> countCityOfRegion = new HashMap<>();
        cityList.forEach(e -> countCityOfRegion.compute(e.getRegion(), (key, value)-> value==null? 1 : ++value));

        countCityOfRegion.forEach((key, value) -> {
            System.out.println(key + " - " + value);
        });

    }
}