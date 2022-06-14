package com.example.aula15

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyAppRepository {
    val listOfStrings = listOf<String>(
        "Frases de encorajamento",
        "", "Qualquer objetivo que você planta, a vida floresce com graça. \uD83D\uDCAE \uD83C\uDF3C \uD83C\uDF3A",
        "", "Quem tem luz própria jamais ficará na escuridão.",
        "",  "Você atingirá o sucesso quando apresentar com orgulho as cicatrizes que adquiriu ao longo da sua jornada.",
        "", "Quanto mais fortes forem suas provações, maiores serão suas vitórias. \uD83D\uDE09",
        "", "Os planos de Deus são justos e certeiros! Tenha fé e confiança! \uD83D\uDE4F",
        "", "Colecione memórias e acumule sorrisos. Todo o resto é passageiro. \uD83D\uDE0A",
        "", "Não espere pelo momento perfeito. Faça de cada momento a oportunidade perfeita.",
        "", "Vencedores não são pessoas que nunca falham, são pessoas que nunca desistem.\uD83E\uDDB8\u200D♂️",
        "", "É melhor rir da vida do que se lamentar por ela. \uD83D\uDE04",
        "", "Fim", "", "Acabou", "", "Bye, Bye"
    )

    // Funções do tipo suspende, são funções que são executadas em segundo plano tem um tipo expecífico que se chama suspende
    // o Flow, que será retornardo, é um fluxo de dados de algum certo tipo
    suspend fun getData(): Flow<String> { // flow é o fluxo de dados que a gente coloca para ser executado em segundo plano, (tem que ser o flow que tenha o coroutines no meio) ele vai ser flow (um fluxo) de que?, qual vai ser o tipo de dados, aqui no caso vai ser String
        //quando configura a função para retornar o flow,então tem que retornar o flow
        return flow {
            listOfStrings.forEach { string -> // intera a lista de strings
                emit(string) //Emite o elemento da lista
                delay(2000) // com o deley
            }

        }
    }
}

