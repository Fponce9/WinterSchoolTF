package org.newpi.trabajofinal;

public class Paciente {
    private String numero;
    private String nombre;
    private String apellido;
    private String codeService;
    private String roomNumber;
    private String lit;

    public Paciente() {
        this("", "", "", "", "", "");
    }

    public Paciente(String numero, String nombre, String apellido, String codeService, String roomNumber, String lit) {
        this.numero = numero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.codeService = codeService;
        this.roomNumber = roomNumber;
        this.lit = lit;
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodeService() {
        return codeService;
    }

    public void setCodeService(String codeService) {
        this.codeService = codeService;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getLit() {
        return lit;
    }

    public void setLit(String lit) {
        this.lit = lit;
    }
}






