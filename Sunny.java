public class Sunny extends Weather {
    public Sunny(CurrentWeatherData currentWeatherData){
        super(currentWeatherData);
        weatherType = WeatherEnum.SUNNY;
    }

    @Override
    public String getMessage() {
        message = "熱中症に気をつけてください。";
        return message;
    }

    @Override
    public String toString() {
        return String.format(
                "場所：%s / 天気情報：%s / 時刻=%tF %tT / 気温=%.1f℃ / 湿度=%d%% / 風速=%.1f m/s",
                currentWeatherdData.name, weatherType, time, time, currentWeatherdData.mainTemp,currentWeatherdData.mainHumidity, currentWeatherdData.windSpeed
        );
    }

}
