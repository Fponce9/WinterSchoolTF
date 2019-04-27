
package org.newpi.trabajofinal;


public class Enfermera {

    private String numero;
    private String codServicio;
    private String rotacion;
    private String Salario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

    public Enfermera() {
        this("","","","","","","","");
    }

    public Enfermera(String numero, String nombre, String apellido,
    String direccion, String telefono, String codServicio, String rotacion, String Salario) {
        this.numero = numero;
        this.codServicio = codServicio;
        this.rotacion = rotacion;
        this.Salario = Salario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(String codServicio) {
        this.codServicio = codServicio;
    }

    public String getRotacion() {
        return rotacion;
    }

    public void setRotacion(String rotacion) {
        this.rotacion = rotacion;
    }

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String Salario) {
        this.Salario = Salario;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    

    
    
}
