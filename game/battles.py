import random
import time
import sys
from utils import typewriter, dramatic_print, input_typewriter


def game_over():
    """Finaliza o jogo quando o player morre"""
    dramatic_print("\n" + "💀" * 20)
    dramatic_print("🎮 FIM DE JOGO - VOCÊ MORREU")
    dramatic_print("💀" * 20)
    dramatic_print("\nO sistema está finalizando...")
    time.sleep(2)
    sys.exit()

def luta_inicial(player):
    print("\n" + "╔" + "═" * 58 + "╗")
    dramatic_print(f"║{'ENCONTRO COM A HORDA DE LOBOS!':^58}║")
    print("╚" + "═" * 58 + "╝")
    
    dramatic_print("🐺 Inimigo: Horda de Lobos Famintos")
    dramatic_print(f"❤️  Vida dos Lobos: 20")
    dramatic_print(f"⚔️  Ataque dos Lobos: 2")
    dramatic_print("─" * 60)

    vida_lobos = 20
    atq_lobos = 2
    venceu = False

    while vida_lobos > 0 and player.vida > 0 and player.stamina > 0:
        dramatic_print(f"\n📊 SEU STATUS:")
        dramatic_print(f"❤️  Sua Vida: {player.vida} | ⚡ Stamina: {player.stamina}")
        dramatic_print(f"🐺 Vida dos Lobos: {vida_lobos}")
        dramatic_print("─" * 40)
        
        ataque = input_typewriter(f"🎯 Qual ataque você deseja usar senhor/a {player.classe}?\n📜 Opções: {', '.join(player.ataques)}\n").title()

        if ataque not in player.ataques:
            dramatic_print("\n❌ Ataque inválido! Você hesitou e os lobos aproveitaram para te atacar!")
            player.vida -= atq_lobos
            dramatic_print(f"🐺 Os lobos te atacam e causam {atq_lobos} de dano.")
            dramatic_print(f"❤️  Sua vida restante: {player.vida}")
            
            if player.vida <= 0:
                dramatic_print("\n💀" * 5)
                dramatic_print("☠️  Desde o inicio eu já sabia o quão fracassado você era, só não sabia que era tanto, você morreu")
                dramatic_print("💀" * 20)
                game_over()
            continue

        info = player.ataques_info[ataque]
        if player.stamina >= info["stam"]:
            vida_lobos -= info["dano"]
            player.stamina -= info["stam"]
            dramatic_print(f"\n✨ Você usou '{ataque}'!")
            dramatic_print(f"💥 Os lobos receberam {info['dano']} de dano. Vida restante: {vida_lobos}. Sua stamina: {player.stamina}")
        else:
            dramatic_print(f"\n😫 Stamina insuficiente para {ataque}!")
            dramatic_print("Você perdeu a vez e os lobos te atacam!")
            player.vida -= atq_lobos
            dramatic_print(f"🐺 Os lobos te atacam e causam {atq_lobos} de dano.")
            dramatic_print(f"❤️  Sua vida restante: {player.vida}")
            
            if player.vida <= 0:
                dramatic_print("\n💀" * 5)
                dramatic_print("☠️  Desde o inicio eu já sabia o quão fracassado você era, só não sabia que era tanto, você morreu")
                dramatic_print("💀" * 20)
                game_over()
            continue
        
        if vida_lobos <= 0:
            dramatic_print("\n" + "🎉" * 5)
            dramatic_print("🎊 Sinceramente... Não achei que você sobreviveria, meus sinceros parabéns. Fracassado.")
            dramatic_print("🎉" * 5)
            venceu = True
            break
        
        dramatic_print(f"\n🐺 Agora é a olhivez dos lobos amigão HAHAHA. Eles te atacam e causam {atq_lobos} de dano.")
        player.vida -= atq_lobos
        dramatic_print(f"❤️  Sua vida restante: {player.vida}")
        
        if player.vida <= 0:
            dramatic_print("\n💀" * 5)
            dramatic_print("☠️  Desde o inicio eu já sabia o quão fracassado você era, só não sabia que era tanto, você morreu")
            dramatic_print("💀" * 20)
            game_over()

    if venceu:
        player.descansar()
        player.ganho_nivel()
        return True
    else:
        player.descansar()
        dramatic_print("💤 Pelo menos você pode descansar... fracassado!")
        return False

