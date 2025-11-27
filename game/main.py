from player import Game
from battles import desafio_inicial, batalha_goblins, batalha_encapuzado

def main():

    jogo1 = Game("Low", "Espadachim") # (ESCOLHA SEU NOME (PRIMEIRA ASPAS), ESCOLHA SUA CLASSE (ESPADACHIM, MAGO OU BERSERKER))
    jogo1.stats_classe()
    jogo1.ataques()
    jogo1.begin()
    desafio_inicial(jogo1)

    # Jogo Parte 2
    jogo1.parte2()
    batalha_goblins(jogo1)

    # Jogo Parte 3
    jogo1.parte3()

    # Jogo Parte 4
    jogo1.parte4()

    # Jogo Parte 5
    jogo1.parte5()
    batalha_encapuzado(jogo1)

    # Jogo Parte 6
    jogo1.parte6()
    
if __name__ == "__main__":
    main()