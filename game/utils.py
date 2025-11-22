import time

def typewriter(text, delay=0.08):
    for char in text:
        print(char, end='', flush=True)
        time.sleep(delay)
    print()

def dramatic_print(text, delay=0.12):
    words = text.split()
    for i, word in enumerate(words):
        print(word, end=' ', flush=True)
        time.sleep(delay)
        if word.endswith(('.', '!', '?', '...')):
            time.sleep(delay * 3)
    print()

def input_typewriter(prompt, delay=0.08):
    typewriter(prompt, delay)
    return input("➡️  ")


def wait_for_enter():
    """Pausa e espera o jogador pressionar Enter para continuar"""
    input("\n[Pressione Enter para continuar...]")
    print()