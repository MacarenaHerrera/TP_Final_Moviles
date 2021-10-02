package com.example.plantilla.ui.ui.inquilinos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Inquilino;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class InquilinosViewModel extends ViewModel {

    private MutableLiveData<List<Inmueble>> listaInmuebleMutable;
    private MutableLiveData<Inquilino> inquilinoMutable;

    public LiveData<List<Inmueble>> getListaInmuebleMutable () {
        if ( listaInmuebleMutable == null ) {
            listaInmuebleMutable = new MutableLiveData<>();
        }
        return listaInmuebleMutable;
    }
    public LiveData<Inquilino> getInquilinoMutable () {
        if ( inquilinoMutable == null ) {
            inquilinoMutable = new MutableLiveData<>();
        }
        return inquilinoMutable;
    }


    public void cargarInmueblesAlquilados() {
        ApiClient api = ApiClient.getApi();
        List<Inmueble> inmuebleList = api.obtenerPropiedadesAlquiladas();
        listaInmuebleMutable.setValue(inmuebleList);
    }

    public void obtenerInquilino(Inmueble inmueble) {
        ApiClient api = ApiClient.getApi();
        Inquilino inquilino = api.obtenerInquilino(inmueble);
        inquilinoMutable.setValue(inquilino);
    }

}
