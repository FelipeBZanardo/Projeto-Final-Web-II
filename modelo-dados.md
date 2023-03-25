# Modelos de Dados
<br>

## Tabela de Apostas
<br>

id: bigint (chave primária)  
numero_sorteio: Long  
dezenas: varchar  
data_jogo: date  
data_sorteio: date  
pontuacao: integer  
valor_premio: numeric  
acumulou: boolean  
usuario_id: bigint (chave estrangeira)  
<br>

## Tabela de Usuários
<br>

id: bigint (chave primária)  
user_password: varchar  
user_name: varchar  
email: varchar  

<br>
