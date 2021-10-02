package com.example.plantilla.ui.ui.inmuebles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.plantilla.R;
import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Propietario;

import java.io.Serializable;
import java.util.List;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel detalleInmuebleViewModel;
    private View root;
    private EditText etCodigoInmueble, etAmbientesInmueble, etDireccionInmueble, etPrecioInmueble, etUsoInmueble, etTipoInmueble;
    private CheckBox cbDisponibleInmueble;
    private ImageView ivFotoInmueble;
    private Inmueble inmueble;
    private Propietario usuario;


    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (savedInstanceState != null) {
            inmueble = (Inmueble)savedInstanceState.getSerializable("inmueble");
            Log.d("msj", "INMUEBLE " + inmueble.getPrecio() );
        } else {
            inmueble = (Inmueble) getArguments().getSerializable("inmueble");
        }

        detalleInmuebleViewModel = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        root = inflater.inflate(R.layout.fragment_detalle_inmueble, container, false);

        inicializarComponentes(root);

        detalleInmuebleViewModel.getDetalleInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                etCodigoInmueble.setText(inmueble.getIdInmueble()+"");
                etAmbientesInmueble.setText(inmueble.getAmbientes()+"");
                etDireccionInmueble.setText(inmueble.getDireccion());
                etPrecioInmueble.setText(inmueble.getPrecio()+"");
                etUsoInmueble.setText(inmueble.getUso());
                etTipoInmueble.setText(inmueble.getTipo());

                cbDisponibleInmueble.setChecked(inmueble.isEstado());

                Glide.with(getContext())
                        .load(inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivFotoInmueble);
            }
        });

        detalleInmuebleViewModel.cargarDetalleInmueble(inmueble);

        return root;
    }

    private void inicializarComponentes(View root) {
        etCodigoInmueble = root.findViewById(R.id.etCodigoInmueble);
        etAmbientesInmueble = root.findViewById(R.id.etAmbientesInmueble);
        etDireccionInmueble = root.findViewById(R.id.etDireccionInmueble);
        etPrecioInmueble = root.findViewById(R.id.etPrecioInmueble);
        etUsoInmueble = root.findViewById(R.id.etUsoInmueble);
        etTipoInmueble = root.findViewById(R.id.etTipoInmueble);
        cbDisponibleInmueble = root.findViewById(R.id.cbDisponibleInmueble);
        ivFotoInmueble = root.findViewById(R.id.ivFotoInmueble);



        cbDisponibleInmueble.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inmueble.setEstado(cbDisponibleInmueble.isChecked());
                    detalleInmuebleViewModel.actualizarDetalleInmueble(inmueble);
                    Toast.makeText(getContext(), "Inmueble actualizado con exito.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("inmueble", inmueble);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("msj", "onDestroyView ");
    }
}