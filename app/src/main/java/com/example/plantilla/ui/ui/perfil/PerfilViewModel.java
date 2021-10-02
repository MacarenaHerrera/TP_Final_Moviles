package com.example.plantilla.ui.ui.perfil;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

public class PerfilViewModel extends ViewModel
{
    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> botonEditarMutable;
    private MutableLiveData<String> botonGuardarMutable;

    public LiveData<Propietario> getPropietarioMutable () {
        if ( propietarioMutable == null ) {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }
    public LiveData<String> getBotonEditarMutable () {
        if ( botonEditarMutable == null ) {
            botonEditarMutable = new MutableLiveData<>();
        }
        return botonEditarMutable;
    }
    public LiveData<String> getBotonGuardarMutable () {
        if ( botonGuardarMutable == null ) {
            botonGuardarMutable = new MutableLiveData<>();
        }
        return botonGuardarMutable;
    }


    public void cargarPerfil() {
        ApiClient api = ApiClient.getApi();
        Propietario propietario = api.obtenerUsuarioActual();
        propietarioMutable.setValue(propietario);
    }

    public void guardarPerfil(Propietario p) {
        ApiClient ap = ApiClient.getApi();
        ap.actualizarPerfil(p);
        propietarioMutable.setValue(p);
    }

    public void HabDes(String textoBoton, Propietario p) {
        if ( textoBoton == "Editar" ) {
            botonEditarMutable.setValue(textoBoton);
        } else if (textoBoton == "Guardar") {
            guardarPerfil(p);
            botonGuardarMutable.setValue(textoBoton);
        }
    }

}