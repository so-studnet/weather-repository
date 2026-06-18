import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class OpenWeatherAPI {

    private static final String API_KEY = "ac7920c6ae71090a87e23a78af9decb7";

    public static String getCurrentWeather(){
        return getCurrentWeather("Osaka");
    }

    public static String getCurrentWeather (String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + API_KEY + "&units=metric&lang=ja";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        String json = "";
        try {
            json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(json);

        // ===== JSON手動パース =====

        // coord
        double lon = extractDouble(json, "\"lon\":", ",");
        double lat = extractDouble(json, "\"lat\":", "}");

        // weather[0]
        double weatherId = extractDouble(json, "\"id\":", ",");
        String weatherMain = extractString(json, "\"main\":\"", "\"");
        String weatherDescription = extractString(json, "\"description\":\"", "\"");
        String weatherIcon = extractString(json, "\"icon\":\"", "\"");

        // base
        String base = extractString(json, "\"base\":\"", "\"");

        // main
        double temp = extractDouble(json, "\"temp\":", ",");
        double feelsLike = extractDouble(json, "\"feels_like\":", ",");
        double tempMin = extractDouble(json, "\"temp_min\":", ",");
        double tempMax = extractDouble(json, "\"temp_max\":", ",");
        double pressure = extractDouble(json, "\"pressure\":", ",");
        double humidity = extractDouble(json, "\"humidity\":", ",");
        double seaLevel = extractDouble(json, "\"sea_level\":", ",");
        double grndLevel = extractDouble(json, "\"grnd_level\":", "}");

        // visibility
        double visibility = extractDouble(json, "\"visibility\":", ",");

        // wind
        double windSpeed = extractDouble(json, "\"speed\":", ",");
        double windDeg = extractDouble(json, "\"deg\":", ",");
        double windGust = extractDouble(json, "\"gust\":", "}");

        // clouds
        double cloudsAll = extractDouble(json, "\"all\":", "}");

        // dt
        double dt = extractDouble(json, "\"dt\":", ",");

        // sys
        double sysType = extractDouble(json, "\"type\":", ",");
        double sysId = extractDouble(json, "\"id\":", ",");
        String country = extractString(json, "\"country\":\"", "\"");
        double sunrise = extractDouble(json, "\"sunrise\":", ",");
        double sunset = extractDouble(json, "\"sunset\":", "}");

        // timezone
        double timezone = extractDouble(json, "\"timezone\":", ",");

        // city information
        double cityId = extractDouble(json, "\"id\":", ",");
        String cityName = extractString(json, "\"name\":\"", "\"");

        // response code
        double cod = extractDouble(json, "\"cod\":", "}");



        return weatherMain;
    }

    // 数値抽出
    private static double extractDouble(String json, String key, String end) {
        int start = json.indexOf(key);
        if (start == -1) return 0;

        start += key.length();
        int finish = json.indexOf(end, start);

        return Double.parseDouble(json.substring(start, finish));
    }

    // 文字列抽出
    private static String extractString(String json, String key, String end) {
        int start = json.indexOf(key);
        if (start == -1) return "";

        start += key.length();
        int finish = json.indexOf(end, start);

        return json.substring(start, finish);
    }
}