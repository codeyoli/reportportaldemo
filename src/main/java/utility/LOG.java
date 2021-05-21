package utility;

import com.google.common.io.BaseEncoding;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.io.File;

public class LOG {

    private static final Logger LOGGER = LoggerFactory.getLogger("binary_data_logger");


    /**
     * Logs files ( specify the file extension ) to the Report Portal
     *
     * @param file
     * @param message
     */
    public static void file(File file, String message) {
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
    public static void screenshot(String message) {
        WebDriver driver = DriverUtils.getDriver();
        String screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}", screenshot, message);
    }

    public static void screenshot_err(String message) {
        WebDriver driver = DriverUtils.getDriver();
        String screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        LOGGER.error("RP_MESSAGE#BASE64#{}#{}", screenshot, message);
    }

    /**
     * Logs text messages to the Report Portal, the log will be visible in the test
     * cases steps
     *
     * @param message
     */
    public static void step(String message) {
        LOGGER.info(message);
    }

    /**
     * Logs error messages to the Report Portal, the log will be visible in the test
     * case steps
     *
     * @param message
     */
    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void json(String json) {
        LOGGER.info(json);
    }
}