def desafio_inicial(player):
    dramatic_print("\n" + "─" * 60)
    decisao1 = input_typewriter("❓ Deseja ir enfrentar um desafio onde você provavelmente irá morrer, mas se sair vivo irá aumentar muito seus atributos por agora? (S/N)\n").upper()

    while decisao1 not in ("S", "N"):
        decisao1 = input_typewriter("❌ Resposta inválida. Por favor, responda com 'S' para sim ou 'N' para não: ").upper()

    if decisao1 == "S":
        dramatic_print("\n🔥 CORAJOSO! Avistamos uma dungeon e entramos nela...")
        return luta_inicial(player)
    else:
        dramatic_print(f"\n🐔 Você decidiu não enfrentar o desafio por agora. É a primeira vez que vejo um {player.classe} medroso!")
        dramatic_print("Boa sorte ao encarar os desafios daqui para frente sem ter upado nada.")
        return True

def batalha_goblins(player):
    print("\n" + "╔" + "═" * 58 + "╗")
    dramatic_print(f"║{'BATALHA CONTRA OS GOBLINS GUARDIÕES!':^58}║")
    print("╚" + "═" * 58 + "╝")
    
    dramatic_print("👹 Inimigo: Goblin Guardião + 2 Goblin Peões")
    dramatic_print(f"❤️  Vida do Goblin Guardião: 15")
    dramatic_print(f"❤️  Vida dos Goblin Peões: 5 cada")
    dramatic_print(f"⚔️  Ataque do Guardião: 3 | Ataque dos Peões: 1")
    dramatic_print("💬 'GRRR! Ninguém passa!'")
    dramatic_print("─" * 60)

    vida_guardiao = 15
    vida_peao1 = 5
    vida_peao2 = 5
    venceu = False
    turno = 1

    while (vida_guardiao > 0 or vida_peao1 > 0 or vida_peao2 > 0) and player.vida > 0 and player.stamina > 0:
        dramatic_print(f"\n🎯 TURNO {turno}")
        dramatic_print(f"📊 SEU STATUS: ❤️ {player.vida} | ⚡ {player.stamina}")
        dramatic_print(f"👹 INIMIGOS: Guardião ❤️{max(0, vida_guardiao)} | Peão1 ❤️{max(0, vida_peao1)} | Peão2 ❤️{max(0, vida_peao2)}")
        dramatic_print("─" * 50)
        
        # SISTEMA DE ALVO CORRIGIDO
        alvos_disponiveis = []
        dramatic_print("🎯 ESCOLHA SEU ALVO:")
        
        if vida_guardiao > 0:
            alvos_disponiveis.append("Guardião")
            dramatic_print("🔴 1- Goblin Guardião (Vida: 15, Ataque: 3) - LÍDER")
        if vida_peao1 > 0:
            alvos_disponiveis.append("Peão1")
            dramatic_print("🟡 2- Goblin Peão 1 (Vida: 5, Ataque: 1) - FRACO")
        if vida_peao2 > 0:
            alvos_disponiveis.append("Peão2") 
            dramatic_print("🟢 3- Goblin Peão 2 (Vida: 5, Ataque: 1) - FRACO")
        
        escolha = input_typewriter("\n🎯 Escolha o alvo (1/2/3) ou digite o nome do ataque: ")

        # CORREÇÃO DO SISTEMA DE ALVO
        alvo_escolhido = None
        ataque = None
        
        if escolha in ["1", "2", "3"]:
            escolha_num = int(escolha)
            if 1 <= escolha_num <= len(alvos_disponiveis):
                alvo_escolhido = alvos_disponiveis[escolha_num-1]
                ataque = input_typewriter(f"🎯 Qual ataque usar em {alvo_escolhido}? {', '.join(player.ataques)}: ").title()
            else:
                dramatic_print("❌ Alvo inválido! Você fica confuso e os goblins te atacam!")
                # ATAQUE DOS GOBLINS POR ERRO DE ALVO
                dano_total_goblins = 0
                if vida_guardiao > 0:
                    player.vida -= 3
                    dano_total_goblins += 3
                    dramatic_print("🔴 Goblin Guardião te ataca com seu machado! (-3❤️)")
                if vida_peao1 > 0:
                    player.vida -= 1
                    dano_total_goblins += 1
                    dramatic_print("🟡 Goblin Peão 1 te joga uma pedra! (-1❤️)")
                if vida_peao2 > 0:
                    player.vida -= 1  
                    dano_total_goblins += 1
                    dramatic_print("🟢 Goblin Peão 2 te ataca com uma faca! (-1❤️)")
                
                dramatic_print(f"💔 Dano total recebido: {dano_total_goblins}")
                dramatic_print(f"❤️  Sua vida restante: {player.vida}")
                
                if player.vida <= 0:
                    dramatic_print("\n💀" * 8)
                    dramatic_print("☠️  Desde o inicio eu já sabia o quão fracassado você era, só não sabia que era tanto, você morreu")
                    dramatic_print("💀" * 8)
                    game_over()
                continue
        else:
            ataque = escolha.title()
            if vida_peao1 > 0:
                alvo_escolhido = "Peão1"
            elif vida_peao2 > 0:
                alvo_escolhido = "Peão2" 
            elif vida_guardiao > 0:
                alvo_escolhido = "Guardião"
            else:
                dramatic_print("❌ Nenhum alvo disponível!")
                continue

        if ataque not in player.ataques:
            dramatic_print("❌ Ataque inválido! Os goblins riem da sua incompetência e te atacam!")
            dano_total_goblins = 0
            if vida_guardiao > 0:
                player.vida -= 3
                dano_total_goblins += 3
                dramatic_print("🔴 Goblin Guardião te ataca com seu machado! (-3❤️)")
            if vida_peao1 > 0:
                player.vida -= 1
                dano_total_goblins += 1
                dramatic_print("🟡 Goblin Peão 1 te joga uma pedra! (-1❤️)")
            if vida_peao2 > 0:
                player.vida -= 1  
                dano_total_goblins += 1
                dramatic_print("🟢 Goblin Peão 2 te ataca com uma faca! (-1❤️)")
            
            dramatic_print(f"💔 Dano total recebido: {dano_total_goblins}")
            dramatic_print(f"❤️  Sua vida restante: {player.vida}")
            
            if player.vida <= 0:
                dramatic_print("\n💀" * 8)
                dramatic_print("☠️  Desde o inicio eu já sabia o quão fracassado você era, só não sabia que era tanto, você morreu")
                dramatic_print("💀" * 8)
                game_over()
            continue

        info = player.ataques_info[ataque]

        if player.stamina < info["stam"]:
            dramatic_print(f"😫 Stamina insuficiente para {ataque}!")
            dramatic_print("Você tropeça e fica vulnerável! Os goblins te atacam!")
            dano_total_goblins = 0
            if vida_guardiao > 0:
                player.vida -= 3
                dano_total_goblins += 3
                dramatic_print("🔴 Goblin Guardião te ataca com seu machado! (-3❤️)")
            if vida_peao1 > 0:
                player.vida -= 1
                dano_total_goblins += 1
                dramatic_print("🟡 Goblin Peão 1 te joga uma pedra! (-1❤️)")
            if vida_peao2 > 0:
                player.vida -= 1  
                dano_total_goblins += 1
                dramatic_print("🟢 Goblin Peão 2 te ataca com uma faca! (-1❤️)")
            
            dramatic_print(f"💔 Dano total recebido: {dano_total_goblins}")
            dramatic_print(f"❤️  Sua vida restante: {player.vida}")
            
            if player.vida <= 0:
                dramatic_print("\n💀" * 8)
                dramatic_print("☠️  Desde o inicio eu já sabia o quão fracassado você era, só não sabia que era tanto, você morreu")
                dramatic_print("💀" * 8)
                game_over()
            continue

        player.stamina -= info["stam"]
        dano = info["dano"]
        
        if alvo_escolhido == "Guardião" and vida_guardiao > 0:
            vida_guardiao -= dano
            dramatic_print(f"\n✨ Você usou '{ataque}' no Goblin Guardião!")
            dramatic_print(f"💥 Causou {dano} de dano! ❤️ Guardião: {max(0, vida_guardiao)}")
        elif alvo_escolhido == "Peão1" and vida_peao1 > 0:
            vida_peao1 -= dano
            dramatic_print(f"\n✨ Você usou '{ataque}' no Goblin Peão 1!")
            dramatic_print(f"💥 Causou {dano} de dano! ❤️ Peão 1: {max(0, vida_peao1)}")
        elif alvo_escolhido == "Peão2" and vida_peao2 > 0:
            vida_peao2 -= dano
            dramatic_print(f"\n✨ Você usou '{ataque}' no Goblin Peão 2!")
            dramatic_print(f"💥 Causou {dano} de dano! ❤️ Peão 2: {max(0, vida_peao2)}")
        else:
            dramatic_print("🎯 Alvo já está derrotado! Ataque desperdiçado...")
        
        if vida_guardiao <= 0 and vida_peao1 <= 0 and vida_peao2 <= 0:
            dramatic_print("\n" + "🎉" * 8)
            dramatic_print("🏆 VITÓRIA! Você derrotou todos os goblins!")
            dramatic_print("🚪 A entrada da dungeon está livre!")
            dramatic_print("⭐ Bom trabalho, aventureiro!")
            dramatic_print("🎉" * 8)
            venceu = True
            break
        
        dramatic_print(f"\n👹 VEZ DOS GOBLINS!")
        dano_total_goblins = 0
        
        if vida_guardiao > 0:
            player.vida -= 3
            dano_total_goblins += 3
            dramatic_print("🔴 Goblin Guardião te ataca com seu machado! (-3❤️)")
        
        if vida_peao1 > 0:
            player.vida -= 1
            dano_total_goblins += 1
            dramatic_print("🟡 Goblin Peão 1 te joga uma pedra! (-1❤️)")
            
        if vida_peao2 > 0:
            player.vida -= 1  
            dano_total_goblins += 1
            dramatic_print("🟢 Goblin Peão 2 te ataca com uma faca! (-1❤️)")
        
        dramatic_print(f"💔 Dano total recebido: {dano_total_goblins}")
        dramatic_print(f"❤️  Sua vida restante: {player.vida}")
        
        frases_goblins = [
            "'Morra, intruso!'",
            "'Ninguém passa por nós!'", 
            "'Sua carne vai virar jantar!'",
            "'Hihihi, ele está sangrando!'"
        ]
        dramatic_print(f"💬 Goblins: {random.choice(frases_goblins)}")
        
        if player.vida <= 0:
            dramatic_print("\n💀" * 8)
            dramatic_print("☠️  Desde o inicio eu já sabia o quão fracassado você era, só não sabia que era tanto, você morreu")
            dramatic_print("💀" * 8)
            game_over()
            
        turno += 1

    if venceu:
        player.descansar()
        player.ganho_nivel()
        dramatic_print("\n🔮 Agora você pode entrar na dungeon...")
        return True
    else:
        player.descansar()
        dramatic_print("\n💤 Você recua enquanto os goblins zombam...")
        dramatic_print("💪 Você não é forte o suficiente, não passa de um fracassado. Vá treinar mais, fracote!")
        return False
    


