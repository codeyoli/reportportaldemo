package utility;

import com.google.common.io.BaseEncoding;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TestLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger("binary_data_logger");


    /**
     * Logs files ( specify the file extension ) to the Report Portal
     *
     * @param file
     * @param message
     */
    public static void log(File file, String message) {
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
    }

    public static void log(byte[] bytes, String message) {
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}", BaseEncoding.base64().encode(bytes), message);
    }

    /**
     * Logs Base64 string the to Report Portal
     *
     * @param message
     */
    public static void logBase64(String message) {
        WebDriver driver = DriverUtils.getDriver();
        String screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}", screenshot, message);
    }

    /**
     * Logs text messages to the Report Portal, the log will be visible in the test
     * cases steps
     *
     * @param message
     */
    public static void log(String message) {
        LOGGER.info(message);
    }

    /**
     * Logs error messages to the Report Portal, the log will be visible in the test
     * case steps
     *
     * @param message
     */
    public static void log_err(String message) {
        LOGGER.error(message);
    }
}
