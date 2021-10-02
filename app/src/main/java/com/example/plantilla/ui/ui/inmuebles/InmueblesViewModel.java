package com.example.plantilla.ui.ui.inmuebles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class InmueblesViewModel extends ViewModel {

    private MutableLiveData<List<Inmueble>> listaInmuebleMutable;

    public LiveData<List<Inmueble>> getListaInmuebleMutable () {
        if ( listaInmuebleMutable == null ) {
            listaInmuebleMutable = new MutableLiveData<>();
        }
        return listaInmuebleMutable;
    }

    public void cargarInmuebles() {
        ApiClient api = ApiClient.getApi();
        List<Inmueble> inmuebleList = api.obtnerPropiedades();
        listaInmuebleMutable.setValue(inmuebleList);
    }

    public void cargarInmueblesAlquilados() {
        ApiClient api = ApiClient.getApi();
        List<Inmueble> inmuebleList = api.obtenerPropiedadesAlquiladas();
        listaInmuebleMutable.setValue(inmuebleList);
    }



}
