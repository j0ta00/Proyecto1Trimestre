package detallesEmpresa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juanjomz.proyecto1trimestre.R;

import clasesEmpresa.EmpresaTecnologica;
import personasDeContacto.PersonasDeContacto;
import viewModels.ViewModelDetalles;

public class DetallesEmpresa extends AppCompatActivity implements AdapterView.OnClickListener{
    EmpresaTecnologica empresa;
    ViewModelDetalles vmDetalles;
    ImageView logoEmpresa;
    TextView nombreEmpresa,correoEmpresa,webEmpresa, tvDireccionEmpresa, tvTelefonoDeContacto;
    EditText direccionEmpresa,telefonoDeContacto;
    Button btnPersonasDeContacto,btnGuardar;
    ImageButton btnUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        empresa=getIntent().getExtras().getParcelable("Empresa");
        vmDetalles= ViewModelProviders.of(this).get(ViewModelDetalles.class);
        asignarId();
        vmDetalles.getEmpresa().observe(this,this::onCompanyChangued);
        inicializarDatosEmpresa();
        asignarEscuchadores();
    }
    /**
     * Proposito:Asigna listeners o escuchadores a los distintos elementos clicables del programa
     * */
    private void asignarEscuchadores(){
        webEmpresa.setOnClickListener(this);
        btnPersonasDeContacto.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnUbicacion.setOnClickListener(this);
        correoEmpresa.setOnClickListener(this);
    }
    /**
     * Proposito:Asigna id a los distintos elementos visuales del programa
     * */
    private void asignarId(){
        nombreEmpresa=findViewById(R.id.tvNombreEmpresa);
        correoEmpresa=findViewById(R.id.tvEmailEmpresa);
        webEmpresa=findViewById(R.id.tvWebEmpresa);
        direccionEmpresa=findViewById(R.id.edLocalizacionEmpresa);
        telefonoDeContacto=findViewById(R.id.edTelefonoEmpresa);
        btnPersonasDeContacto=findViewById(R.id.btnPersonasDeContacto);
        btnGuardar=findViewById(R.id.btnGuardar);
        btnUbicacion=findViewById(R.id.btnLocalizacion);
        logoEmpresa=findViewById(R.id.imgLogoEmpresa);
        tvDireccionEmpresa =findViewById(R.id.tvLocalizacionEmpresa);
        tvTelefonoDeContacto =findViewById(R.id.tvTelefonoEmpresa);
    }
    /**
     * Propósito:Inicializa los datos del objeto empresa cuyo datos se estarán mostrando
     * */
    private void inicializarDatosEmpresa(){
        nombreEmpresa.setText(empresa.getNombreEmpresa());
        correoEmpresa.setText(empresa.getEmailDeContacto());
        webEmpresa.setText(empresa.getUrlWebEmpresa());
        logoEmpresa.setImageResource(empresa.getIdLogo());
        direccionEmpresa.setText(empresa.getLocalizacion().getNombreLocalizacion());
        telefonoDeContacto.setText(empresa.getTelefono());
    }
    /**
     * Propósito:Método que llamamos en el observador, se encarga de cambiar los datos de los textViews
     * @param empresa EmpresaTecnologica
     * */
    private void onCompanyChangued(EmpresaTecnologica empresa){
        tvDireccionEmpresa.setText(empresa.getLocalizacion().getNombreLocalizacion());
        tvTelefonoDeContacto.setText(empresa.getTelefono());
    }
    /**
     * Propósito:Determinar que botón estamos pulsando y que acción se realizará
     * @param view View
     * */
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnGuardar:{
                guardarDatosModificados();
            }break;
            case R.id.btnLocalizacion:{
               realizarIntentMapa();
            }break;
            case R.id.btnPersonasDeContacto:{
                realizarIntentPersonasDeContaco();
            }break;
            case R.id.tvEmailEmpresa:{
                realizarIntentEmail();
            }break;
            case R.id.tvWebEmpresa:{
                realizarIntentWeb();
            }break;
        }
    }
    /**
     * Propósito:Realiza el intent que abre la app de google map
     *
     * */
    private void realizarIntentMapa(){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, empresa.getLocalizacion().getUriLocalizacion());
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
    /**
     * Propósito:Realiza el intent que abre la actividad de personas de contacto
     *
     * */
    private void realizarIntentPersonasDeContaco(){
        Intent intentActivityPersonasDeContactos = new Intent(getBaseContext(), PersonasDeContacto.class);
        intentActivityPersonasDeContactos.setAction(Intent.ACTION_SEND);
        startActivity(intentActivityPersonasDeContactos);
    }
    /**
     * Propósito:Realiza el intent que abre la app email
     *
     * */
    private void realizarIntentEmail(){
        Intent mailClient = new Intent(Intent.ACTION_SENDTO);
        mailClient.setData(Uri.parse("mailto:"));
        mailClient.putExtra(Intent.EXTRA_EMAIL, new String[]{empresa.getEmailDeContacto()});
        startActivity(mailClient);
    }
    /**
     * Propósito:Realiza el intent que abre la pagina web de nuestra empresa
     *
     * */
    private void realizarIntentWeb(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(empresa.getUrlWebEmpresa()));
        startActivity(i);
    }
    /**
     * Propósito:LLama al método onCompanyChangued y modifica los datos del objeto empresa
     *
     * */
    private void guardarDatosModificados(){
        empresa.setTelefono(telefonoDeContacto.getText().toString());
        empresa.getLocalizacion().setNombreLocalizacion(direccionEmpresa.getText().toString());
        vmDetalles.getEmpresa().postValue(empresa);
    }
}