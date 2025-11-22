from player import Game
from battles import desafio_inicial, batalha_goblins, batalha_encapuzado

def main():

    # Jogo Parte 1
    jogo1 = Game("Bunny", "Mago") #(ESCOLHA SEU NOME (PRIMEIRA ASPAS), ESCOLHA SUA CLASSE (ESPACHIM, MAGO OU BERSERKER))
    jogo1.stats_classe()
    jogo1.ataques()

    #Jogo Parte5
    jogo1.parte5()
    batalha_encapuzado(jogo1)
    
if __name__ == "__main__":
    main()