package viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import clasesEmpresa.EmpresaTecnologica;

public class ViewModelDetalles extends ViewModel{
    private MutableLiveData<EmpresaTecnologica> empresa;
    /**
     * Es el get de la empresa de tipo mutable que tendremos en nuestro viewmodel
     * */
    public MutableLiveData<EmpresaTecnologica> getEmpresa(){
        if(empresa==null){
            empresa=new MutableLiveData<>();
        }
        return empresa;
    }

    public void setEmpresa(MutableLiveData<EmpresaTecnologica> empresa) {
        this.empresa = empresa;
    }
}
