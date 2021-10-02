package com.example.plantilla.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.request.ApiClient;

public class MainActivityViewModel extends ViewModel {
    MutableLiveData<String> mensajeMutable;
    MutableLiveData<Boolean> loginMutable;
    public LiveData<String> getMensajeMutable(){
        if(mensajeMutable==null){
            mensajeMutable= new MutableLiveData<>();
        }
        return mensajeMutable;
    }
    public LiveData<Boolean> getLoginMutable(){
        if(loginMutable==null){
            loginMutable= new MutableLiveData<>();
        }
        return loginMutable;
    }
    public void verificarDatos(String usuario, String contrasenia){
        if(usuario !=null && contrasenia!=null && usuario.length()>0 && contrasenia.length()>0){
            ApiClient api=ApiClient.getApi();
            if (api.login(usuario, contrasenia)!=null){
                mensajeMutable.setValue("¡Bienvenido/a " + usuario + " !");
                loginMutable.setValue(true);
            }else{
                mensajeMutable.setValue("Usuario o contraseña incorrecto");
            }
        }else{
            mensajeMutable.setValue("Los campos no pueden estar vacíos");
        }

    }

}
