package clasePersona;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Persona implements Parcelable {
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

    protected Persona(Parcel in) {
        idFoto = in.readInt();
        nombre = in.readString();
        apellidos = in.readString();
        numeroTelefono = in.readString();
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idFoto);
        parcel.writeString(nombre);
        parcel.writeString(apellidos);
        parcel.writeString(numeroTelefono);
    }
}
