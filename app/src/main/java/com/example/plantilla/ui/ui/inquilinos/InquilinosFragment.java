package com.example.plantilla.ui.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Inquilino;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private InquilinosViewModel inquilinosViewModel;
    private List<Inmueble> listaInmueblesAlquilados;
    private Inquilino inquilinoActual;
    private View root;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inquilinosViewModel = new ViewModelProvider(this).get(InquilinosViewModel.class);
        root = inflater.inflate(R.layout.fragment_inquilinos, container, false);

        inquilinosViewModel.getListaInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                listaInmueblesAlquilados = inmuebles;
                generarView(inflater, root);
            }
        });
        inquilinosViewModel.getInquilinoMutable().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                inquilinoActual = inquilino;
            }
        });

        inquilinosViewModel.cargarInmueblesAlquilados();

        return root;
    }

    private void generarView(LayoutInflater layoutInflater, View root) {
        ArrayAdapter<Inmueble> arrayAdapter = new InquilinoAdapter(getContext(), R.layout.item_inquilino, listaInmueblesAlquilados, layoutInflater);
        final ListView listView = root.findViewById(R.id.lvInmueblesAlquilados);

        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Inmueble inmueble = listaInmueblesAlquilados.get(position);

                // obtenerInquilino iq

                // 1- llamada del método al ViewModel(inmueble)
                inquilinosViewModel.obtenerInquilino(inmueble);

                // 2- Mutable en el ViewModel

                // 3- El observer lo ponemos en onCreateView

                // 4- Setear resultado del observer en un ATRIBUTO (un inquilino)

                // 5- Utilizar referencia del ATRIBUTO

                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilinoActual", inquilinoActual);

                Navigation.findNavController(view).navigate(R.id.action_inquilinosFragment_to_detalleInquilinoFragment, bundle);

            }
        });

    }
}