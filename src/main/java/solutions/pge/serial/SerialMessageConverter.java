package solutions.pge.serial;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import solutions.pge.handler.MeasurementHandler;
import solutions.pge.models.business.Measurement;

import java.util.regex.Pattern;

@ApplicationScoped
public class SerialMessageConverter {

    private final MeasurementHandler measurementHandler;

    private final Pattern COMMAND_PATTERN = Pattern.compile("^(?<TYPE>[A-Z]{3}):(?<BODY>[A-Z0-9]*);$");

    @Inject
    public SerialMessageConverter(MeasurementHandler measurementHandler){
        this.measurementHandler = measurementHandler;
    }

    public void read(String line){
        var matcher = COMMAND_PATTERN.matcher(line);
        if(matcher.matches()){
            String type = matcher.group("TYPE");
            String body = matcher.group("BODY");

            switch (type){
                case "MES": {
                    var measurement = new Measurement(body);
                    measurementHandler.receive(measurement);
                }
            }
        }
    }
}
