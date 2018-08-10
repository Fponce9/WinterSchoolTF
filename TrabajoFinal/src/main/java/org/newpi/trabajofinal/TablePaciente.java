package org.newpi.trabajofinal;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TablePaciente extends AbstractTableModel {
    String[] columnas = {
                            "Número",
                            "Nombre",
                            "Apellido",
                            "Código de Servicio",
                            "Número de Habitación",
                            "Número de Cama"
                        };
    
    ArrayList<Paciente> pacientes;
    
    public TablePaciente() {
        try {
            pacientes = new Conexion().getAllPatients();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }   
    
    @Override
    public int getRowCount() {
        return pacientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    public void filter(String number, String name, String service) {
        try {
            pacientes = new Conexion().getAllPatients();
            if(!number.isEmpty())
                pacientes.removeIf(p -> !p.getNumero().contains(number));
            if(!name.isEmpty())
                pacientes.removeIf(p -> !p.getNombre().contains(name));
            if(!service.isEmpty())
                pacientes.removeIf(p -> !p.getCodeService().contains(service));
            this.fireTableDataChanged();
            this.fireTableChanged(null);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }    
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String val;
        switch (columnIndex) {
            case 0: val = pacientes.get(rowIndex).getNumero(); break;
            case 1: val = pacientes.get(rowIndex).getNombre(); break;
            case 2: val = pacientes.get(rowIndex).getApellido(); break;
            case 3: val = pacientes.get(rowIndex).getCodeService(); break;
            case 4: val = pacientes.get(rowIndex).getRoomNumber(); break;
            case 5: val = pacientes.get(rowIndex).getLit(); break;
            default: val = "";
        }
        return val;
    }

    public Paciente getPatientAt(int rowIndex) {
        return pacientes.get(rowIndex);
    }
    
    public String getNumeroAt(int rowIndex) {
        return this.getPatientAt(rowIndex).getNumero();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
}
