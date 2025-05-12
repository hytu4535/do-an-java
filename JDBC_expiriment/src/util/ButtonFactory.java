/*##################
-LỚP NÀY TẠO CÁC NÚT CÓ ẢNH VÀ CHỮ(ẢNH BÊN TRÁI, CHỮ BÊN PHẢI)
//##################
*/
package util;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {

    public static void applyImageToButton(JButton button, String imagePath, int iconWidth, int iconHeight) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        
        System.out.println(imagePath);
        
        System.out.println(icon.getIconWidth());

        button.setIcon(resizedIcon);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);  // chữ bên phải
        button.setVerticalTextPosition(SwingConstants.CENTER);   // căn giữa dọc
        button.setFocusPainted(false);

    }
    
    // icon nằm trên text
    public static void applyImageAboveButton(JButton button, String imagePath, int iconWidth, int iconHeight) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        
        System.out.println(imagePath);
        
        System.out.println(icon.getIconWidth());

        button.setIcon(resizedIcon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);  
        button.setVerticalTextPosition(SwingConstants.BOTTOM);   // chữ bên dưới
        button.setFocusPainted(false);

    }

// phương thức trả về đường dẫn từ tên file ảnh
    public static String buildIconPath(String fileName) {
        return "src/resources/images/" + fileName;
    }


}

