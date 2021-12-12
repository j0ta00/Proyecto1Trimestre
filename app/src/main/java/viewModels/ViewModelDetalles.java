package viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import clasesEmpresa.EmpresaTecnologica;

public class ViewModelDetalles extends ViewModel{
    private MutableLiveData<EmpresaTecnologica> empresa;
    public MutableLiveData<EmpresaTecnologica> getEmpresa(){
        if(empresa==null){
            empresa=new MutableLiveData<EmpresaTecnologica>();
        }
        return empresa;
    }

    public void setEmpresa(MutableLiveData<EmpresaTecnologica> empresa) {
        this.empresa = empresa;
    }
}
