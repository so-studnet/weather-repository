import java.util.Calendar;

public abstract class CurrentWeather extends Object {
    protected WeatherEnum weather = null;
    protected Calendar time = Calendar.getInstance();
    protected double temperature = 0.0;
    protected double windSpeed = 0.0;
    protected double chanceOfRain = 0.0;

    @Override
    public String toString() {
        return String.format(
                "天気情報：%s / 時刻=%tF %tT / 気温=%.1f℃ / 風速=%.1f m/s",
                weather, time, time, temperature, windSpeed
        );
    }
    
    public static CurrentWeather getInstance(){

        String weather = OpenWeatherAPI.getCurrentWeather();

        switch (weather) {
            case "晴れ":
                return new Sunny();
            case "Clouds":
                return new Cloudy();
            default:
                return null;
        }
        
    }


}
