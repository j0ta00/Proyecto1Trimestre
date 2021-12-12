package personasDeContacto;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

import com.example.juanjomz.proyecto1trimestre.R;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import clasePersona.Persona;

public class PersonasDeContacto extends AppCompatActivity {

    private List<Persona> empleados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas_de_contacto);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void llenarLista(){
        empleados=new LinkedList<Persona>();
        empleados.add(new Persona("Juan José","Muñoz Arenas",
        LocalDateTime.of(2000,6,7,00,00),"6634243418"));
        empleados.add(new Persona("Antoine","Lebrija Sevilla",
                LocalDateTime.of(2000,6,7,00,00),"6634243418"));
        empleados.add(new Persona("Luis","Molinas Martínez",
                LocalDateTime.of(2000,6,7,00,00),"6634243418"));
        empleados.add(new Persona("María","",
                LocalDateTime.of(2000,6,7,00,00),"6634243418"));

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
            TextView txt=null;
            ViewHolder holder=null;
            View vista=Convertview;
            LayoutInflater inflater =null;
            if(vista==null){
                inflater = getLayoutInflater();
                vista=inflater.inflate(R.layout.spinner_row,parent,false);
                img=vista.findViewById(R.id.fotoEquipo);
                txt=vista.findViewById(R.id.nombreEquipo);
                holder=new Holder(img,txt);
                vista.setTag(holder);
            }else{
                holder=(ViewHolder) vista.getTag();
            }
            holder.getImgFotoEquipo().setImageResource(empleadosAdapter.get(i).getIdFoto());
            holder.getTxtNombreEquipo().setText(empleadosAdapter.get(i).getNombre());
            return vista;
        }


    }
    public class ViewHolder{
        ImageView imgFotoEquipo;
        TextView txtNombreEquipo;

        public Holder(ImageView imgFotoEquipo, TextView txtNombreEquipo) {
            this.imgFotoEquipo = imgFotoEquipo;
            this.txtNombreEquipo = txtNombreEquipo;
        }

        public ImageView getImgFotoEquipo() {
            return imgFotoEquipo;
        }

        public void setImgFotoEquipo(ImageView imgFotoEquipo) {
            this.imgFotoEquipo = imgFotoEquipo;
        }

        public TextView getTxtNombreEquipo() {
            return txtNombreEquipo;
        }

        public void setTxtNombreEquipo(TextView txtNombreEquipo) {
            this.txtNombreEquipo = txtNombreEquipo;
        }
    }
}