package com.example.plantilla.ui.ui.inmuebles;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

public class DetalleInmuebleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> detalleInmuebleMutable;

    public LiveData<Inmueble> getDetalleInmuebleMutable() {
        if ( detalleInmuebleMutable == null ) {
            detalleInmuebleMutable = new MutableLiveData<>();
        }
        return detalleInmuebleMutable;
    }

    public void cargarDetalleInmueble(Inmueble inmueble) {
        detalleInmuebleMutable.setValue(inmueble);
    }
    public void actualizarDetalleInmueble(Inmueble inmueble) {
        ApiClient apiClient = ApiClient.getApi();
        apiClient.actualizarInmueble(inmueble);
        detalleInmuebleMutable.setValue(inmueble);
    }
}
