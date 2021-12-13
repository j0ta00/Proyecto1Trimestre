package personasDeContacto;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.juanjomz.proyecto1trimestre.R;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import clasePersona.Persona;

public class PersonasDeContacto extends AppCompatActivity {

    private List<Persona> empleados;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas_de_contacto);
        llenarLista();
        spinner=findViewById(R.id.spnPersonasDeContacto);
        spinner.setAdapter(new spinnerAdapter(this,empleados));
    }


    private void llenarLista(){
        empleados=new LinkedList<Persona>();
        empleados.add(new Persona(R.drawable.juanjo,"Juan José ","Muñoz Arenas", "6634243418"));
        empleados.add(new Persona(R.drawable.antonio,"Antonio ","Lopez Caballero", "6634243418"));
        empleados.add(new Persona(R.drawable.luis,"Luis ","Molinas Martínez", "6634243418"));
        empleados.add(new Persona(R.drawable.maria,"María ","Barroso Núñez", "6634243418"));

    }
    public class spinnerAdapter extends BaseAdapter {
        private List<Persona> empleadosAdapter;
        Context context;

        public spinnerAdapter(@NonNull Context context, @NonNull List<Persona> empleadosAdapter) {
            this.empleadosAdapter = empleadosAdapter;

        }

        @Override
        public int getCount() {
            return empleadosAdapter.size();
        }

        @Override
        public Object getItem(int i) {
            return empleadosAdapter.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View Convertview, ViewGroup parent) {
            ImageView img=null;
            TextView txtNombre=null,txtApellidos=null;
            ViewHolder holder=null;
            View vista=Convertview;
            LayoutInflater inflater =null;
            if(vista==null){
                inflater = getLayoutInflater();
                vista=inflater.inflate(R.layout.layout_spinner,parent,false);
                img=vista.findViewById(R.id.imgPersona);
                txtNombre=vista.findViewById(R.id.nombrePersona);
                txtApellidos=vista.findViewById(R.id.apellidosPersona);
                holder=new ViewHolder(img,txtNombre,txtApellidos);
                vista.setTag(holder);
            }else{
                holder=(ViewHolder) vista.getTag();
            }
            holder.getImgPersona().setImageResource(empleadosAdapter.get(i).getIdFoto());
            holder.getTxtNombre().setText(empleadosAdapter.get(i).getNombre());
            holder.getTxtApellidos().setText(empleadosAdapter.get(i).getApellidos());
            return vista;
        }


    }
    public class ViewHolder{
        ImageView imgPersona;
        TextView txtNombre,txtApellidos;

        public ViewHolder(ImageView imgPersona, TextView txtNombre, TextView txtApellidos) {
            this.imgPersona = imgPersona;
            this.txtNombre = txtNombre;
            this.txtApellidos = txtApellidos;
        }

        public ImageView getImgPersona() {
            return imgPersona;
        }

        public void setImgPersona(ImageView imgPersona) {
            this.imgPersona = imgPersona;
        }

        public TextView getTxtNombre() {
            return txtNombre;
        }

        public void setTxtNombre(TextView txtNombre) {
            this.txtNombre = txtNombre;
        }

        public TextView getTxtApellidos() {
            return txtApellidos;
        }

        public void setTxtApellidos(TextView txtApellidos) {
            this.txtApellidos = txtApellidos;
        }
    }
}