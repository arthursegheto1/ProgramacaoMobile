class JogoDaVelha {

    private val mesa = Array(3) { CharArray(3) { ' ' } }
    private var jogadorAtual = 'X'
    private var fimJogo = false

    fun iniciarJogo() {
        while (!fimJogo) {
            exibirJogo()
            println("Jogador $jogadorAtual, é sua vez de jogar, informe a linha (0 - 2) e a coluna (0 - 2) separadas por espaço:")
            val (linha, coluna) = obterJogada()

            if (validarJogada(linha, coluna)) {
                mesa[linha][coluna] = jogadorAtual
                if (verificarVitoria(jogadorAtual)) {
                    exibirJogo()
                    println("Parabéns, jogador $jogadorAtual, você venceu o jogo!")
                    fimJogo = true
                } else if (verificarEmpate()) {
                    exibirJogo()
                    println("O jogo terminou em empatado!")
                    fimJogo = true
                } else {
                    jogadorAtual = if (jogadorAtual == 'X') 'O' else 'X'
                }
            } else {
                println("Jogada inválida, tente novamente!")
            }
        }

    }

    private fun exibirJogo() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                print("${mesa[i][j]} ")
            }
            println()
        }
    }

    private fun obterJogada(): Pair<Int, Int> {
        val entrada = readLine()
        val partes = entrada?.split(" ")
        val linha = partes?.get(0)?.toIntOrNull()
        val coluna = partes?.get(1)?.toIntOrNull()
        return Pair(linha ?: -1, coluna ?: -1)
    }

    private fun validarJogada(linha: Int, coluna: Int): Boolean {
        return linha in 0 until 3 && coluna in 0 until 3 && mesa[linha][coluna] == ' '
    }

    private fun verificarVitoria(jogador: Char): Boolean {
//Verificar se houve vitória na horizontal
        for (i in 0 until 3) {
            if (mesa[i][0] == jogador && mesa[i][1] == jogador && mesa[i][2] == jogador) {
                return true
            }
        }
        //Verificar se houve vitória na vertical
        for (j in 0 until 3) {
            if (mesa[0][j] == jogador && mesa[1][j] == jogador && mesa[2][j] == jogador) {
                return true
            }
        }
        //Verificar se houve vitória nas diagonais
        if (mesa[0][0] == jogador && mesa[1][1] == jogador && mesa[2][2] == jogador) {
            return true
        }
        if (mesa[0][2] == jogador && mesa[1][1] == jogador && mesa[2][0] == jogador) {
            return true
        }
        return false
    }

    private fun verificarEmpate(): Boolean {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (mesa[i][j] == ' ') {
                    return false //Porque ainda existem células vazias, o que significa que o jogo ainda não terminou
                }
            }
        }
        return true //Porque não existem mais células vazias, o que significa que o jogo terminou em empate
    }
}
