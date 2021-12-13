package clasePersona;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Persona{
    private int idFoto;
    private String nombre;
    private String apellidos;
    private String numeroTelefono;

    public Persona(int idFoto,String nombre, String apellidos, String numeroTelefono) {
        this.idFoto=idFoto;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroTelefono = numeroTelefono;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
