# 🎮 Sudoku - Jogo em Java (Console e GUI)

> Um jogo de Sudoku clássico, agora com opções para jogar tanto em uma interface de linha de comando quanto em uma interface gráfica de usuário (GUI) construída com Java Swing (JFrame).

---

## ✨ Sobre o Projeto

Este projeto é uma implementação completa do jogo de Sudoku, oferecendo aos jogadores a flexibilidade de escolher entre uma interface de linha de comando (CLI) para uma experiência minimalista e uma interface gráfica de usuário (GUI) intuitiva construída com Java Swing (`JFrame`). O objetivo principal continua sendo criar uma aplicação com código limpo, seguindo os princípios da Programação Orientada a Objetos (POO) e uma boa separação de responsabilidades.

A aplicação foi desenvolvida com foco em:

-   **Dupla Interface:** Suporte tanto para linha de comando quanto para interface gráfica.
-   **Robustez:** Tratamento de erros e exceções para uma experiência de usuário fluida em ambas as interfaces.
-   **Manutenibilidade:** O código é dividido em camadas (Model, Service, UI), facilitando a compreensão e futuras expansões para ambas as interfaces.
-   **Design de Código:** Utilização de padrões como a camada de serviço para encapsular a lógica de negócio e desacoplar as interfaces do modelo de dados.

---

## 🚀 Funcionalidades

### Funcionalidades Comuns (Console e GUI):

-   **Iniciar um Novo Jogo:** Carrega um tabuleiro padrão de dificuldade média.
-   **Inserir e Remover Números:** Permite que o usuário preencha e limpe células do tabuleiro.
-   **Validação de Células Fixas:** Impede que o usuário altere os números que fazem parte do desafio inicial.
-   **Status do Jogo:** Informa se o jogo está completo, incompleto ou se contém erros.
-   **Verificação de Erros:** Sinaliza quando um número inserido não corresponde à solução do quebra-cabeça.
-   **Finalização Automática:** Reconhece quando o tabuleiro foi preenchido corretamente e parabeniza o jogador.
-   **Configuração Flexível:** O jogo carrega configurações de tabuleiro a partir de um mapa interno, facilitando a definição de diferentes quebra-cabeças.

### Funcionalidades da Interface de Linha de Comando (CLI):

-   Menu interativo baseado em texto para todas as ações do jogo.
-   Exibição do tabuleiro no terminal.

### Funcionalidades da Interface Gráfica de Usuário (GUI):

-   Janela interativa com grade para representar o tabuleiro de Sudoku.
-   Campos de texto para inserir os números nas células.
-   Destaque visual para erros ou células fixas.
-   Botões para ações como "Reiniciar Jogo", "Verificar Jogo", "Concluir".

---

## 🛠️ Tecnologias Utilizadas

-   **Java (JDK 21 LTS):** Linguagem principal do projeto.
-   **Java Swing:** Para a construção da interface gráfica (`JFrame`, `JPanel`, `JTextField`, `JButton`).
-   **Sem dependências externas adicionais.**

---

## ⚡ Como Executar o Projeto

Você pode executar o projeto escolhendo entre a interface de linha de comando ou a interface gráfica.

### Pré-requisitos

-   **Java Development Kit (JDK) 21** ou superior instalado e configurado no seu sistema.
-   **Git** para clonar o repositório.

### Passos para Execução

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/thierrysm/sudoku-game-java.git
    cd sudoku-game-java
    ```

2.  **Opção 1: Executar pela IDE (Recomendado)**
    -   Abra o projeto na sua IDE preferida (IntelliJ, Eclipse, VS Code).
    -   **Para a Interface de Console:** Localize o arquivo `Main.java` e execute seu método `main()`.
    -   **Para a Interface Gráfica:** Localize o arquivo principal da GUI `UIMain.java` e execute seu método `main()`.

3.  **Opção 2: Executar pelo Terminal**
    -   Compile todos os arquivos `.java`:
        ```bash
        # Crie um diretório de saída, se não existir
        mkdir out

        # Compile todos os arquivos-fonte para o diretório de saída
        javac -d out src/**/*.java
        ```
    -   Execute a interface desejada:
        -   **Para a Interface de Console:**
            ```bash
            java -cp out Main
            ```
        -   **Para a Interface Gráfica:**
            ```bash
            java -cp out UIMain
            ```

---

## 📖 Como Jogar

As instruções básicas do Sudoku se aplicam a ambas as interfaces.

### Interface de Linha de Comando (CLI):
Siga as instruções do menu para interagir com o jogo. Use os números do teclado para selecionar as opções e preencher as coordenadas e valores.

### Interface Gráfica de Usuário (GUI):
-   Utilize os campos de texto dentro da grade para inserir números de 1 a 9 nas células vazias.
-   Clique nos botões na janela para realizar ações como iniciar um novo jogo, verificar o tabuleiro ou limpar suas jogadas.
-   Células preenchidas no início do jogo (fixas) geralmente serão protegidas contra edição.
-   O feedback sobre erros ou conclusão do jogo será exibido visualmente na interface.

---

## 👤 Contato

**Thierry**

-   **GitHub:** `[@thierrysm](https://github.com/thierrysm)`
-   **LinkedIn:** `[@ThierryMarinho](https://www.linkedin.com/in/thierry-marinho-81b899200/)`

---

## ⚖️ Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
