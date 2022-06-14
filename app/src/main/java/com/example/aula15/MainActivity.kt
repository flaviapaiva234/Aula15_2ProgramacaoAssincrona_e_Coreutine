package com.example.aula15

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.aula15.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var number: Int = 0
    private var isLoopStarted: Boolean = false

    private lateinit var myJob: Job // essa função está lá em baixo no private fun initLoop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickEvents()
    }

    private fun clickEvents() {
        binding.apply {
            btnStart.setOnClickListener {
                //troquei o isLoopStarted true e false pelo initLoop() e pelo myJob.cancel()
                //            isLoopStarted = true
                initLoop()
            }
            btnEnd.setOnClickListener {
                //            isLoopStarted = false
                myJob.cancel() // vai fazer o botal para a execução

                //
            }
        }
    }

    //com o coroutines da para usar mais de uma função ao mesmo tempo
    private fun initLoop() {
        //CoroutineScope é uma fução que recebe um contexto, e esse contexto é que vai determinar se vai utiliar a fila thread ou a fila da while
        // Ocorre paralelamente à thead de UI responsável por eventos de interface, é uma forma de aliviar e não travar nada
        //tem que escolher o Disatchers(kotlin.coroutines).IO
        // é um contesto de entrada e saida de dados, que é algo que acontece sempre em segundo plano,

        //pode também manipular diretamente essa coroutine
        // quando digita CoroutineScope(Dispatchers.IO).launch { ele retorna um objeto do tipo job, esse job representa essa fila em segundo plano onde está acontecendo eventos
        // e quando tem esse objeto em mão, da para se manipular essa coroutine
        // dá para controlar o cancelamento de processos em segundo plano
        myJob = CoroutineScope(Dispatchers.IO).launch {
            while (isLoopStarted) { //loop ocorrendo em segundo plano
                //por padrão, qualquer evento que esteja ocorrendo na interface, tem que acontecer na thread de while
                // pode colocar uma tarefa pesada em segundo plano(tipo uma while ou uma contagem, nem imprimir um texto da tela
                // o certo é pegar o resultado que esta acontecendo em segundo plano e levar para a fila em segundo plano
                // para fazer isso, vou usar o *withcontext()e passar o contexto da while thread
                number++
                //consigo ter controle da quantidade de tempe em que o programa vai implementando
                // A função delay pausa momentaneamente o processo por um determinado tempo
                // O tempo é sempre em milisegundos
                delay(1000) // colocando o delay, se tem o controle sobre o processo inteiro
                // Resultado sendo levado para a thread de UI (principal)
                //Main de principal, e a impressão tem que ficar dentro do withContext
                withContext(Dispatchers.Main) {
                    binding.textView.text = "$number"
                    Log.i(ContentValues.TAG, "Contagem: " + number)
                }

            }
        } //essa fila tem que ser inicializada dando o lauch e coloco esse while dentro desse lauch
        // imput/Output
        //      myJob = CoroutineScope(Dispatchers.IO).launch {

    }
}

// sempre que estiver fazendo um aplicativo com um while ou tipo uma capitura de banco de dados ou de um servidor, tudo isso tem que acontecer em threads de segundo plano,
// pq se não, o aplicativo vai ficar congelando
//da para ter controle total do que está em segundo plano
