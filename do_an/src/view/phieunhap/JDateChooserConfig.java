package view.phieunhap;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;


/*###############
-Lớp này sẽ có phuong thức để thay đổi màu nền cho component JDateChooser
-Vì lí do gì đấy phương thức chỉ mới thay đổi màu nền của icon và label của phần popup
###############*/


public class JDateChooserConfig {

    // Phương thức để điều chỉnh màu sắc cho JDateChooser với tham số màu nền
    public static void customizeDateChooser(JDateChooser dateChooser, Color bgColor) {
        // Thay đổi màu nền của JDateChooser (phần hiển thị ngày)
        dateChooser.setBackground(bgColor);  // Màu nền cho JDateChooser

        // Thay đổi màu nền của phần nút bấm chọn ngày (calendar button)
        dateChooser.getCalendarButton().setBackground(bgColor);  // Màu nền nút calendar

        // Thay đổi màu chữ của nút bấm chọn ngày
        dateChooser.getCalendarButton().setForeground(Color.BLACK);

        // Thay đổi màu chữ của phần hiển thị ngày
        dateChooser.setForeground(Color.BLACK);

        // Lấy đối tượng JCalendar (popup calendar) từ JDateChooser
        JCalendar calendar = dateChooser.getJCalendar();

        // Thay đổi màu nền của popup calendar (ngày, tuần, tháng)
        calendar.setBackground(bgColor);  // Màu nền cho popup calendar

        // Thay đổi màu nền cho các ngày trong popup calendar
        calendar.getDayChooser().setBackground(bgColor);  // Màu nền của phần ngày

        // Cập nhật màu chữ cho các ngày trong calendar
        calendar.getDayChooser().setForeground(Color.BLACK);  // Màu chữ của các ngày

        // Thay đổi màu nền cho các tháng trong popup calendar
        calendar.getMonthChooser().setBackground(bgColor);  // Màu nền của phần chọn tháng

        // Thay đổi màu chữ của tháng trong popup calendar
        calendar.getMonthChooser().setForeground(Color.BLACK);  // Màu chữ của tháng

        // Thay đổi màu nền cho các năm trong popup calendar
        calendar.getYearChooser().setBackground(bgColor);  // Màu nền của phần chọn năm

        // Thay đổi màu chữ của năm trong popup calendar
        calendar.getYearChooser().setForeground(Color.BLACK);  // Màu chữ của năm
    }
}
