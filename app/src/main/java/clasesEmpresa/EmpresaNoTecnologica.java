package clasesEmpresa;

import android.os.Parcelable;

public class EmpresaNoTecnologica extends Empresa{
    //Atributos
    private String codigoCNAE;

    //Constructores
    public EmpresaNoTecnologica(int idLogo, String nombreEmpresa, String codigoCNAE) {
        super(idLogo, nombreEmpresa);
        this.codigoCNAE = codigoCNAE;
    }

    //Getters&Setters
    public String getCodigoCNAE() {
        return codigoCNAE;
    }

    public void setCodigoCNAE(String codigoCNAE) {
        this.codigoCNAE = codigoCNAE;
    }
}
