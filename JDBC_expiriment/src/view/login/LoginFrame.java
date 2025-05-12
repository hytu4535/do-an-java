package login;

import javax.swing.*;
import java.awt.*;
import controller.NguoiDungController;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Đăng nhập");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(93, 173, 118)); // xanh nhạt
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(loginLabel);
        leftPanel.add(Box.createVerticalGlue());

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(15, 40, 50)); // xanh đậm
        rightPanel.setLayout(null);

        JLabel userLabel = new JLabel("Tên đăng nhập");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        userLabel.setBounds(80, 80, 200, 30);
        rightPanel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(80, 110, 300, 30);
        rightPanel.add(usernameField);

        JLabel passLabel = new JLabel("Mật khẩu");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        passLabel.setBounds(80, 160, 200, 30);
        rightPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(80, 190, 300, 30);
        rightPanel.add(passwordField);

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(80, 250, 300, 40);
        loginButton.setBackground(new Color(93, 173, 118));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        rightPanel.add(loginButton);

        JLabel forgotLabel = new JLabel("Quên mật khẩu ?");
        forgotLabel.setForeground(Color.WHITE);
        forgotLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        forgotLabel.setBounds(80, 310, 200, 30);
        rightPanel.add(forgotLabel);

        loginButton.addActionListener(e -> handleLogin());

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);
        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = String.valueOf(passwordField.getPassword()).trim();

        if (NguoiDungController.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            dispose();
            new view.MainFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}