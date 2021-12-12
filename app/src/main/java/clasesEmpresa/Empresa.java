package clasesEmpresa;

import android.os.Parcelable;

public abstract class Empresa{
    //Atributos
    private int idLogo;
    private String nombreEmpresa;

    //Constructores
    protected Empresa(int idLogo, String nombreEmpresa) {
        this.idLogo = idLogo;
        this.nombreEmpresa = nombreEmpresa;
    }
    protected Empresa(){}

    //Getters&Setters
    public int getIdLogo() {
        return idLogo;
    }

    public void setIdLogo(int idLogo) {
        this.idLogo = idLogo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}
