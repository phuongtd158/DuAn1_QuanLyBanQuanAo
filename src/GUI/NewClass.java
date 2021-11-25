/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.HoaDonDAO;
import Entity.HoaDon;
import Ultil.XDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NewClass {

    public static void main(String[] args) {
        HoaDonDAO dao_hd = new HoaDonDAO();
        List<HoaDon> list = dao_hd.selectAll();
        List<Object> obj = new ArrayList<>();
        Calendar c1 = Calendar.getInstance();
        int thang = c1.get(Calendar.MONTH) + 1;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Calendar c = Calendar.getInstance();
        int ngayCuoiThang = c.getMaximum(Calendar.DATE);

        if (true) {
            for (HoaDon x : list) {
                if (x.getTrangThai().equalsIgnoreCase("Đơn hàng âm") && XDate.toMonth(x.getNgayTao()).equals(String.valueOf(thang))) {

                    obj.add("\n" + "+Mã hóa đơn: " + x.getMaHD() + "   " + "Lý do: " + x.getGhiChu());
                }
            }
        }

//        System.out.println("" + obj + thang+ngayCuoiThang);

        if (XDate.toDay(date).equals(String.valueOf(ngayCuoiThang)) && XDate.toTime(new Date()).equals("09:00 PM")) {
            Jfr_ThongKe.tk.sendEmail_Thang();
        }
        System.out.println("" + XDate.toDay(date));

        System.out.println("" + XDate.toTime(new Date()));
    }

}
