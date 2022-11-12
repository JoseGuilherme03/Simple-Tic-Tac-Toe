package tictactoe

fun main() {
    rodarJogo()
}

fun rodarJogo() {
//    1. Imprime uma grade vazia no início do jogo.

//    2. Cria um loop de jogo onde o programa pede ao usuário para inserir as coordenadas da célula,
//    analisa o movimento para correção e mostra uma grade com as alterações se tudo estiver bem.

//    3. Termina o jogo quando alguém ganha ou há um empate.
    //Variavel para imprimir o tabuleiro vazio
    var entradaPadrao = "_________"

    // Variavel para localizar onde deve ser preenchido a jogada
    val cordenadas = mutableListOf(
        mutableListOf(entradaPadrao[0], entradaPadrao[1], entradaPadrao[2]),
        mutableListOf(entradaPadrao[3], entradaPadrao[4], entradaPadrao[5]),
        mutableListOf(entradaPadrao[6], entradaPadrao[7], entradaPadrao[8])
    )
    var cont = 0

    interfaceJogoDaVelha(cordenadas)

    while (true) {
        if (verificaResultado(entradaPadrao) == "Draw" || verificaResultado(entradaPadrao) == "X wins" || verificaResultado(entradaPadrao) == "O wins") break
        if (cont % 2 == 0) interageComUsuario(cordenadas,'X') else interageComUsuario(cordenadas,'O')
        entradaPadrao = ""
        for (lista in cordenadas) {
            for (item in lista) {
                entradaPadrao += item
            }
        }
        cont ++
    }
    println(verificaResultado(entradaPadrao))
}


fun interageComUsuario(cordenadas: MutableList<MutableList<Char>>, jogador: Char) {
    // 1. Recebe a jogada do ususário e verifica se é um número

    // 2. Verifica se as cordenadas passada pelo mesmo é válida e se não está ocupado por outra célula

    // 3. Se tudo ocorrer sem erros, imprimi a tabela


    var teste = false
    while (!teste) {
        val entradaUsuario = readln()
        val coluna = entradaUsuario[0].digitToIntOrNull()
        val linha = entradaUsuario[2].digitToIntOrNull()


        if (coluna == null || linha == null) {
            teste = false
            println("You should enter numbers!")
        } else if (linha !in 1..3 || coluna !in 1..3) {
            teste = false
            println("Coordinates should be from 1 to 3!")
        } else if ('X' == cordenadas[coluna - 1][linha - 1] || 'O' == cordenadas[coluna - 1][linha - 1]) {
            teste = false
            println("This cell is occupied! Choose another one!")
        } else {
            teste = true
            cordenadas[coluna - 1][linha - 1] = jogador
            interfaceJogoDaVelha(cordenadas)
        }
    }
}


fun verificaResultado(entradaPadrao: String): String {
    // 1. Cria 3 sublistas quem verifica todas as possiveis vitorias dos dois jogadores

    // 2. Verifica se as jogadas são validas e se um jogador não jogou mais que o outro

    // 3. Verifica o resultado da partida -> vitória, empate, derrota

    val lista = listOf(
        0, 4, 8, 2, 4, 6, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 3, 6, 1, 4, 7, 2, 5, 8
    ).chunked(3)

    val countX = entradaPadrao.count { it == 'X' }
    val countO = entradaPadrao.count { it == 'O' }
    val countVazio = entradaPadrao.count { it == '_' }
    var verificaX = 0
    var verificaO = 0

    for (tresPosicao in lista) {
        var str = ""
        for (item in tresPosicao) {
            str += entradaPadrao[item]
            if (str == "XXX") {
                verificaX++
            } else if (str == "OOO") {
                verificaO++
            }
        }
    }


    val resultado = when {
        countX - countO > 1 || countO - countX > 1 -> "Impossible"
        verificaX > 0 && verificaO > 0 -> "Impossible"
        verificaX == 0 && verificaO == 0 && countVazio > 0 -> "Game not finished"
        verificaX == 0 && verificaO == 0 && countVazio == 0 -> "Draw"
        verificaX == 1 && verificaO == 0 -> "X wins"
        verificaX == 0 && verificaO == 1 -> "O wins"
        else -> ""
    }
    return resultado
}


fun interfaceJogoDaVelha(cordenadas: MutableList<MutableList<Char>>) {
    // Cria a tabela do jogo através das cordenadas padrão.

    linha()
    for (lista in cordenadas) {
        println("| ${lista.joinToString(" ")} |")
    }
    linha()
}


fun linha() {
    println("---------")
}