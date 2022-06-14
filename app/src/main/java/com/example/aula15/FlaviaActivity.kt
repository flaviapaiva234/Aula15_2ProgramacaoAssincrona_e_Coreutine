package com.example.aula15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aula15.adapter.FlaAdapter
import com.example.aula15.databinding.ActivityFlaviaBinding

class FlaviaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlaviaBinding
    private lateinit var flavinhaAdapter: FlaAdapter
    private lateinit var viewModel123: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlaviaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //aqui estou chamando esse método ViewModelProvider
        //.get vai pegar a classe do view model e usa a função para fazer com que a view model sempre acompanhe o momento em que a minha activit nasce e morre para que ele termine junto,
        // por isso usamos esse método para instanciar a class lá na activity MyViewModel (linha 25) já tem o *LiveData<String> = _string* que é a _string que está vindo lá do flow
        // tipo assim: tem um fluxo de dados que está sendo emitido string por string em segundo plano, está sendo coletado lá na activity MyViewModel (linha 38), está colocando a _string no o LiveData,
        // e nessa activity FlaviaActivity, como eu já tenho essa ViewModel Instanciado, eu vou pegar esse LiveData e Observar o valor dela
        viewModel123 = ViewModelProvider(this).get(MyViewModel::class.java)
        flavinhaAdapter = FlaAdapter()

        setUpAdapterAndRecyclerFlaviaView()

        // como está usando a função MVVM tem que ter alguma função para observar a minha tela
        onObserve456()

    }

    private fun setUpAdapterAndRecyclerFlaviaView(){
        binding.apply {
            recyclerViewFlaviaActivity.apply {
                adapter = flavinhaAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun onObserve456(){
        viewModel123.getDataFromRepository() // fez a chamada da função lá na activity MyViewmodel (linha 30) que eu faço a captura de dados

        //Observa o valor e atualiza no adapter
        // fica observando em tempo real, qualquer mudança ou qualquer dado que está vindo de lá, se veio uma string, como ele está observan, ele já pega para mim.
        viewModel123.string.observe(this, Observer {

          // tem que passar a referencia da activity (this) e passar a interface (observer) o Observer já até reconhece que é uma string, então lá do segundo plano está vindo uma string, a string é coletada pela ViewModel colocada como valor do LiveData e a activity está observando, quando ela ve que veio um dado novo, ela faz uma ação para esse dado.
            flavinhaAdapter.addNewString(it)// veio um dado do segundo plano, ele observou que veio e adcionou aqui, e vai adcionar um dado a mais nessa lista já exixtente, vai ser notificado e vai fazer a ação de impprimir o dado para mim.
        } )


    }


}