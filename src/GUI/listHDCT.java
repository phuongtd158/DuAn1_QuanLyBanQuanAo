/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ASUS
 */
class listHDCT {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();

        // hiển thị ngày hiện tai
        // Calendar.DAY_OF_MONTH sẽ cho biết ngày hiện tại của tháng hiện tại
        // hàm get() sẽ trả về giá trị ngày hiện tại
        System.out.println("Ngày hiện tại là " + cal.get(Calendar.DAY_OF_MONTH));

        // hiển thị tháng hiện tại
        // sử dụng Calendar.MONTH
        // vì tháng trong Java sẽ chạy từ 0 đến 11
        // tức là nếu hiện giờ là tháng 7 thì chương trình sẽ hiển thị là tháng 6
        // vì vậy để hiển thị đúng thì ta sẽ cộng thêm tháng đó cho 1
        System.out.print("Tháng hiện tại là ");
        int thang =cal.get(Calendar.MONTH) + 1;
        System.out.println(thang);
        
        System.out.println(""+java.time.LocalDateTime.now());
        
    }
}
