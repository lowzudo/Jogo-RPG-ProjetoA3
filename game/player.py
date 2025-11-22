import random
import time
import sys
from treino import cena_treino_sistema
from utils import typewriter, dramatic_print, input_typewriter, wait_for_enter

class Game:
    classes = ("Mago", "Espadachim", "Berserker")

    def __init__(self, nome, classe):
        self.nome = nome
        self.nivel = 1
        self.forca = 1
        self.vida = 1
        self.stamina = 1
        if classe not in Game.classes:
            raise ValueError("Classe inválida. Escolha entre Mago, Espadachim ou Berserker.")    
        self.classe = classe

    def stats_classe(self):
        if self.classe == "Mago":
            self.forca = 3
            self.vida = 7
            self.stamina = 15
            self.stamina_maxima = 15
            self.vida_maxima = 7
        elif self.classe == "Espadachim":
            self.forca = 5
            self.vida = 6  
            self.stamina = 12
            self.stamina_maxima = 12
            self.vida_maxima = 6
        else:
            self.forca = 4 
            self.vida = 10
            self.stamina = 14
            self.stamina_maxima = 14
            self.vida_maxima = 10

    def ganho_nivel(self):
        forca_antiga = self.forca
        vida_antiga = self.vida
        stamina_antiga = self.stamina
            
        self.nivel += 1
        self.forca += 2
        self.vida += 3
        self.stamina += 5
        self.stamina_maxima += 5
        self.vida_maxima += 3

        typewriter("=" * 60)
        
        if self.nivel <= 5:
            dramatic_print(f"Você realmente está evoluindo, fracassado! Você alcançou o nivel {self.nivel}.")
        elif self.nivel > 5 and self.nivel <= 10:
            dramatic_print(f"Você está se saindo bem, mas ainda é um fracassado! Você alcançou o nivel {self.nivel}.")
        else:
            dramatic_print(f"Quem diria que você fosse chegar até aqui, o admiro garoto. Você conseguiu ultrapassar os limites do jogo e está acima do nivel máximo. Um verdadeiro prodígio entre os fracassados. Alegre-se, você se tornou uma lenda!")

        dramatic_print("-" * 40)
        dramatic_print(f"Força: {forca_antiga} -> {self.forca} (+2)")
        dramatic_print(f"Vida: {vida_antiga} -> {self.vida} (+3)")
        dramatic_print(f"Stamina: {stamina_antiga} -> {self.stamina} (+5)")
        typewriter("=" * 60)
        
        wait_for_enter()

    def ataques(self):
        if self.classe == "Mago":
            self.ataques_info = {
            "Bola De Fogo": {"stam": 3, "dano": 2},
            "Raio Congelante": {"stam": 4, "dano": 3 },
            "Tempestade Arcana": {"stam": 6, "dano": 4 }
            }
            self.ataques = list(self.ataques_info.keys())
        
        elif self.classe == "Espadachim":
            self.ataques_info = {
            "Corte Rapido": {"stam": 2, "dano": 1 },
            "Investida": {"stam": 3, "dano": 2 },
            "Shishi Sonson": {"stam": 5, "dano": 3 }
            }
            self.ataques = list(self.ataques_info.keys()) 
        
        elif self.classe == "Berserker":
            self.ataques_info = {
                "Golpe Brutal": {"stam": 4, "dano": 3 },
                "Fúria Selvagem": {"stam": 5, "dano": 4 },
                "Terremoto": {"stam": 7, "dano": 5 }
            }
            self.ataques = list(self.ataques_info.keys())

    def descansar(self):
        self.stamina = self.stamina_maxima
        self.vida = self.vida_maxima

    def begin(self):
        print("\n" + "=" * 60)
        dramatic_print("SWORD ART ONLINE - INÍCIO DA JORNADA")
        print("=" * 60)
        
        dramatic_print(f"Jogador: {self.nome}")
        dramatic_print(f"Classe: {self.classe}")
        dramatic_print(f"Nível: {self.nivel}")
        dramatic_print(f"Força: {self.forca}")
        dramatic_print(f"Vida: {self.vida}")
        dramatic_print(f"Stamina: {self.stamina}")
        
        wait_for_enter()
        
        dramatic_print("\n" + "-" * 60)
        dramatic_print("AVISOS NECESSÁRIOS PARA SUA SOBREVIVÊNCIA:")
        dramatic_print("1- Você deve sempre estar atento aos seus arredores, inimigos podem surgir a qualquer momento.")
        dramatic_print("2- Sempre gerencie bem sua stamina, ataques mais fortes consomem mais stamina, caso sua stamina chegue a zero, você morrerá instantaneamente.")
        dramatic_print("3- Procure sempre evoluir seu nivel, com o nivel sendo aumentado, seus atributos também subirão, isso irá facilitar sua jornada.")
        dramatic_print("4- Divirta-se ou morra!")
        dramatic_print("-" * 60)
        
        wait_for_enter()

    def parte2(self):
        print("\n" + "=" * 60)
        dramatic_print("CAPÍTULO 2: A DUNGEON DOS GOBLINS")
        print("=" * 60)
        
        dramatic_print("Após alguns dias explorando o mundo de Aincrad, você se sente mais confiante.")
        dramatic_print("Após vagar dias, você avista outra dungeon ao longe, decidindo se aproximar dela.")
        dramatic_print("Ao chegar perto, você percebe que a entrada está cercada por goblins que parecem estar protegendo algo.")
        dramatic_print("Você sabe que enfrentá-los será um desafio, mas parece ter uma voz vindo de dentro da dungeon, o chamando para entrar.")
        dramatic_print("Você precisa entrar lá.")
        
        wait_for_enter()
        
        dramatic_print("\nEntão fracassado, agora é seu momento!")
        dramatic_print("Respire e vá a batalha contra esses goblins nojentos!")
        dramatic_print("Descubra cada vez mais sobre esse mundo!")
        dramatic_print("\nEssa é a primeira e última vez que irei encorajá-lo a enfrentar alguém,")
        dramatic_print("então não me decepcione fracassado!")
        dramatic_print("Essa dungeon tem algo de especial,")
        dramatic_print("eu quero que você derrote-os e descubra o que está lá HAHAHAHA!")
        
        wait_for_enter()

    def parte3(self):
        print("\n" + "=" * 60)
        dramatic_print("CAPÍTULO 3: O TEMPLO DOS DEUSES")
        print("=" * 60)
        
        dramatic_print("Ao entrar na dungeon, você sente um clima estranho, como se a todo momento você estivesse sendo observado. Mesmo enfrentando os goblins, e vários outros inimigos dentro dessa dungeon, a sensação persiste.")
        dramatic_print("Você encontra uma sala, com uma porta gigante, adornada com símbolos antigos e misteriosos. Ao se aproximar, uma voz ecoa em sua cabeça, 'Entre', diz a voz. Você sente calafrios, sente que algo grande irá acontecer caso você entre nessa sala... Porém, não sabe dizer se será algo bom ou ruim.")
        
        wait_for_enter()
        
        decisao = input_typewriter("\nO que você irá fazer? (entrar/abandonar): ").strip().lower()

        if decisao == "entrar":
            dramatic_print("\n" + "=" * 60)
            dramatic_print("VOCÊ DECIDIU ENTRAR!")
            print("=" * 60)
            
            dramatic_print("\nVocê toma coragem e atravessa a porta gigante. Ao entrar, uma luz intensa te envolve.")
            dramatic_print("Você sente uma energia ancestral percorrer seu corpo.")
            dramatic_print("As vozes na sua mente sussurram segredos antigos do mundo de Aincrad.")
            
            wait_for_enter()
            
            dramatic_print("\nVocê encontrou o Santuário dos Deuses Antigos... Mas esses Deuses não são benevolentes.")
            dramatic_print("Eles testam a coragem dos aventureiros, apenas para rirem de suas tentativas.")
            dramatic_print("Você lê inscrições nas paredes que falam sobre sacrifícios e desafios.")
            dramatic_print("Enquanto explora o templo, você sente que está sendo avaliado por essas entidades poderosas.")
            dramatic_print("Entidades essas que são estátuas de aproximadamente 20m de altura, cada uma segurando uma arma diferente.")
            
            wait_for_enter()
            
            dramatic_print("\nDe repente, as estátuas começam a se mover, revelando-se como seres vivos, gigantescos e poderosos.")
            dramatic_print("Elas se aproximam de você, e você percebe que está em uma situação extremamente perigosa.")
            
            decisao2 = input_typewriter("\nO que você fará diante dessa situação? (lutar/fugir): ").strip().lower()

            if decisao2 == "lutar":
                dramatic_print("\nLutar é inútil, mesmo querendo, você sabe que não tem força o suficiente contra elas.")
                dramatic_print("Você se sente pequeno diante essas criaturas gigantescas.")
            else:
                dramatic_print("\nVocê tenta fugir, mas a porta já se fechou atrás de você.")
                dramatic_print("Você está preso ali e sente que as deixou furiosas.")

            dramatic_print("\nVocê percebe que está em uma situação extremamente perigosa, e que talvez, a única maneira de sobreviver seja obedecendo essas criaturas gigantescas...")

            wait_for_enter()

            dramatic_print("\nVocê aceita o que está acontecendo alí, e decide se curvar diante dessas estatuas.")
            dramatic_print("Essa é a única maneira de sobreviver, você pensa consigo mesmo.")
            dramatic_print("As estátuas parecem se acalmar e param de se movimentar.")
            dramatic_print("Você sente um alívio momentâneo, mas no fundo sabe que está apenas adiando o inevitável.")
            dramatic_print("Com sua cabeça cheia de pensamentos, você tenta encontrar uma maneira de sair dali.")

            wait_for_enter()

            dramatic_print("\nAo escutar vozes vindas de longe, você percebe que a saída da dungeon está próxima.")
            dramatic_print("Você por instinto, grita por ajuda. Quando percebeu, já era tarde de mais, seu corpo foi partido ao meio quase que instantaneamente.")
            dramatic_print("Tudo fica escuro, você sente seu corpo se afundando em seu próprio sangue.")
            dramatic_print("Um calor vem te abraçando, você sabe que está morrendo.")
            dramatic_print("Sua visão começa a escurecer, mas antes de tudo ficar completamente escuro, você vê as estátuas se aproximando de você, e a última coisa que você guarda em sua mente antes de sua morte, é a estátua abrindo um sorriso ao vê-lo morrer.")

            wait_for_enter()

            dramatic_print(f"\nPensamento {self.nome}: 'Droga, minha vida vai acabar, e a última coisa que tenho é a sensação de ser fraco, de que eu poderia mais, aquela estátua... Ela estava sorrindo para mim, desgraçada, se eu tiver uma chance de poder me vingar. Se eu conseguir voltar, eu juro, que irei destruir vocês, todas vocês, Deuses de merda...'")

            print("\n" + "=" * 60)
            dramatic_print("SISTEMA DE EMERGÊNCIA")
            print("=" * 60)

            sistema = input_typewriter("\nVocê deseja se tornar um jogador-sistema? Caso responda não, seu coração irá parar de bater em 0,2 segundos. (sim/não): ").strip().lower()

            if sistema == "sim":
                dramatic_print("\nVocê acorda em um hospital, com o corpo inteiro. Você não está mais partido ao meio, e está vivo, isso é impossivel, você pensa consigo mesmo.")
                dramatic_print("Você começa a se levantar da cama, mas sente medo, medo daquilo se repetir, medo de continuar sendo fraco, medo do inevitável.")
                dramatic_print("Ao olhar para o lado, você vê um homem, encapuzado, você não consegue ver seu rosto, mas sente uma presença poderosa vindo dele.")

                wait_for_enter()

                dramatic_print("\nHomem: 'Então você é o novato? Hmm... Você ainda não passa de um fraco, mas vejo que mesmo com medo, você ainda tem vontade de viver. Obedeça ao sistema garoto, ele te deixara mais forte do que qualquer um.'")

                wait_for_enter()

                dramatic_print("\nAo piscar de olhos o homem some, dúvidas vem a sua cabeça. O que é esse sistema?")
                dramatic_print("Ao se perguntar, uma tela aparece em sua frente, como se fosse um jogo.")
                dramatic_print("Você vê suas estatísticas, seus atributos, e uma série de missões para completar.")
                dramatic_print("Você percebe que agora é um jogador-sistema, e que tem a chance de se tornar mais forte do que nunca.")

            elif sistema == "não":
                dramatic_print("\nVocê realmente nunca passou de um fracassado. Seu coração para de bater. Você morreu.")
                return False
            else:
                dramatic_print("\nResposta inválida. Seu coração para de bater. Você morreu.")
                return False
            
        else:
            dramatic_print(f"\nVocê decide abandonar a dungeon, como um covarde. Você ainda tem medo de enfrentar o desconhecido.")
            dramatic_print("E isso o torna incapaz de evoluir, você está sempre fugindo e fugindo do que desconhece.")
            dramatic_print(f"Mas saiba, caro/a {self.nome}, que essa dungeon, não é do tipo de fazer as pazes com covardes...")

            wait_for_enter()

            dramatic_print("\nVocê sente algo te perseguindo enquanto tenta sair da dungeon.")
            dramatic_print("De repente, uma sombra aparece atrás de você, e antes que possa reagir, tudo fica escuro...")
            dramatic_print("Você foi derrotado, antes de poder fazer qualquer coisa, você foi derrotado, principalmente, por não ter coragem de enfrentar o desconhecido.")

            print("\n" + "-" * 60)
            dramatic_print("O DESPERTAR NAS PROFUNDEZAS")
            print("-" * 60)

            dramatic_print("\nVocê acorda em um lugar, cercado por estatuas gigantes, cada uma com uma arma, as estátuas deviam ter em cerca de 20m de altura.")
            dramatic_print("Você finalmente entende o que estava te observando esse tempo todo, afinal, a sensação agora está mais forte do que nunca.")
            dramatic_print("Você percebe algo estranho, como se fossem escritas pelas paredes, e mesmo estando em uma escrita que você não conhece, você consegue entender perfeitamente o que está escrito ali.")

            wait_for_enter()

            dramatic_print("\nAlí diz... 'Adore-nos', 'Obedeça-nos', 'Sirva-nos'. Você sente um calafrio percorrer sua espinha.")
            dramatic_print("De repente, as estátuas começam a se mover, uma a uma, revelando-se como seres vivos, gigantescos e poderosos.")
            dramatic_print("Elas se aproximam de você, e você percebe que está em uma situação extremamente perigosa.")

            decisao1 = input_typewriter("\nO que você fará diante dessa situação? (lutar/fugir): ").strip().lower()

            if decisao1 == "lutar":
                dramatic_print("\nLutar é inútil, mesmo querendo, você sabe que não tem força o suficiente contra elas.")
            else:
                dramatic_print("\nVocê tenta fugir, mas a porta já se fechou atrás de você.")

            dramatic_print("\nVocê percebe que está em uma situação extremamente perigosa, e que talvez, a única maneira de sobreviver seja obedecendo essas criaturas gigantescas...")

            wait_for_enter()

            dramatic_print("\nVocê aceita o que está acontecendo alí, e decide se curvar diante dessas estatuas.")
            dramatic_print("Essa é a única maneira de sobreviver, você pensa consigo mesmo.")
            dramatic_print("As estátuas parecem se acalmar e param de se movimentar.")
            dramatic_print("Você sente um alívio momentâneo, mas no fundo sabe que está apenas adiando o inevitável.")
            dramatic_print("Com sua cabeça cheia de pensamentos, você tenta encontrar uma maneira de sair dali.")

            wait_for_enter()

            dramatic_print("\nAo escutar vozes vindas de longe, você percebe que a saída da dungeon está próxima.")
            dramatic_print("Você por instinto, grita por ajuda. Quando percebeu, já era tarde de mais, seu corpo foi partido ao meio quase que instantaneamente.")
            dramatic_print("Tudo fica escuro, você sente seu corpo se afundando em seu próprio sangue.")
            dramatic_print("Um calor vem te abraçando, você sabe que está morrendo.")
            dramatic_print("Sua visão começa a escurecer, mas antes de tudo ficar completamente escuro, você vê as estátuas se aproximando de você, e a última coisa que você guarda em sua mente antes de sua morte, é a estátua abrindo um sorriso ao vê-lo morrer.")

            wait_for_enter()

            dramatic_print(f"\nPensamento {self.nome}: 'Droga, minha vida vai acabar, e a última coisa que tenho é a sensação de ser fraco, de que eu poderia mais, aquela estátua... Ela estava sorrindo para mim, desgraçada, se eu tiver uma chance de poder me vingar. Se eu conseguir voltar, eu juro, que irei destruir vocês, todas vocês, Deuses de merda...'")

            print("\n" + "=" * 60)
            dramatic_print("SISTEMA DE EMERGÊNCIA")
            print("=" * 60)

            sistema = input_typewriter("\nVocê deseja se tornar um jogador-sistema? Caso responda não, seu coração irá parar de bater em 0,2 segundos. (sim/não): ").strip().lower()

            if sistema == "sim":
                dramatic_print("\nVocê acorda em um hospital, com o corpo inteiro. Você não está mais partido ao meio, e está vivo, isso é impossivel, você pensa consigo mesmo.")
                dramatic_print("Você começa a se levantar da cama, mas sente medo, medo daquilo se repetir, medo de continuar sendo fraco, medo do inevitável.")
                dramatic_print("Ao olhar para o lado, você vê um homem, encapuzado, você não consegue ver seu rosto, mas sente uma presença poderosa vindo dele.")

                wait_for_enter()

                dramatic_print("\nHomem: 'Então você é o novato? Hmm... Você ainda não passa de um fraco, mas vejo que mesmo com medo, você ainda tem vontade de viver. Obedeça ao sistema garoto, ele te deixara mais forte do que qualquer um.'")

                wait_for_enter()

                dramatic_print("\nAo piscar de olhos o homem some, dúvidas vem a sua cabeça. O que é esse sistema?")
                dramatic_print("Ao se perguntar, uma tela aparece em sua frente, como se fosse um jogo.")
                dramatic_print("Você vê suas estatísticas, seus atributos, e uma série de missões para completar.")
                dramatic_print("Você percebe que agora é um jogador-sistema, e que tem a chance de se tornar mais forte do que nunca.")

            elif sistema == "não":
                dramatic_print("\nVocê realmente nunca passou de um fracassado. Seu coração para de bater. Você morreu.")
                return False
            else:
                dramatic_print("\nResposta inválida. Seu coração para de bater. Você morreu.")
                return False
        
        wait_for_enter()
        return True

    def parte4(self):
        print("\n" + "=" * 60)
        dramatic_print("CAPÍTULO 4: A ASCENSÃO DO JOGADOR-SISTEMA")
        print("=" * 60)
        
        dramatic_print("\nApós toda essa loucura, você ainda está processando o que aconteceu.")
        dramatic_print("Enquanto a tela do sistema flutua diante de seus olhos, você lê as missões que precisa completar para ganhar 'Recompensas' e 'Habilidades'.")
        dramatic_print("Mas caso o contrário, tem algo bem grande e vermelho escrito 'Punição caso você não complete as missões. Tempo até missão acabar: 5 horas'.")

        wait_for_enter()

        while True:  
            decisao = input_typewriter("\nO que você fará? ('aceitar/adiar'): ").strip().lower()

            if decisao == "aceitar":
                dramatic_print("\nVocê aceitou as missões do Sistema!")
                recompensas = cena_treino_sistema()
                if recompensas:
                    self.aplicar_recompensas(recompensas)
                break
                
            elif decisao == "adiar":
                while True:
                    confirmar = input_typewriter("\nTem certeza que deseja adiar as missões? Isso pode ter consequências graves. (sim/não): ").strip().lower()
                    if confirmar == "sim":
                        break
                    elif confirmar == "não":
                        decisao = "aceitar"
                        break
                    else:
                        dramatic_print("\nResposta inválida. Digite 'sim' ou 'não'.")
                dramatic_print("\nVocê decide adiar as missões, mas o tempo está passando...")
                x = 5
                while x != 0:
                    dramatic_print(f"\nTempo restante para completar as missões: {x} horas")
                    time.sleep(1)
                    x -= 1
                    dramatic_print("Você sente a pressão do Sistema...")
                continue
                
            else:
                dramatic_print("\nResposta inválida. Digite 'aceitar' ou 'adiar'")

        wait_for_enter()

    def aplicar_recompensas(self, recompensas):
        self.forca += recompensas['forca']
        self.vida_maxima += recompensas['vida']
        self.vida = self.vida_maxima
        self.stamina_maxima += recompensas['stamina']
        self.stamina = self.stamina_maxima
        
        dramatic_print("\nATRIBUTOS ATUALIZADOS!")
        dramatic_print(f"Força: {self.forca}")
        dramatic_print(f"Vida: {self.vida_maxima}") 
        dramatic_print(f"Stamina: {self.stamina_maxima}")
        
        wait_for_enter()

    def parte5(self):
        print("\n" + "=" * 60)
        dramatic_print("CAPÍTULO 5: O CONFRONTO FINAL")
        print("=" * 60)
        
        dramatic_print("TIME-SKIP DO FRACASSADO")
        dramatic_print("\n2 meses se passam, e você agora se sente bem mais confiante.")
        dramatic_print("Seus atributos estão bem mais altos do que antes, e você anseia, tem sede de poder.")
        dramatic_print("Cada vez mais e mais, não quer apenas ser um jogador qualquer.")
        dramatic_print("Você quer ser o melhor jogador que já pisou em Aincrad.")
        
        wait_for_enter()

        dramatic_print("\nCom sua percepção, força, agilidade e resistência aumentadas, você tem noção do poder em suas mãos.")
        dramatic_print("Graças a sua percepção aguçada, você consegue detectar inimigos a longas distâncias, antecipar ataques e encontrar pontos fracos em inimigos fácilmente.")
        dramatic_print("Sua força aumentada permite que você cause danos devastadores com seus ataques, derrubando inimigos com facilidade.")
        dramatic_print("Sua agilidade aprimorada torna você incrivelmente rápido e evasivo, permitindo que você desvie de ataques com graça e precisão.")
        dramatic_print("E sua resistência elevada faz com que você suporte mais danos, permitindo que você continue lutando mesmo em situações difíceis.")
        
        wait_for_enter()

        dramatic_print("\nTudo estava tranquilo, porém uma aura avassaladora começa a tomar conta do local em que você está.")
        dramatic_print("Você sente o ar pesando, enquanto aquilo se aproxima de você.")
        dramatic_print("Aquilo claramente não é humano, mas diferente de antes, você não sente medo.")
        dramatic_print("E sim uma excitação, de ter alguém do seu nivel para enfrentar e ver o quanto melhorou.")
        dramatic_print("Você parte para cima da aura, e vê aquele mesmo homem encapuzado, do dia do hospital.")
        
        wait_for_enter()

        dramatic_print(f"\nO homem encapuzado sorri ao ver você se aproximando, ele diz: 'Parece que alguém não tem mais medo do desconhecido...'")
        dramatic_print("'Você evoluiu garoto, tanto ao ponto de eu não conseguir reconhecer sua presença ao se aproximar de mim.'")
        dramatic_print("'Venha com tudo...' Enquanto diz isso, ele começa a liberar uma aura ainda maior, rindo, como se aquilo não fosse nada.")
        
        wait_for_enter()

        dramatic_print("\nVocê sente que tem alguém do seu nivel... Não, sente que ele talvez seja até mais forte que você.")
        dramatic_print("Mas isso não importa, seu coração anseia por isso.")
        
        if self.classe == "Mago":
            self.ataques_info.update({
                "Explosão de Mana": {"stam": 8, "dano": 6 + self.forca},
                "Circulo do Caos": {"stam": 10, "dano": 8 + self.forca},
                "JUNÇÃO DE TODOS OS ELEMENTOS": {"stam": 15, "dano": 12 + self.forca}
            })
            dramatic_print("Mago: Explosão de Mana, Circulo do Caos, JUNÇÃO DE TODOS OS ELEMENTOS")
        
        elif self.classe == "Espadachim":
            self.ataques_info.update({
                "Espada de luz": {"stam": 7, "dano": 5 + self.forca},
                "1000 cortes por segundo": {"stam": 9, "dano": 7 + self.forca},
                "CORTE DIMENSIONAL": {"stam": 14, "dano": 11 + self.forca}
            })
            dramatic_print("Espadachim: Espada de luz, 1000 cortes por segundo, CORTE DIMENSIONAL")
        
        elif self.classe == "Berserker":
            self.ataques_info.update({
                "Quebrador de Planetas": {"stam": 9, "dano": 7 + self.forca},
                "Destruidor de Deuses": {"stam": 11, "dano": 9 + self.forca},
                "DEVASTADOR DE GALÁXIAS": {"stam": 16, "dano": 13 + self.forca}
            })
            dramatic_print("Berserker: Quebrador de Planetas, Destruidor de Deuses, DEVASTADOR DE GALÁXIAS")
        
        self.ataques = list(self.ataques_info.keys())
        
        dramatic_print("\n" + "-" * 60)
        dramatic_print("Agora é a hora de provar seu verdadeiro poder!")
        dramatic_print("Enfrente seu destino, fracassado!")
        dramatic_print("-" * 60)
        
        wait_for_enter()