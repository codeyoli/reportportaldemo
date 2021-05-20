package base;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.DriverUtils;

public class UIActions {

    // Global variable
    private static WebDriver driver;
    private static WebDriverWait waits;


    public UIActions() {
        driver = DriverUtils.getDriver();
        waits = new WebDriverWait(driver, 30);
    }

    //region BROWSER RELATED METHODS
    protected void gotoSite(String url) {
        driver.get(url);
    }

    protected void maximize() {
        driver.manage().window().maximize();
    }

    protected void fullScreen() {
        driver.manage().window().fullscreen();
    }

    protected void setResolutions(int width, int height) {
        Dimension size = new Dimension(width, height);
        driver.manage().window().setSize(size);
    }

    protected void refresh() {
        driver.navigate().refresh();
    }

    protected void goFoward() {
        driver.navigate().forward();
    }

    protected void goBack() {
        driver.navigate().back();
    }

    protected String title() {
        return driver.getTitle();
    }

    protected String getCurrentURL() {
        return driver.getCurrentUrl();
    }
    //endregion


    //region ELEMENT RELATED METHODS
    protected void click(By locator) {

        WebElement element = waits.until(ExpectedConditions.elementToBeClickable(locator));
        highlight(element);
        element.click();

    }

    protected void click(String text) {
        By locator = xpath("//*[text()='" + text + "']");
        List<WebElement> allElems = findAllElements(locator);
        if (allElems.size() > 0) {
            for (WebElement element : allElems) {
                if (element.getText().equalsIgnoreCase(text)) {
                    highlight(element);
                    element.click();
                    break;
                }
            }
        }
    }

    protected void doubleClick(By locator) {
        WebElement element = findElement(locator);
        highlight(element);
        Actions actions = new Actions(driver);
        actions.doubleClick(element);
        actions.perform();
    }

    protected void rightClick(By locator) {
        WebElement element = findElement(locator);
        highlight(element);
        Actions actions = new Actions(driver);
        actions.contextClick(element);
        actions.perform();
    }

    protected void dragAndDrop(By form, By target) {
        WebElement fromElem = findElement(form);
        highlight(fromElem);
        WebElement targetElem = findElement(target);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(fromElem, targetElem);
        actions.perform();
    }

    protected void selectOptionsWithText(By locator, String optionText) {
        WebElement element = findElement(locator);
        highlight(element);
        Select dropdown = new Select(element);
        for (WebElement opt : dropdown.getOptions()) {
            if (opt.getText().equals(optionText)) {
                highlight(opt);
                opt.click();
                break;
            }
        }
    }

    protected void selectOptionsWithValue(By locator, String value) {
        WebElement element = findElement(locator);
        highlight(element);
        Select dropdown = new Select(element);
        for (WebElement opt : dropdown.getOptions()) {
            String extractedAttrValue = opt.getAttribute("value");
            if (extractedAttrValue.equals(value)) {
                highlight(opt);
                opt.click();
                break;
            }
        }
    }

    protected WebElement moveElementToDisplay(By locator) {
        WebElement element = findElement(locator);
        highlight(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return element;
    }

    protected WebElement moveElementToDisplay(WebElement element) {
        highlight(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return element;
    }

    protected void hover(By locator) {
        WebElement element = findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    protected WebElement findElement(By locator) {
        WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    protected void highlight(By locator) {
        WebElement element = findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
    }

    protected void highlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
    }

    protected void write(By locator, String text) {
        try {
            WebElement input = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
            highlight(input);
            input.sendKeys(text);
        } catch (Exception e) {
            System.out.println("====CLICK ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl());
            System.out.println("Element :  " + locator.toString());
            System.out.println("=======================================");
        }
    }

    protected void clearThenWrite(By locator, String text) {
        try {
            WebElement input = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
            input.clear();
            input.sendKeys(text);
        } catch (Exception e) {
            System.out.println("====CLICK ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl());
            System.out.println("Element :  " + locator.toString());
            System.out.println("=======================================");
        }
    }

    protected void clear(By locator) {
        try {
            WebElement input = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
            input.clear();
        } catch (Exception e) {
            System.out.println("====CLICK ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl());
            System.out.println("Element :  " + locator.toString());
            System.out.println("=======================================");
        }
    }

    protected String getText(By locator) {
        WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    protected boolean elementIsVisible(By locator) {
        try {
            WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
            highlight(element);
            return element.isDisplayed();  // true, false
        } catch (Exception e) {
            System.out.println("====Element Visibility ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl());
            System.out.println("Determining the element visibility has failed.");
            System.out.println("=======================================");
        }
        return false;
    }

    protected boolean elementIsPresent(By locator) {
        try {
            WebElement element = waits.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException te) {
            return false;
        }
    }

    protected boolean elementIsEnabled(By locator) {
        try {
            WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isEnabled();
        } catch (Exception e) {
            System.out.println("====Element Enablement ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl());
            System.out.println("Determining the element's enablement has failed.");
            System.out.println("=======================================");
        }
        return false;
    }

    protected boolean elementIsSelected(By locator) {
        try {
            WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isSelected();
        } catch (Exception e) {
            System.out.println("====Element SELECTED ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl());
            System.out.println("Determining the element's enablement has failed.");
            System.out.println("=======================================");
        }
        return false;
    }
    //endregion


    //region MULTIPLE ELEMENT RELATED METHODS
    protected List<WebElement> findAllElements(By locator) {
        return waits.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected boolean clickNthElementWithText(By locator, String text) {
        List<WebElement> allElems = waits.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        for (WebElement element : allElems) {
            if (element.getText().equals(text)) {
                moveElementToDisplay(element);
                highlight(element);
                element.click();
                return true;
            }
        }
        // if code execution reaches here
        return false;
    }

    protected List<String> getAllTexts(By locator) {
        List<WebElement> allElems = waits.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        List<String> allTexts = new ArrayList<>();  // empty list of string with size 0

        if (allElems.size() > 0) {
            for (WebElement element : allElems) {
                String currentText = element.getText();
                allTexts.add(currentText);
            }
        }
        return allTexts;
    }
    //endregion


    //region TIME & WAITS RELATED METHODS
    protected void waitfor(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException ie) {
            System.out.println("====WAIT ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl());
            System.out.println("Browser has failed to wait for specified time");
            System.out.println("=======================================");
        }
    }

    protected void waitforMili(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException ie) {
            System.out.println("====WAIT ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl());
            System.out.println("Browser has failed to wait for specified time");
            System.out.println("=======================================");
        }
    }

    protected void waitUntilElementIsInvisible(By locator) {
        waits.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    //endregion


    //region ELEMENT LOCATING STRATEGY
    protected By css(String expression) {
        return By.cssSelector(expression);
    }

    protected By xpath(String expression) {
        return By.xpath(expression);
    }

    protected By id(String value) {
        return By.id(value);
    }

    protected By name(String value) {
        return By.name(value);
    }

    protected By classAttr(String value) {
        return By.className(value);
    }

    protected By link(String text) {
        return By.linkText(text);
    }

    protected By linkContains(String text) {
        return By.partialLinkText(text);
    }

    protected By tag(String tagname) {
        return By.tagName(tagname);
    }
    //endregion
}
