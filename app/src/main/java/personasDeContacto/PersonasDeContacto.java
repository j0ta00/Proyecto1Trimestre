package personasDeContacto;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.juanjomz.proyecto1trimestre.R;
import java.util.LinkedList;
import java.util.List;
import clasePersona.Persona;

public class PersonasDeContacto extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private List<Persona> empleados;
    private Spinner spinner;
    TextView txtNombre,txtApellidos,txtTelefono;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas_de_contacto);
        llenarLista();
        spinner=findViewById(R.id.spnPersonasDeContacto);
        spinner.setAdapter(new spinnerAdapter(empleados));
        spinner.setOnItemSelectedListener(this);
        asignarId();
    }
    /**
     * Proposito:Asigna id a los distintos elementos visuales del programa
     * */
    private void asignarId(){
        txtNombre=findViewById(R.id.txtNombrePersona);
        txtApellidos=findViewById(R.id.txtApellidosPersona);
        txtTelefono=findViewById(R.id.txtTelefonoPersona);
        img=findViewById(R.id.imgPersona);
    }

    /**
     * Proposito:LLena una lista de personas con objetos persona
     * */
    private void llenarLista(){
        empleados=new LinkedList<>();
        empleados.add(new Persona(R.drawable.juanjo,"Juan José ","Muñoz Arenas", "6634243418"));
        empleados.add(new Persona(R.drawable.antonio,"Antonio ","Lopez Caballero", "6634243418"));
        empleados.add(new Persona(R.drawable.luis,"Luis ","Molinas Martínez", "6634243418"));
        empleados.add(new Persona(R.drawable.maria,"María ","Barroso Núñez", "6634243418"));

    }
    /**
     * Proposito:Una vez se clique en la persona seleccionada se setearan los textviews que mostrarán los datos de esta
     * @param adapterView AdapterView<?> , View view, int i, long l
     * */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Persona persona=empleados.get(i);
        txtNombre.setText(persona.getNombre());
        txtApellidos.setText(persona.getApellidos());
        txtTelefono.setText(persona.getNumeroTelefono());
        img.setImageResource(persona.getIdFoto());
    }
    /*Método que implementa la interfaz onItemSelected y que no uso*/
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    public class spinnerAdapter extends BaseAdapter {
        private List<Persona> empleadosAdapter;


        public spinnerAdapter(@NonNull List<Persona> empleadosAdapter) {
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
        /**
         * Proposito:Devuelve la vista que será lo que se mostrará en el spinner
         * @param i int , View Convertview, ViewGroup parent
         * */
        @Override
        public View getView(int i, View Convertview, ViewGroup parent) {
            TextView txtNombre,txtApellidos;
            ViewHolder holder;
            View vista=Convertview;
            LayoutInflater inflater;
            if(vista==null){
                inflater = getLayoutInflater();
                vista=inflater.inflate(R.layout.layout_spinner,parent,false);
                txtNombre=vista.findViewById(R.id.nombrePersona);
                txtApellidos=vista.findViewById(R.id.apellidosPersona);
                holder=new ViewHolder(txtNombre,txtApellidos);
                vista.setTag(holder);
            }else{
                holder=(ViewHolder) vista.getTag();
            }
            holder.getTxtNombre().setText(empleadosAdapter.get(i).getNombre());
            holder.getTxtApellidos().setText(empleadosAdapter.get(i).getApellidos());
            return vista;
        }


    }
    public class ViewHolder{
        TextView txtNombre,txtApellidos;

        public ViewHolder(TextView txtNombre, TextView txtApellidos) {
            this.txtNombre = txtNombre;
            this.txtApellidos = txtApellidos;
        }
        public TextView getTxtNombre() {
            return txtNombre;
        }
        public TextView getTxtApellidos() {
            return txtApellidos;
        }
    }
}