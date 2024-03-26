package solutions.pge.handler;

import io.vertx.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solutions.pge.models.Measurement;
import solutions.pge.serial.SerialReceiver;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class MeasurementHandler {

    private static final Logger log = LoggerFactory.getLogger(MeasurementHandler.class);
    private final Map<String, Measurement> MEASUREMENTS = new HashMap<>();
    private final EventBus bus;

    @ConfigProperty(name = "measurement.threshold")
    public Long threshold;

    @Inject
    public MeasurementHandler(EventBus eventBus){
        this.bus = eventBus;
    }

    public void receive(Measurement measurement){
        var lastMeasurement = MEASUREMENTS.get(measurement.id());
        if(lastMeasurement == null){
            log.info("New measurement of {} at {}", measurement.id(), measurement.timestamp());
            MEASUREMENTS.put(measurement.id(), measurement);
            bus.publish("MEASUREMENTS", measurement);
            return;
        }

        long diff = ChronoUnit.MILLIS.between(lastMeasurement.timestamp(), measurement.timestamp());
        if(diff > threshold){
            log.info("New measurement of {} at {}", measurement.id(), measurement.timestamp());
            MEASUREMENTS.put(measurement.id(), measurement);
            bus.publish("MEASUREMENTS", measurement);
        }
    }
}
