package javaBasic;

import org.openqa.selenium.By;
import selenium.Topic_01_Environment;

import java.util.ArrayList;
import java.util.List;

public class Topic_01_DataType {
    // I - Kiểu dữ liệu nguyên thủy (Primitive Type)
    // Kiểu kí tự
        char aChar = 'A';
    // Kiểu số nguyên
    // Số không có phần thập phân
    byte bNumber = 127;
    short sNumber = 32000;
    int iNumber = 172384;
    long lNumber = 1993123;


    // Kiểu số thực
    // Số có phần thập phân: điểm số/ lương/...
    float fNumber = 15.6f;
    double dNumber = 16.9d;

    // Kiểu logic
    boolean sex = true;

    // I - Kiểu dữ liệu tham chiếu (Reference Type)
    // Kiểu chuỗi
    String studentAddress = "123 Le Loi - Ho Chi Minh City";

    // Kiểu mảng
    String[] studentCity = {"Ha Noi", "Da Nang", "Ho Chi Minh"};
    int[] studentNumber = {25,30,33,44};

    // Kiểu class/ interface
    Topic_01_Environment topic = new Topic_01_Environment();
    IWebDriver iWebDriver;;

    // Collection/ Object
    List<String> address = new ArrayList<String>();
    Object name = "Automation Testing";
    By by = By.name("");

    // Khai báo 1 biến
    // Access Modifier: Phạm vi truy cập
    // private/ default/ protected/ public
    // Sinh ra ở đâu và nơi nào sử dụng nó

    // Loại kiểu dữ liệu
    // Kiểu chuỗi: String

    // Đặt tên: lower camel case - studentName
    // Giá trị của dữ liệu: gán cho biến qua dấu =
}
