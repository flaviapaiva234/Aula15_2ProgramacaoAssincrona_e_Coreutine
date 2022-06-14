package com.example.aula15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// temque chamar uma coroutine, o kotlin disponibiliza um tipo de coroutine específico para a view model
//essa coroutine vai ser iniciada assim que a view model for iniciada,
// e assim que essa view model for parada ou cancelada, esse coroutine vai parar junto
//por exemplo o usuario sai dessa tela e a view model vai embora, então o coroutine vai embora junto.

class MyViewModel: ViewModel() {

    // 2. chamar o repositório no caso a activity *MyAppRepository*
    private var repository = MyAppRepository()


    //para diferenciar o LiveData do MutableLiveData por padrão se usa o underline ficando: string para LiveData e a MutableLiveData _string para
    private var _string = MutableLiveData<String>()
    //val string, agora vai ser o valor que vai ser observado
    val string: LiveData<String> = _string



    //1. criar uma função *getDataFromRepository(){*
    fun getDataFromRepository(){
        //aí chama a coroutine *viewModelScope.launch(Dispatchers.IO){*, como eu quero em segundo plano, coloco o *.launch(Dispatchers.IO){*
        //Coroutine atrelada ai ciclo de vida da view model
        //abre um processo em segundo plano
        viewModelScope.launch(Dispatchers.IO){
           //.para coletar aquiles dados que estão vindo daquele fluxo tem que pegar a função *repository.getDAta()
           // o .collect coleta os dados que são transmitido atravez daquele emiti, aquele emiti entrou em ação, emitiu um dado que vai ser capturado nesse collect
            // aqui é o mesmo esquema das coroutines normais
            //Coleta os dados emitidos em segundo plano, a intenção é no deixar travar a thread de UI
            repository.getData().collect {

                //coloca o valor no Live data
                _string.postValue(it)
            }
            }
        }
    }

