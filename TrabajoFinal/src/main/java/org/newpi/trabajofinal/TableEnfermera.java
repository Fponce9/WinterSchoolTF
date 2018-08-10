/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.newpi.trabajofinal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Proyecto
 */
public class TableEnfermera extends AbstractTableModel {
    
    String[] columnas = {
                            "Número",
                            "Nombre",
                            "Apellido",
                            "Direccion",
                            "Telefono",
                            "Codigo Servicio",
                            "Rotacion",
                            "Salario"                            
                        };
    
    ArrayList<Enfermera> enfermeras;
    
    public TableEnfermera() {
        try {
            enfermeras = new Conexion().getAllNurses();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }   
    
    @Override
    public int getRowCount() {
        return enfermeras.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
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
            case 0: val = enfermeras.get(rowIndex).getNumero(); break;
            case 1: val = enfermeras.get(rowIndex).getNombre(); break;
            case 2: val = enfermeras.get(rowIndex).getApellido(); break;
            case 3: val = enfermeras.get(rowIndex).getDireccion(); break;
            case 4: val = enfermeras.get(rowIndex).getTelefono(); break;
            case 5: val = enfermeras.get(rowIndex).getCodServicio(); break;
            case 6: val = enfermeras.get(rowIndex).getRotacion(); break;
            case 7: val = enfermeras.get(rowIndex).getSalario(); break;
            default: val = "";
        }
        return val;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }
     public void filter(String code, String name, String surname) {
        try {
            enfermeras = new Conexion().getAllNurses();
            if(!code.isEmpty())
                enfermeras.removeIf(p -> !p.getNumero().contains(code));
            if(!name.isEmpty())
                enfermeras.removeIf(p -> !p.getNombre().contains(name));
            if(!surname.isEmpty())
                enfermeras.removeIf(p -> !p.getApellido().contains(surname));
            this.fireTableDataChanged();
            this.fireTableChanged(null);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }    
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
    
    public Enfermera getEnfermeraAt(int rowIndex)
    {
        return enfermeras.get(rowIndex);
    }
    public String getNumeroAt(int rowIndex)
    {
        return this.getEnfermeraAt(rowIndex).getNumero();
    }
    
}
