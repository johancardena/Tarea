package Interfaz;

import Clases.Alquiler;
import Clases.Alquilerr;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlquilerInterfaz {
    public JPanel panelAlquiler;
    private JTextField clienteField;
    private JTextField vehiculoField;
    private JTextField fechaInicioField;
    private JTextField fechaFinField;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JTable alquilerTable;
    private JButton actualizarButton;

    private Alquilerr alquilerDAO;
    private DefaultTableModel model;

    public AlquilerInterfaz() {
        alquilerDAO = new Alquilerr();
        model = new DefaultTableModel(new String[]{"Cliente", "Vehículo", "Inicio", "Fin"}, 0);
        alquilerTable.setModel(model);
        cargarAlquileres();

        alquilerTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = alquilerTable.getSelectedRow();
            if (selectedRow != -1) {
                clienteField.setText(model.getValueAt(selectedRow, 0).toString());
                vehiculoField.setText(model.getValueAt(selectedRow, 1).toString());
                fechaInicioField.setText(model.getValueAt(selectedRow, 2).toString());
                fechaFinField.setText(model.getValueAt(selectedRow, 3).toString());
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cliente = clienteField.getText();
                String vehiculo = vehiculoField.getText();
                String fechaInicio = fechaInicioField.getText();
                String fechaFin = fechaFinField.getText();

                if (alquilerDAO.agregarAlquiler(cliente, vehiculo, fechaInicio, fechaFin)) {
                    cargarAlquileres();
                    JOptionPane.showMessageDialog(null, ":) Alquiler agregado");
                } else {
                    JOptionPane.showMessageDialog(null, ":( Error al agregar alquiler");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = alquilerTable.getSelectedRow();
                if (row != -1) {
                    String cliente = model.getValueAt(row, 0).toString();
                    if (alquilerDAO.eliminarAlquiler(cliente)) {
                        cargarAlquileres();
                        JOptionPane.showMessageDialog(null, ":) Alquiler eliminado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar");
                    }
                }
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = alquilerTable.getSelectedRow();
                if (row != -1) {
                    String cliente = clienteField.getText();
                    String vehiculo = vehiculoField.getText();
                    String fechaInicio = fechaInicioField.getText();
                    String fechaFin = fechaFinField.getText();

                    // Crear el objeto Clases.Alquiler para actualizar
                    Alquiler alquilerSeleccionado = new Alquiler(cliente, vehiculo, fechaInicio, fechaFin);

                    if (alquilerDAO.actualizarAlquiler(alquilerSeleccionado)) {
                        cargarAlquileres();
                        JOptionPane.showMessageDialog(null, " :) Alquiler actualizado");
                    } else {
                        JOptionPane.showMessageDialog(null, " :( Error al actualizar alquiler");
                    }
                }
            }
        });
    }

    private void cargarAlquileres() {
        model.setRowCount(0); // Limpiar la tabla antes de recargar los datos
        for (Alquiler alquiler : alquilerDAO.obtenerAlquileres()) {
            model.addRow(new Object[]{alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaInicio(), alquiler.getFechaFin()});
        }
    }
}
