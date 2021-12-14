package com.example.juanjomz.proyecto1trimestre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import claseLocalizacion.Localizacion;
import clasesEmpresa.Empresa;
import clasesEmpresa.EmpresaNoTecnologica;
import clasesEmpresa.EmpresaTecnologica;
import detallesEmpresa.DetallesEmpresa;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<Empresa> listaEmpresas;
    private ListView lvEmpresas;
    IconAdapter<Empresa> myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llenarListaEmpresas();
        lvEmpresas=findViewById(R.id.lvEmpresas);
        AutoCompleteTextView autoComplete=findViewById(R.id.actvEmpresas);
        myAdapter=new IconAdapter<>(this,listaEmpresas);
        autoComplete.setAdapter(myAdapter);
        lvEmpresas.setAdapter(myAdapter);
        autoComplete.addTextChangedListener(new MyTextWatcher(myAdapter));
        lvEmpresas.setOnItemClickListener(this);
    }
    /**
     * Propósito:LLena la lista de empresas con objetos de tipo empresa
     *
     * */
    private void llenarListaEmpresas(){
        listaEmpresas=new LinkedList<>();
        listaEmpresas.add(new EmpresaTecnologica(R.drawable.deloitte_logo,"Deloitte","https://www2.deloitte.com/es/es.html",
                new Localizacion("C/ Gonzalo Jimenez de Quesada, 2, 41092 Sevilla",Uri.parse("geo:37.39164871013399, -6.010424402772642")),"adminfinanciera@deloitte.es","954 48 93 00"));
        listaEmpresas.add(new EmpresaNoTecnologica(R.drawable.azvi_logo,"Azvi","F-4212"));
    }
    /**
     * Propósito:LLeva a la actividad de detalles de la empresa si se clica en una empresa de tipo tecnológica
     *
     * */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Empresa empresa=(Empresa)myAdapter.getItem(i);
        Intent intent;
        if(empresa instanceof EmpresaTecnologica){
            intent=new Intent(getBaseContext(), DetallesEmpresa.class);
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra("Empresa",(EmpresaTecnologica)empresa);
            startActivity(intent);
        }
    }


    public class IconAdapter<T> extends BaseAdapter implements Filterable {
        Context context;
        List<Empresa> listaEmpreasAdaptador;
        List<Empresa> listaEmpreasOriginal;
        MyFilter myFilter=new MyFilter();

        public IconAdapter(@NonNull Context context, @NonNull List<Empresa> objects) {
            this.context = context;
            listaEmpreasAdaptador=new LinkedList<>();
            listaEmpreasOriginal=new LinkedList<>();
            listaEmpreasAdaptador.addAll(objects);
            listaEmpreasOriginal.addAll(objects);
        }
        @Override
        public int getCount() {
            return listaEmpreasAdaptador.size();
        }

        @Override
        public Object getItem(int i) {
            return listaEmpreasAdaptador.get(i);
        }

        @Override
        public long getItemId(int i) {
            return listaEmpreasAdaptador.get(i).hashCode();
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
        /**
         * Proposito:Devuelve la vista que será lo que se mostrará el dropdown del autocomplete y el listview
         * @param position int , View Convertview, ViewGroup parent
         * */
        @Override
        public View getView(int position, View convertView,ViewGroup parent){
            int itemViewType=getItemViewType(position);
            View row=convertView;
            ViewHolderEmpresaNoTecnologica viewHolderEmpresaNoTecnologica=null;
            ViewHolderEmpresaTecnologica viewHolderEmpresaTecnologica=null;
            TextView labName,labCnae,labUbicacion,labCorreo,labWeb;
            ImageView img;
            EmpresaNoTecnologica empresaNoTecnologica;
            EmpresaTecnologica empresaTecnologica;
            if (row==null){
                row=rowInflate(parent,itemViewType);
                if(itemViewType==1) {
                    labName=row.findViewById(R.id.tvNombreEmpresaTecnologica);
                    labCorreo=row.findViewById(R.id.tvCorreoEmpresaTecnologica);
                    labUbicacion=row.findViewById(R.id.tvUbicacionEmpresaTecnologica);
                    labWeb=row.findViewById(R.id.tvWebEmpresaTecnologica);
                    img=row.findViewById(R.id.ivImagenEmpresaTecnologica);
                    viewHolderEmpresaTecnologica=new ViewHolderEmpresaTecnologica(labName,labWeb,labUbicacion,labCorreo,img);
                    row.setTag(viewHolderEmpresaTecnologica);
                }else{
                    labName=row.findViewById(R.id.tvNombreEmpresaNoTecnologica);
                    img=row.findViewById(R.id.ivImagenEmpresaNoTecnologica);
                    labCnae=row.findViewById(R.id.tvivCnaeEmpresaNoTecnologica);
                    viewHolderEmpresaNoTecnologica=new ViewHolderEmpresaNoTecnologica(labName,img,labCnae);
                    row.setTag(viewHolderEmpresaNoTecnologica);
                }
            }
            else{
                if(itemViewType==1) {
                    viewHolderEmpresaTecnologica = (ViewHolderEmpresaTecnologica) row.getTag();
                }else{
                    viewHolderEmpresaNoTecnologica=(ViewHolderEmpresaNoTecnologica)row.getTag();
                }
            }
            if(itemViewType==1) {
                empresaTecnologica=(EmpresaTecnologica) listaEmpreasAdaptador.get(position);
                llenarViewHolderEmpresaTecnológica(viewHolderEmpresaTecnologica,empresaTecnologica);
            }else{
                empresaNoTecnologica=(EmpresaNoTecnologica) listaEmpreasAdaptador.get(position);
                llenarViewHolderEmpresaNoTecnológica(viewHolderEmpresaNoTecnologica,empresaNoTecnologica);
            }
            return  row;
        }

        /**
         * Propósito:Llena el viewholder de los elementos de tipo empresa tecnológica
         * @param  viewHolderEmpresaTecnologica ViewHolderEmpresaTecnologica,EmpresaTecnologica empresaTecnologica
         * */
        private void llenarViewHolderEmpresaTecnológica( ViewHolderEmpresaTecnologica viewHolderEmpresaTecnologica,EmpresaTecnologica empresaTecnologica){
            viewHolderEmpresaTecnologica.getLabName().setText(empresaTecnologica.getNombreEmpresa());
            viewHolderEmpresaTecnologica.getLabCorreo().setText(empresaTecnologica.getEmailDeContacto());
            viewHolderEmpresaTecnologica.getLabWeb().setText(empresaTecnologica.getUrlWebEmpresa());
            viewHolderEmpresaTecnologica.getLabUbicacion().setText(empresaTecnologica.getLocalizacion().getNombreLocalizacion());
            viewHolderEmpresaTecnologica.getImgV().setImageResource(empresaTecnologica.getIdLogo());

        }
        /**
         * Propósito:Llena el viewholder de los elementos de tipo empresa no tecnológica
         * @param  viewHolderEmpresaNoTecnologica ViewHolderEmpresaNoTecnologica,EmpresaNoTecnologica empresaNoTecnologica
         * */
        private void llenarViewHolderEmpresaNoTecnológica(ViewHolderEmpresaNoTecnologica viewHolderEmpresaNoTecnologica,EmpresaNoTecnologica empresaNoTecnologica){
            viewHolderEmpresaNoTecnologica.getLabName().setText(empresaNoTecnologica.getNombreEmpresa());
            viewHolderEmpresaNoTecnologica.getImgV().setImageResource(empresaNoTecnologica.getIdLogo());
            viewHolderEmpresaNoTecnologica.labCnae.setText(empresaNoTecnologica.getCodigoCNAE());


        }

        /**
         * Propósito:Realiza el inflado de los distintos layouts que tiene la app
         * @param parent ViewGroup ,int itemViewType
         * */
        private View rowInflate(ViewGroup parent,int itemViewType){
            View row;
            if(itemViewType==1) {
                row=getLayoutInflater().inflate(R.layout.lyt_empresa_tecnolgica, parent, false);
            }else{
                row=getLayoutInflater().inflate(R.layout.lyt_empresa_no_tecnologica, parent, false);
            }
            return row;
        }
        @Override
        public int getItemViewType(int position) {
            int viewType=0;
            if(getItem(position) instanceof EmpresaTecnologica){
             viewType=1;
            }
            return viewType;
        }

        private class MyFilter extends Filter{
            /**
             * Proposito:Método que se encarga de realizar el filtrado de la lista y el autocomplete en función del nombre de la empresa
             * @param charSequence CharSequence
             * */
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults resultado=new FilterResults();
                List<Empresa> sugerencia=new ArrayList<>();
                String patronDeFiltrado;
                if(charSequence == null || charSequence.length()<0){
                    sugerencia.addAll(listaEmpreasOriginal);
                }else{
                    patronDeFiltrado=charSequence.toString().toLowerCase().trim();
                    for(Empresa empresa:listaEmpreasOriginal){
                        if(empresa.getNombreEmpresa().toLowerCase().contains(patronDeFiltrado)){
                            sugerencia.add(empresa);
                        }
                    }
                }
                resultado.values=sugerencia;
                resultado.count=sugerencia.size();
                return resultado;
            }
            /**
             * Propósito:Asigna los resultados del filtrado a la lista que se va a mostrar
             *
             * */
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listaEmpreasAdaptador=(ArrayList<Empresa>) filterResults.values;
                notifyDataSetChanged();
            }

        }

        @Override
        public Filter getFilter() {
            return myFilter;
        }
    }
    class ViewHolderEmpresaTecnologica{
        TextView labName,labWeb,labUbicacion,labCorreo;
        ImageView imgV;

        public ViewHolderEmpresaTecnologica(TextView labName, TextView labWeb, TextView labUbicacion, TextView labCorreo, ImageView imgV) {
            this.labName = labName;
            this.labWeb = labWeb;
            this.labUbicacion = labUbicacion;
            this.labCorreo = labCorreo;
            this.imgV = imgV;
        }

        public TextView getLabName() {
            return labName;
        }

        public void setLabName(TextView labName) {
            this.labName = labName;
        }

        public TextView getLabWeb() {
            return labWeb;
        }

        public void setLabWeb(TextView labWeb) {
            this.labWeb = labWeb;
        }

        public TextView getLabUbicacion() {
            return labUbicacion;
        }

        public void setLabUbicacion(TextView labUbicacion) {
            this.labUbicacion = labUbicacion;
        }

        public TextView getLabCorreo() {
            return labCorreo;
        }

        public void setLabCorreo(TextView labCorreo) {
            this.labCorreo = labCorreo;
        }

        public ImageView getImgV() {
            return imgV;
        }

        public void setImgV(ImageView imgV) {
            this.imgV = imgV;
        }
    }
    class ViewHolderEmpresaNoTecnologica{
        TextView labName,labCnae;
        ImageView imgV;

        ViewHolderEmpresaNoTecnologica (TextView labName, ImageView imgV,TextView labCnae){
            this.labName = labName;
            this.imgV = imgV;
            this.labCnae = labCnae;

        }

        public TextView getLabName (){
            return this.labName;
        }

        public ImageView getImgV (){
            return this.imgV;
        }
        public TextView getLabCnae (){
            return this.labCnae;
        }

    }

    public class MyTextWatcher implements TextWatcher {
        private IconAdapter<Empresa> lAdapter;

        public MyTextWatcher(IconAdapter<Empresa> lAdapter) {
            this.lAdapter = lAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            lAdapter.getFilter().filter(s.toString().toLowerCase());
        }
    }

}