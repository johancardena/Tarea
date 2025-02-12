package Interfaz;

import Clases.UsuarioDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JFrame{
    public JPanel panelRegistro;
    private JTextField nombreField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registrarButton;

    public Registro() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                // Validación: Verificar que el correo contenga '@'
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(null, " El correo debe contener '@'.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (usuarioDAO.registrarUsuario(nombre, email, password)) {
                    JOptionPane.showMessageDialog(null, " :) Usuario registrado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, " :( Email ya registrado");
                }
            }
        });
    }
}
