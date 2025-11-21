from player import Game
from battles import desafio_inicial, batalha_goblins, batalha_encapuzado

def main():

    # Jogo Parte 1
    jogo1 = Game("Low", "Espadachim")
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
    jogo1.parte4()  # ← ADICIONAR ESTA LINHA

    #Jogo Parte5
    jogo1.parte5()
    batalha_encapuzado(jogo1)
    
if __name__ == "__main__":
    main()