def vitoria_encapuzado(player):
    dramatic_print("\n" + "╔" + "═" * 58 + "╗")
    dramatic_print(f"║{'VITÓRIA CONTRA O IMPOSSÍVEL':^58}║")
    print("╚" + "═" * 58 + "╝")
    
    dramatic_print("💥 Com um último golpe desferido com toda sua força... Você finalmente foi capaz de o derrotar, após uma ardua luta.")
    dramatic_print("🎭 O homem encapuzado cai de joelhos... Sua aura avassaladora que tomava o ambiente, começa a se dissipar.")
    dramatic_print("😲 Ele remove lentamente o traje, junto de seu capuz, revelando um rosto marcado por cicatrizes e sabedoria. Junto de um corpo sem nenhum arranhão se quer, sem ser os golpes que você mesmo o atingiu.")
    
    dramatic_print("Você pensa consigo mesmo: 'Ele era tão forte assim mesmo com essa idade. Talvez se ele fosse mais novo o resultado seria diferente...'")
    dramatic_print("Você escuta ele o chamando para perto... E então ele diz")
    dramatic_print(f"\n👁️  'Incrível, {player.nome}...' ele sussurra com voz rouca.")
    dramatic_print("🌟 'Você alcançou o que eu pensei ser impossível...'")
    dramatic_print("💫 'Durante séculos, eu busquei por alguém que pudesse me superar, que pudesse carregar o fardo... Sabe, por muitos anos eu estava triste por sempre ficar no topo, um lugar solitário, mas hoje... Eu posso descansar. Estou feliz por não ter morrido por doenças ou qualquer outro motivo bobo. Muito obrigado... Saiba...'")
    
    dramatic_print("\n🔮 'O sistema não foi criado para nos controlar, mas para nos preparar.'")
    dramatic_print("🌌 'Prepare-se para a verdade sobre Aincrad, sobre as estátuas, sobre tudo...'")
    dramatic_print("⚡ Sua forma começa a brilhar com uma luz intensa.")
    
    dramatic_print("\n🎯 'Você não derrotou apenas um homem - você derrotou o próprio conceito de limite!'")
    dramatic_print("👑 'A partir de hoje, você é o novo Guardião do Sistema.'")
    dramatic_print("💎 'Use esse poder com sabedoria, pois grandes desafios ainda estão por vir...'")
    
    dramatic_print("\n" + "─" * 60)
    dramatic_print("✨ CONQUISTA DESBLOQUEADA: 'O Novo Guardião'")
    dramatic_print("─" * 60)
    
    player.forca += 50
    player.vida_maxima += 100
    player.stamina_maxima += 200
    player.vida = player.vida_maxima
    player.stamina = player.stamina_maxima
    
    dramatic_print(f"💪 Força: {player.forca} (+50)")
    dramatic_print(f"❤️  Vida: {player.vida_maxima} (+100)")
    dramatic_print(f"⚡ Stamina: {player.stamina_maxima} (+200)")
    
    dramatic_print("\n🌠 O homem sorri pela última vez, era um sorriso cheio de sentimentos, ele estava orgulhoso de você, antes de se dissipar em partículas de luz ele deposita em você sua confiança.")
    dramatic_print("🗝️  'Enfrente as estátuas... Nem eu fui capaz de derrotá-las. Elas guardam a verdade final.'")
    dramatic_print("💭 Sua voz ecoa pela última vez: 'Mostre a elas o que é verdadeiro poder...'")



