package detallesEmpresa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
        vmDetalles.getEmpresa().observe(this,this::onCompanyChangued);
        webEmpresa.setOnClickListener(this);
        inicializarDatosEmpresa();
        btnPersonasDeContacto.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnUbicacion.setOnClickListener(this);
        correoEmpresa.setOnClickListener(this);
    }

    private void inicializarDatosEmpresa(){
        nombreEmpresa.setText(empresa.getNombreEmpresa());
        correoEmpresa.setText(empresa.getEmailDeContacto());
        webEmpresa.setText(empresa.getUrlWebEmpresa());
        logoEmpresa.setImageResource(empresa.getIdLogo());
    }

    private void onCompanyChangued(EmpresaTecnologica empresa){
        tvDireccionEmpresa.setText(empresa.getLocalizacion().getNombreLocalizacion());
        tvTelefonoDeContacto.setText(empresa.getTelefono());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnGuardar:{
                empresa.setTelefono(telefonoDeContacto.getText().toString());
                empresa.getLocalizacion().setNombreLocalizacion(direccionEmpresa.getText().toString());
                vmDetalles.getEmpresa().postValue(empresa);
            }break;
            case R.id.btnLocalizacion:{
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, empresa.getLocalizacion().getUriLocalizacion());
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }break;
            case R.id.btnPersonasDeContacto:{
                Intent intentActivityPersonasDeContactos = new Intent(getBaseContext(), PersonasDeContacto.class);
                intentActivityPersonasDeContactos.setAction(Intent.ACTION_SEND);
                startActivity(intentActivityPersonasDeContactos);
            }break;
            case R.id.tvEmailEmpresa:{
                Intent mailClient = new Intent(Intent.ACTION_SENDTO);
                mailClient.setData(Uri.parse("mailto:"));
                mailClient.putExtra(Intent.EXTRA_EMAIL, new String[]{empresa.getEmailDeContacto()});
                startActivity(mailClient);
            }break;
            case R.id.tvWebEmpresa:{
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(empresa.getUrlWebEmpresa()));
                startActivity(i);
            }break;
        }
    }
}