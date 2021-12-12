package clasesEmpresa;

import android.os.Parcel;
import android.os.Parcelable;

import claseLocalizacion.Localizacion;

public class EmpresaTecnologica extends Empresa implements Parcelable {
    //Atributos
    private String urlWebEmpresa;
    private Localizacion localizacion;
    private String emailDeContacto;
    private String telefono;

    //Constructores
    public EmpresaTecnologica(int idLogo, String nombreEmpresa, String urlWebEmpresa, Localizacion localizacion, String emailDeContacto, String telefono) {
        super(idLogo, nombreEmpresa);
        this.urlWebEmpresa = urlWebEmpresa;
        this.localizacion = localizacion;
        this.emailDeContacto = emailDeContacto;
        this.telefono=telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    protected EmpresaTecnologica(Parcel in) {
        super();
        setIdLogo(in.readInt());
        setNombreEmpresa(in.readString());
        urlWebEmpresa = in.readString();
        emailDeContacto = in.readString();
        localizacion = in.readParcelable(Localizacion.class.getClassLoader());
        telefono=in.readString();
    }

    public static final Creator<EmpresaTecnologica> CREATOR = new Creator<EmpresaTecnologica>() {
        @Override
        public EmpresaTecnologica createFromParcel(Parcel in) {
            return new EmpresaTecnologica(in);
        }

        @Override
        public EmpresaTecnologica[] newArray(int size) {
            return new EmpresaTecnologica[size];
        }
    };

    //Getters&Setters
    public String getUrlWebEmpresa() {
        return urlWebEmpresa;
    }

    public void setUrlWebEmpresa(String urlWebEmpresa) {
        this.urlWebEmpresa = urlWebEmpresa;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public String getEmailDeContacto() {
        return emailDeContacto;
    }

    public void setEmailDeContacto(String emailDeContacto) {
        this.emailDeContacto = emailDeContacto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getIdLogo());
        parcel.writeString(getNombreEmpresa());
        parcel.writeString(urlWebEmpresa);
        parcel.writeString(emailDeContacto);
        parcel.writeParcelable(localizacion,i);
        parcel.writeString(telefono);
    }
}
