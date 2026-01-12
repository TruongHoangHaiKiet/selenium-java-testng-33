package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Element_Command {
    WebDriver driver; // Khai báo
    @BeforeClass
    public void initBrowser(){
        //Arrange: Pre-Condition
        // Init browser/ open page
        // Init class/ init data test
        // Open DB/...
        driver = new FirefoxDriver(); //Khởi tạo
        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_01_() {
        // Tương tác với element
        // Thao tác trực tiếp (Không cần khai báo biến)
        // driver.findElement(By.cssSelector(""));
        // Khai báo biến để sử dụng lại nhiều lần
        // Khai báo + Khởi tạo cùng lúc
        // Dấu = dung để gán dữ liệu vào biến
        WebElement webElement = driver.findElement(By.cssSelector("")); //***

        // Nhập giá trị vào những loại element có thể edit được (Editable): Textbox/ TextArea/ Dropdown
        webElement.sendKeys("kietthh@gmail.com"); //***

        // Xóa dữ liệu trước khi nhập (nếu có)
        webElement.clear();

        // Click vào 1 element: Button/ Checkbox/ Raido/ Link/ Image/ Icon/...(Clickable)
        webElement.click(); //***

        // Chỉ có tác dụng với Form (Login/ Register/ Search/...)
        // Tương tự như action Enter gủi request lên Server mà không cần click vào button
        webElement.submit();

        // Dùng cho tất cả các loại element dể kiểm tra nó hiển thị hoặc không
        // Tất cả các hàm có tiền tố isXXX đều cần phải kiểm tra tính đúng sai
        // Mong đợi 1 kết quả trả về là đúng
        Assert.assertTrue(webElement.isDisplayed()); //***

        // Để kiểm tra 1 element cho phép thao tác lên hay không (không bị disable)
        Assert.assertTrue(webElement.isEnabled()); //***
        Assert.assertFalse(webElement.isEnabled()); //***

        // Để kiểm tra 1 element đã chọn hay chưa
        // Dùng cho 3 loại element (Checkbox/ Radio/ Dropdown)
        Assert.assertTrue(webElement.isSelected()); //***
        Assert.assertFalse(webElement.isSelected());

        // Lấy ra text của element (Link/ Button/ Message/...)
        // Các hàm tiền tố getXXX sẽ trả về dữ liệu để dùng kiểm tra ở bước tiếp theo
        // Nếu kiểm tra trực tiếp thì không cần khai báo biến
        // Nếu dùng qua bước sau thì khai báo biến để sử dụng lại nhiều lần
        webElement.getText();
        Assert.assertEquals(webElement.getText(), "This is a required field");

        // New User
        String userID = webElement.getText();
        Assert.assertEquals(userID, "526812");


        // Dashboard
        Assert.assertEquals(userID, "526812");

        // Thường dùng ở các version của Selenium 4.x trở lên
        // Lấy ra thuộc tính bên cây Properties
        webElement.getDomAttribute("placeholder"); //***

        // Lấy ra thuộc tính bên cây Properties
        // Firefox cần show DOM Properties ra mới có bên DEV tool
        webElement.getDomProperty("placeholder");  //***
        webElement.getDomProperty("textContent");
        webElement.getDomProperty("value");
        webElement.getDomProperty("innerText");
        webElement.getDomProperty("validationMessage");

        // Lấy ra các thuộc tính về Css của element (FE)
        // Font/ Color/ Size/ Location/ Position/...
        // Firefox nằm ở tab Rules
        // Chrome/ Edge nằm ở tab Styles
        webElement.getCssValue("font-size"); //***
        webElement.getCssValue("background-color");
        webElement.getCssValue("font-family");
        webElement.getCssValue("text-align");

        // Xử lý Shadown DOM
        webElement.getShadowRoot();

        // Nằm trong tab Accessibility
        // Selenium version 4.x mới có
        webElement.getAccessibleName();
        webElement.getAriaRole();

        Point elementLocation = webElement.getLocation();
        elementLocation.getX();
        elementLocation.getY();
        // Lấy ra kích thước chiểu rộng/ chiều cao của element
        Dimension elementSize= webElement.getSize();

        elementSize.getWidth();
        elementSize.getHeight();

        // Bao gồm cả Kích thước và Vị trí của element
        Rectangle elementRect = webElement.getRect();
        Dimension elementSizeSecond = elementRect.getDimension();

        elementSizeSecond.getHeight();
        elementSizeSecond.getWidth();

        elementRect.getHeight();
        elementRect.getWidth();

        //Khi không biết tên thẻ của element là gì (trong trường hợp dùng Css để viết tắt Locator)
        webElement.getTagName();

        //Input
        // Lấy ra hình ảnh của trang/ element trả về kiểu File/ Byte/...
        webElement.getScreenshotAs(OutputType.BYTES);
        webElement.getScreenshotAs(OutputType.FILE);
        webElement.getScreenshotAs(OutputType.BASE64);


    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
