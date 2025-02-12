package Interfaz;

import Clases.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    public JPanel panelLogin;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                Usuario usuarioDAO = new Usuario();
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(null, "El correo debe contener '@'.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (usuarioDAO.loginUsuario(email, password)) {
                    JOptionPane.showMessageDialog(null, " :) Login exitoso");

                    JFrame alquilerFrame = new JFrame("Gestión de Alquileres");
                    alquilerFrame.setContentPane(new AlquilerInterfaz().panelAlquiler);
                    alquilerFrame.setSize(400, 400);
                    alquilerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    alquilerFrame.setLocationRelativeTo(null);
                    alquilerFrame.setVisible(true);

                    ((JFrame) SwingUtilities.getWindowAncestor(panelLogin)).dispose();
                } else {
                    JOptionPane.showMessageDialog(null, " :( Credenciales incorrectas");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Registro de Usuario");
                frame.setContentPane(new Registro().panelRegistro);
                frame.setSize(300, 300);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
