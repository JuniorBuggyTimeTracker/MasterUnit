package solutions.pge.handler;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@ApplicationScoped
public class InitHandler {

    private static final Logger log = LoggerFactory.getLogger(InitHandler.class);

    private final DriverHandler racerHandler;

    @Inject
    public InitHandler(DriverHandler racerHandler) {
        this.racerHandler = racerHandler;
        try {
            this.racerHandler.init();
        } catch (Exception e){
            log.warn("Unable to read demo racers", e);
        }
    }
}
