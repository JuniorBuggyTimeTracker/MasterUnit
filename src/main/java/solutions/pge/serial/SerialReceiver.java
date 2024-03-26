package solutions.pge.serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import io.quarkus.runtime.Startup;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class SerialReceiver implements SerialPortDataListener {
    private static final Logger log = LoggerFactory.getLogger(SerialReceiver.class);

    private final SerialPort serialPort;
    private final SerialMessageConverter serialMessageConverter;

    @Inject
    public SerialReceiver(SerialMessageConverter serialMessageConverter) {
        this.serialMessageConverter = serialMessageConverter;

        System.setProperty("fazecast.jSerialComm.appid", "JBTT-MU");
        serialPort = SerialPort.getCommPort("/dev/cu.usbmodem2201");
        log.debug("Found serial port: {}", serialPort.getPortDescription().trim());
        if(serialPort.openPort()){
            serialPort.addDataListener(this);
            log.debug("Connection for serial communication established");
        } else {
            log.error("Unable to establish serial communication");
        }
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] newData = event.getReceivedData();
        String line  = new String(newData).trim();
        log.debug("Received: " + line);
        serialMessageConverter.read(line);
    }
}
