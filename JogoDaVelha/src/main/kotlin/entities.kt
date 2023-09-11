class JogoDaVelha {

    private val tabuleiro = Array(3) { CharArray(3) { ' ' } }
    private var jogadorAtual = 'X'
    private var jogoTerminado = false

    fun iniciarJogo() {
        while (!jogoTerminado) {
            exibirTabuleiro()
            println("Jogador $jogadorAtual, é sua vez de jogar, informe a linha (0 - 2) e a coluna (0 - 2) separadas por espaço:")
            val (linha, coluna) = obterJogada()

            if (validarJogada(linha, coluna)) {
                tabuleiro[linha][coluna] = jogadorAtual
                if (verificarVitoria(jogadorAtual)) {
                    exibirTabuleiro()
                    println("Parabéns, jogador $jogadorAtual, você venceu o jogo!")
                    jogoTerminado = true
                } else if (verificarEmpate()) {
                    exibirTabuleiro()
                    println("O jogo terminou em empate!")
                    jogoTerminado = true
                } else {
                    jogadorAtual = if (jogadorAtual == 'X') 'O' else 'X'
                }
            } else {
                println("Jogada inválida, tente novamente!")
            }
        }

    }

    private fun exibirTabuleiro() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                print("${tabuleiro[i][j]} ")
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
        return linha in 0 until 3 && coluna in 0 until 3 && tabuleiro[linha][coluna] == ' '
    }

    private fun verificarVitoria(jogador: Char): Boolean {
//Verificar vitória na horizontal
        for (i in 0 until 3) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true
            }
        }
        //Verificar vitória na vertical
        for (j in 0 until 3) {
            if (tabuleiro[0][j] == jogador && tabuleiro[1][j] == jogador && tabuleiro[2][j] == jogador) {
                return true
            }
        }
        //Verificar vitória nas diagonais
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true
        }
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true
        }
        return false
    }

    private fun verificarEmpate(): Boolean {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (tabuleiro[i][j] == ' ') {
                    return false //Porque ainda existem células vazias, o jogo ainda não terminou
                }
            }
        }
        return true //Porque não existem mais células vazias, o jogo terminou em empate
    }
}
