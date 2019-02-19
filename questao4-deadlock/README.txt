DeadLock é um encadeamento fechado de threads um status de blocked, consequentemente interrompendo a operação do sistema:
	Por exemplo em um carrinho de compras de um e-commerce, em um cenário em que hajam mais de uma transação ao mesmo tempo,a compra de um mesmo produto que é o último do estoque por dois usuários diferentes, na prática uma requisição sempre vai chegar primeiro do que outra, porém dependendo do tempo de resposta ocasionalmente do sistema, podemos cair em um caso de encadeamento de dependência cíclica de threads.
	Podemos resolver um deadlock de algumas formas:
- Análises e monitorias de threads em “Waiting” para localizar onde está o encadeamento.
- Dependendo do caso correção do código para obedecer uma ordem de prioridades, sempre levando em consideração o registro mais antigos, 
invalidando novas solicitações até a finalização do primeiro processamento, garantindo o trabalho sincronizado das requisições.
