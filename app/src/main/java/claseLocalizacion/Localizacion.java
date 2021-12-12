package claseLocalizacion;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Localizacion implements Parcelable {
    //Atributos
    private String nombreLocalizacion;
    private Uri uriLocalizacion;

    //Constructores
    public Localizacion(String nombreLocalizacion, Uri uriLocalizacion) {
        this.nombreLocalizacion = nombreLocalizacion;
        this.uriLocalizacion = uriLocalizacion;
    }

    protected Localizacion(Parcel in) {
        nombreLocalizacion = in.readString();
        uriLocalizacion = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Localizacion> CREATOR = new Creator<Localizacion>() {
        @Override
        public Localizacion createFromParcel(Parcel in) {
            return new Localizacion(in);
        }

        @Override
        public Localizacion[] newArray(int size) {
            return new Localizacion[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombreLocalizacion);
        parcel.writeParcelable(uriLocalizacion, i);
    }
}
