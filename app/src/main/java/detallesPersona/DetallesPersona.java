package detallesPersona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juanjomz.proyecto1trimestre.R;

import clasePersona.Persona;

public class DetallesPersona extends AppCompatActivity {
    TextView txtNombre,txtApellidos,txtTelefono;
    ImageView img;
    Persona persona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_persona);
        txtNombre=findViewById(R.id.txtNombrePersona);
        txtApellidos=findViewById(R.id.txtApellidosPersona);
        txtTelefono=findViewById(R.id.txtTelefonoPersona);
        persona=getIntent().getExtras().getParcelable("Persona");
        img=findViewById(R.id.imgPersona);
        llenarDatosPersona();
    }
    private void llenarDatosPersona(){
        txtNombre.setText(persona.getNombre());
        txtApellidos.setText(persona.getApellidos());
        txtTelefono.setText(persona.getNumeroTelefono());
        img.setImageResource(persona.getIdFoto());
    }

}