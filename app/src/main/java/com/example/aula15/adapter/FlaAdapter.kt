package com.example.aula15.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aula15.databinding.ActivityItensStringBinding

class FlaAdapter: RecyclerView.Adapter<FlaAdapter.FlaViewHolder>() {

    private var listOfStrings = ArrayList<String>()

    class FlaViewHolder(val binding: ActivityItensStringBinding):
        RecyclerView.ViewHolder(binding.root){

        fun  onBind(item: String){
            binding.tvItensString.text = item
        }
    }
    // construuiu o Adapter, a lista vai vir elementos de String que vai ser adicionados uma string de cada vez nessa lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlaViewHolder {
        return FlaViewHolder(
            ActivityItensStringBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FlaViewHolder, position: Int) {
        holder.onBind(listOfStrings[position])
    }

    override fun getItemCount(): Int {
        return listOfStrings.size
    }

    //criar uma função, new String
    fun addNewString(string: String){
        //eu consigo inserir valores em uma posição específica na minha lista
        //inicialmente essa lista está vazia, então o tamanho ou posição é zero, posso usar esse tamanho como o um mecanismo para definir a posição que eu quero
        //essa lista pode receber  posição e o elemento
        // como posição vou colocar o tamanho atual dela (size) e o elemento (string), como o elemento string está vazio, então o tamanho dela é zero, // quando entra um elento o tamanho de aumentou para um, então o próximo elemento que vier vai ser inserido na posição um, que é a posiçaõ atual dela, aí o elemento é inserido aumentando mais um, passando a ser dois, e assim sucessivamente
        listOfStrings.add(listOfStrings.size, string) // esse add pode receber a posição e o elemento,
        notifyDataSetChanged()  // tem que lembrar de clocar o * notifyDataSetChanged()* notificar o adapter de que os dados estão sendo mudado
    }


}