def batalha_encapuzado(player):
    # Configurações do inimigo
    vida_encapuzado = 150
    stamina_encapuzado = 120
    forca_encapuzado = 25
    
    # Sistema de fases da batalha
    fase_batalha = 1
    golpes_especiais_desbloqueados = False

    dramatic_print("\n" + "╔" + "═" * 58 + "╗")
    dramatic_print(f"║{'BATALHA CONTRA O HOMEM QUE VENCEU O SISTEMA!':^58}║")
    print("╚" + "═" * 58 + "╝")

    dramatic_print("🦹 Inimigo: Homem Encapuzado")
    dramatic_print(f"❤️  Vida do Encapuzado: {vida_encapuzado}")
    dramatic_print(f"⚔️  Ataque do Encapuzado: {forca_encapuzado}")

    dramatic_print("Eu vejo que você evoluiu... Mas não o bastante para me derrotar. Venha, mostre-me do que é capaz!")

    dramatic_print("Desafio secreto desbloqueado: 'Desafiante do Sistema - Derrote o Homem que venceu do Sistema'")

    dramatic_print("Atributos buffados para o desafio")

    player.vida = 90
    player.stamina = 90
    player.forca = 15

    dramatic_print("'Vamos ver o quanto eu progredi nesses 3 meses... Eu quero testar AQUILO...' - Você pensa consigo mesmo.")    

    player.ataques

    while vida_encapuzado > 0 and player.vida > 0:
        # Sistema de fases dinâmico
        if vida_encapuzado <= 50 and fase_batalha == 1:
            fase_batalha = 2
            dramatic_print("\n🔥 FASE 2 DESBLOQUEADA: O VÉU DA REALIDADE SE ABRE!")
            dramatic_print("🌌 SEUS GOLPES ESPECIAIS MAIS PODEROSOS ESTÃO LIBERADOS!")
        
            dramatic_print(f"🔮 {player.classe}: {', '.join(player.ataques)}")
            golpes_especiais_desbloqueados = True

        # Display épico de status
        print("\n" + "🌠" * 30)
        dramatic_print(f"🎭 FASE {fase_batalha} | ❤️  ENCAPUZADO: {vida_encapuzado}/150 | ❤️  {player.nome}: {player.vida}/90")
        dramatic_print(f"⚡ SUA STAMINA: {player.stamina}/90 | 🌪️  AURA DO INIMIGO: {stamina_encapuzado}/120")
        print("🌠" * 30)
        
        # MENU DE ATAQUES PERSONALIZADO POR CLASSE
        dramatic_print("\n🎯 ESCOLHA SEU ATAQUE:")
        
        # ✅ FASE 1: Ataques básicos + alguns especiais
        # ✅ FASE 2: APENAS ataques da classe (TODOS)
        opcoes_ataques = {}
        
        if not golpes_especiais_desbloqueados:
            # FASE 1 - Ataques básicos + até 4 especiais
            opcoes_ataques = {
                "1": "🗡️  Ataque Básico (5 stamina)",
                "2": "🛡️  Defender (10 stamina)", 
                "3": "🎯 Focar (0 stamina)"
            }
            
            # Adicionar alguns ataques especiais na Fase 1
            for i, ataque in enumerate(player.ataques[:4], 4):  # Máximo 4 especiais
                info = player.ataques_info[ataque]
                opcoes_ataques[str(i)] = f"✨ {ataque} ({info['stam']} stamina)"
        else:
            # ✅ FASE 2 - APENAS ATAQUES DA CLASSE (TODOS)
            for i, ataque in enumerate(player.ataques, 1):
                info = player.ataques_info[ataque]
                opcoes_ataques[str(i)] = f"✨ {ataque} ({info['stam']} stamina)"
        
        # Mostrar menu
        for key, value in opcoes_ataques.items():
            dramatic_print(f"{key}. {value}")
        
        escolha = input_typewriter("\n➡️  Digite sua escolha: ").strip()
        
        # SISTEMA DE ATAQUES DO JOGADOR
        dano_jogador = 0
        custo_stamina = 0
        efeito_extra = ""
        
        if not golpes_especiais_desbloqueados:
            # ✅ FASE 1 - Sistema original
            if escolha == "1":
                dano_jogador = random.randint(8, 15) + player.forca
                custo_stamina = 5
                dramatic_print(f"⚡ Ataque Básico! Dano: {dano_jogador}")
                
            elif escolha == "2":
                dano_jogador = random.randint(3, 8) + player.forca
                custo_stamina = 10
                player.vida += 8
                efeito_extra = " + Cura: 8"
                dramatic_print(f"🛡️  Defesa com Contra-Ataque! Dano: {dano_jogador}{efeito_extra}")
                
            elif escolha == "3":
                custo_stamina = 0
                bonus_dano = random.randint(5, 12)
                player.forca += bonus_dano
                dramatic_print(f"🎯 Foco Intenso! Força +{bonus_dano} neste turno!")
                
            elif escolha in ["4", "5", "6", "7"]:
                index_ataque = int(escolha) - 4
                if index_ataque < len(player.ataques):
                    nome_ataque = player.ataques[index_ataque]
                    info_ataque = player.ataques_info[nome_ataque]
                    
                    dano_jogador = info_ataque["dano"] + random.randint(5, 15)
                    custo_stamina = info_ataque["stam"]
                    
                    if player.classe == "Mago":
                        dramatic_print(f"🔮 {nome_ataque}! Dano Mágico: {dano_jogador}")
                    elif player.classe == "Espadachim":
                        dramatic_print(f"⚔️  {nome_ataque}! Dano de Corte: {dano_jogador}")
                    elif player.classe == "Berserker":
                        dramatic_print(f"🪓 {nome_ataque}! Dano Brutal: {dano_jogador}")
                else:
                    dramatic_print("❌ Ataque inválido! Você perde o turno.")
                    custo_stamina = 5
            else:
                dramatic_print("❌ Movimento inválido! Você perde o turno!")
                custo_stamina = 5
        else:
            if escolha in [str(i) for i in range(1, len(player.ataques) + 1)]:
                index_ataque = int(escolha) - 1
                if index_ataque < len(player.ataques):
                    nome_ataque = player.ataques[index_ataque]
                    info_ataque = player.ataques_info[nome_ataque]
                    
                    dano_jogador = info_ataque["dano"] + random.randint(5, 15)
                    custo_stamina = info_ataque["stam"]
                    
                    # Efeitos visuais épicos por classe
                    if player.classe == "Mago":
                        dramatic_print(f"🔮 {nome_ataque}! Dano Mágico: {dano_jogador}")
                    elif player.classe == "Espadachim":
                        dramatic_print(f"⚔️  {nome_ataque}! Dano de Corte: {dano_jogador}")
                    elif player.classe == "Berserker":
                        dramatic_print(f"🪓 {nome_ataque}! Dano Brutal: {dano_jogador}")
                else:
                    dramatic_print("❌ Ataque inválido! Você perde o turno.")
                    custo_stamina = 5
            else:
                dramatic_print("❌ Movimento inválido! Você perde o turno!")
                custo_stamina = 5
        

        if player.stamina >= custo_stamina:
            player.stamina -= custo_stamina
            vida_encapuzado -= max(0, dano_jogador)
        else:
            dramatic_print("💨 STAMINA INSUFICIENTE! Movimento falha!")
        
        # Verificar vitória
        if vida_encapuzado <= 0:
            dramatic_print(f"\n🎉 VOCÊ DERROTOU O HOMEM ENCAPUZADO!")
            vitoria_encapuzado(player)
            return True
        
        # TURNO DO INIMIGO
        dramatic_print(f"\n🎭 TURNO DO HOMEM ENCAPUZADO...")
        time.sleep(2)
        
        if fase_batalha == 1:
            ataques_inimigo = [
                ("👊 Soco Sônico", random.randint(10, 20)),
                ("🌀 Campo de Força", random.randint(5, 15)), 
                ("💨 Investida Relâmpago", random.randint(15, 25))
            ]
        else:
            ataques_inimigo = [
                ("🌌 DISRUPTOR DIMENSIONAL", random.randint(25, 40)),
                ("💀 COLAPSO EXISTENCIAL", random.randint(30, 45)),
                ("⚡ PARADOXO TEMPORAL", random.randint(20, 35))
            ]
        
        ataque_escolhido = random.choice(ataques_inimigo)
        dano_inimigo = ataque_escolhido[1]
        
        chance_esquiva = min(30, player.stamina)
        if random.randint(1, 100) <= chance_esquiva:
            dramatic_print(f"🎯 Você esquiva habilmente do {ataque_escolhido[0]}!")
            dano_inimigo = 0
        else:
            dramatic_print(f"💥 {ataque_escolhido[0]} acerta você! Dano: {dano_inimigo}")
            player.vida -= dano_inimigo
        
        # Regeneração
        player.stamina = min(player.stamina + 8, 90)
        stamina_encapuzado = min(stamina_encapuzado + 10, 120)
        
        if player.vida <= 0:
            break
        
        time.sleep(1)

    if player.vida <= 0:
        dramatic_print("\n💀 VOCÊ CHEGOU AO SEU LIMITE...")
        dramatic_print("O homem encapuzado olha para você sorrindo...")
        dramatic_print("Diferente do sorriso da estátua, aquele não era um sorriso de desprezo, e sim de admiração.")
        dramatic_print("Você chegou longe, muito longe, meu jovem... Eu acredito no seu potencial. Porém não podem haver dois vencedores do sistema.")
        dramatic_print("Ele prepara um golpe final, ele ergue sua mão o clima ao redor começa a mudar...")
        dramatic_print("Você pensa consigo mesmo: 'Eu não estou triste, eu consegui mudar o destino da minha vida, eu consegui me tornar mais forte, eu evolui, treinei, não fico triste, pois eu dei meu máximo e sei disso...'")
        dramatic_print("Você continua pensando: 'A única coisa em que me arrependo, é de não poder me vingar daquela estátua que me subestimou, mas tudo bem, afinal eu me tornei o segundo homem mais forte que já existiu nesse mundo...'")
        dramatic_print("Corta uma tela que mostra os homens mais fortes que já existiram, o primeiro tem seu nome como ???, o segundo é você.")
        dramatic_print(f"O homem encapuzado diz antes de te atacar: 'Você foi um adversário digno, muitos já tiveram a oportunidade dada pelo sistema, mas poucos conseguiram chegar tão longe quanto você... Meus parabéns {player.nome} você é realmente forte...'")
        dramatic_print("Prestes a dar o golpe final, o homem encapuzado diz: 'A verdade garoto... É que minha era já acabou há muitos anos, eu só queria alguém forte o suficiente para me enfrentar em uma luta linda até a morte. O homem então remove seu traje, junto de seu capuz... E por trás daquele traje havia um homem, por volta de 50 anos, que nunca tivera recebido um acerto em seu corpo, o único capaz de tal feito, foi você.'")
        dramatic_print("Ele diz: 'Graças a você eu me lembrei da minha época onde eu gostava disso tudo, eu só queria que tudo chegasse ao fim, enfrentando alguém mais forte que eu, porém esse dia nunca chegou. Ao eu lutar contra você hoje pirralho, eu sei que você irá me superar, ainda está cedo para você se despedir deste mundo... Muito obrigado por me fazer relembrar dos velhos tempos.' Com uma lágrima no rosto do homem ele diz suas últimas palavras: 'Se torne mais forte do que eu, e viva uma vida de que você jamais irá se arrepender.' Disparando o golpe em que ele tinha carregado contra sí mesmo.")
        dramatic_print("Você mesmo sem conhecer ele há muito tempo, entende o sentimento do mesmo. 'Então essa é a solidão do mais forte...' Você pensa")

        dramatic_print("Quando se dá conta, você está vivo, e o homem encapuzado ainda se mantém de pé em sua frente... Porém sem mais vida")

        dramatic_print("Conquista Desbloqueada - 'O Homem mais forte do Mundo'. Melhorando seus atributos")

        player.forca += 50  
        player.vida_maxima += 100
        player.stamina_maxima += 200
        player.vida = player.vida_maxima
        player.stamina = player.stamina_maxima

        dramatic_print("Seus novos status são: ")
        dramatic_print(f"\n📊 SEU STATUS:")
        dramatic_print(f"❤️  Sua Vida: {player.vida} | ⚡ Stamina: {player.stamina}")
        dramatic_print("─" * 40)

    return player.vida > 0