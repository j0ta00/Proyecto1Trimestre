package clasesEmpresa;

import claseLocalizacion.Localizacion;

public class EmpresaTecnologica extends Empresa{
    //Atributos
    private String urlWebEmpresa;
    private Localizacion localizacion;
    private String emailDeContacto;

    //Constructores
    public EmpresaTecnologica(int idLogo, String nombreEmpresa, String urlWebEmpresa, Localizacion localizacion, String emailDeContacto) {
        super(idLogo, nombreEmpresa);
        this.urlWebEmpresa = urlWebEmpresa;
        this.localizacion = localizacion;
        this.emailDeContacto = emailDeContacto;
    }

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
}
