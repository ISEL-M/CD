Este documento serve para explicar alguns pressupostos assumidos na execução deste projeto, bem como os passos necessários para por o programa em execução.

Assumiu-se que
1º Tanto o servidor RingManager como os servidores de ChatServer nunca falham
2º O porto e ip do RingManager é conhecido pelos ChatServers, sendo o porto = 9000
3º O porto do ChatServer é 8000, por default, pode ser alterado ao iniciar o server.
4º Caso se esteja a correr os servidores localmente os ports dos servidores não poderão ser iguais ou consecutivos (ie.Chatserver no porto 8000 e outro Chatserver no porto 8001),
uma vez que cada servidor (RingManager e ChatServer) implementa dois contratos diferentes, que precisam de canais diferentes, os portos utilizados por cada servidor vai
ser o porto_inicial e porto_inicial+1 (ie. 8000 para um contrato e 8001 para o outro)
5º O load balance envia o cliente sempre para o próximo servidor independentemente da carga, actual, de cada servidor
6º Os clientes obtêm o ip do server ao qual se vai conectar a partir do Ring Manager.
7º Os ChatServes apenas podem começar a atender pedidos de clientes depois de todos os ChatServers terem-se conectado ao RingManager


Para por o código em execução será necessário
1º Executar o RingManager fornecendo o número de ChatServers que se irão ligar (por default serão 3)
2º Executar os ChatServers fornecendo o Port (por default é o 8000)
3º Executar o Client
4º Fornecer comandos através da consola ( ver clientes registados, Registar, mandar mensagem)
