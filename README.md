# Lista de Tarefas para o Aplicativo NFC Lock Thinks

### Link do Figma: 
https://www.figma.com/design/FGS59AEWwz0hDebFaCu7jK/Lock-Thinks-APP?node-id=5-31&t=h2UxV33NMNuWyINp-1

### Link do APK no Drive
https://drive.google.com/file/d/1qpNWJowZQuI55BlgYEV-x7vppG0UGkOO/view?usp=drive_link
### 1. Tela Inicial
- **Criar a tela inicial do aplicativo.**
  - [x] Implementar um botão na tela inicial chamado **"Atribuir chave ao cadeado"**.

### 2. Atribuição de Tag ao Cadeado
- **Implementar a funcionalidade de atribuição de uma tag NFC ao cadeado.**
  - [x] Quando o usuário clicar no botão **"Atribuir chave ao cadeado"**, o app deve permitir que uma tag NFC seja atribuída ao cadeado.
  - [x] Após a atribuição da tag NFC, exibir uma mensagem de sucesso: **"Chave atribuída com sucesso!"**.
  - [x] Exibir uma imagem ou ícone de um cadeado aberto após a atribuição bem-sucedida.

### 3. Botão de Fechar e Abrir o Cadeado
- **Adicionar botões para abrir e fechar o cadeado.**
  - [x] Implementar um botão para **"Fechar Cadeado"**.
  - [x] Implementar um botão para **"Abrir Cadeado"**.

### 4. Solicitação de Tag NFC para Abrir o Cadeado
- **Solicitar a tag NFC previamente cadastrada ao tentar abrir o cadeado.**
  - [x] Quando o usuário clicar no botão **"Abrir Cadeado"**, o app deve solicitar a tag NFC atribuída previamente para proceder com a abertura.

### 5. Validação e Abertura do Cadeado
- **Validar a tag NFC e realizar a abertura do cadeado.**
  - [x] Ao aproximar a tag NFC correta, o cadeado deve abrir e exibir uma mensagem de sucesso: **"Cadeado aberto com sucesso!"**.
  - [x] Se a tag NFC for incorreta, o app deve emitir um som de erro e exibir a mensagem **"Chave incorreta!"**.

### 6. Contador Regressivo para Abertura do Cadeado
- **Implementar um contador regressivo para a abertura do cadeado.**
  - [x] Adicionar um contador regressivo de 30 segundos após a solicitação de abertura do cadeado.
  - [x] Se o tempo se esgotar sem que a tag correta seja apresentada, cancelar a ação de abertura do cadeado.

## Estrutura da Arquitetura

### 1. MVVM (Model-View-ViewModel)
A arquitetura MVVM foi escolhida para separar claramente a lógica de apresentação (View) da lógica de negócios (Model) e da lógica de interação (ViewModel). Abaixo está uma breve explicação das responsabilidades de cada componente:

- **Model**: Representa a camada de dados, incluindo fontes locais (banco de dados) e remotas (APIs). Exemplo: `Repository`, `DataSource`.
  
- **View**: A interface do usuário, que observa o ViewModel e reflete as mudanças no estado da UI. Exemplo: `Activity`, `Fragment`.
  
- **ViewModel**: Intermediário que fornece dados da Model à View e contém a lógica de apresentação. Mantém o estado da UI e reage às interações do usuário.

### 2. Clean Code e SOLID
Para garantir que o código seja sustentável e fácil de manter, aplicamos os princípios de Clean Code e SOLID:

- **S**: **Single Responsibility Principle** - Cada classe tem uma única responsabilidade.
  
- **O**: **Open/Closed Principle** - O código está aberto para extensão, mas fechado para modificação.
  
- **L**: **Liskov Substitution Principle** - As subclasses devem ser substituíveis por suas superclasses.
  
- **I**: **Interface Segregation Principle** - Múltiplas interfaces específicas são melhores do que uma interface geral.
  
- **D**: **Dependency Inversion Principle** - Módulos de alto nível não devem depender de módulos de baixo nível, ambos devem depender de abstrações.

### 3. Injeção de Dependência com Dagger Hilt
Dagger Hilt foi utilizado para gerenciar a injeção de dependência, simplificando o fornecimento e a configuração de dependências dentro do aplicativo:

### 4. Leitura de NFC com Componentes Nativos
A integração de NFC foi realizada utilizando componentes nativos do Android:

- **NfcAdapter**: Para detectar a presença de dispositivos NFC.
  
- **PendingIntent** e **IntentFilter**: Para gerenciar as intenções associadas à leitura de tags NFC.

