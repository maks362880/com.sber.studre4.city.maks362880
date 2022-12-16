import model.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
         * Поиск города с наибольшим количеством жителей;
         */

        City maxPopulationCity = cityList.stream().max((x, y) -> x.getPopulation() - y.getPopulation()).get();
        System.out.println("[" + cityList.indexOf(maxPopulationCity) + "] = "
                + new DecimalFormat( "###,###" ).format(maxPopulationCity.getPopulation()));

    }
}