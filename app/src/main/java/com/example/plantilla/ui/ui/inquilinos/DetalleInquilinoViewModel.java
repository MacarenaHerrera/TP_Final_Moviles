package com.example.plantilla.ui.ui.inquilinos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inquilino;

public class DetalleInquilinoViewModel extends ViewModel {
    private MutableLiveData<Inquilino> detalleInquilinoMutable;

    public LiveData<Inquilino> getDetalleInquilinoMutable() {
        if (detalleInquilinoMutable == null) {
            detalleInquilinoMutable = new MutableLiveData<>();
        }
        return detalleInquilinoMutable;
    }

    public void cargarDetalleInquilino(Inquilino inquilino) {
        detalleInquilinoMutable.setValue(inquilino);
    }
}
