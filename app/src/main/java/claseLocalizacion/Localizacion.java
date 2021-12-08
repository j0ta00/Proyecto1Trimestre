package claseLocalizacion;

import android.net.Uri;

public class Localizacion{
    //Atributos
    private String nombreLocalizacion;
    private Uri uriLocalizacion;

    //Constructores
    public Localizacion(String nombreLocalizacion, Uri uriLocalizacion) {
        this.nombreLocalizacion = nombreLocalizacion;
        this.uriLocalizacion = uriLocalizacion;
    }

    //Getters&Setters
    public String getNombreLocalizacion() {
        return nombreLocalizacion;
    }

    public void setNombreLocalizacion(String nombreLocalizacion) {
        this.nombreLocalizacion = nombreLocalizacion;
    }

    public Uri getUriLocalizacion() {
        return uriLocalizacion;
    }

    public void setUriLocalizacion(Uri uriLocalizacion) {
        this.uriLocalizacion = uriLocalizacion;
    }
}
