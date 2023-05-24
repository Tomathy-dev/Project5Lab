# Project5Lab
Huffman implementation for Lab_Prog

O método público encode
  codifica a string passada ao primeiro parâmetro usando os códigos passados ao
  segundo parâmetro; devolve uma string apenas de 0s e 1s, excepto se
  encontrar um carácter na string argumento para o qual não existe código;
  nesse caso, assinala a situação excepcional inserindo um hífen na string
  resultado;
O método privado frequencyTable
  devolve a tabela de frequências absolutas de ocorrência de cada carácter no
  corpus passado como argumento;
O construtor privado HuffmanTree
  cria uma árvore de Huffman a partir de uma tabela de frequências;
 para cada carácter na tabela, é criado um objecto HuffmanNode*
, o qual é
adicionado a uma PriorityQueue; note que os HuffmanNodes ficam ordenados
por frequência, com as frequências menores na frente da fila;
 depois, enquanto houver pelo menos dois HuffmanNodes na fila,
 extrai-se os dois nós com a menor frequência;
 cria-se um novo nó (que é interno) com a soma das duas frequências
 e cujos filhos esquerdo e direito são os dois nós retirados, sendo o filho
esquerdo o nó retirado que tem a menor frequência;
 o novo nó pai é acrescentado à fila;
 quando já só houver 1 nó restante na fila, esse nó é o nó raiz, ou seja, o nó
usado para aceder à arvore que acabou de ser criada; é retirado da fila e é
atribuído ao atributo root da classe HuffmanTree;
 o método privado auxiliar getHuffmanCodesAux
 deve ser implementado de forma recursiva;
 a partir do nó raiz, faz uma travessia completa da árvore, gerando códigos de
Huffman (strings de 0s e 1s) por meio dessa travessia;
 quando aplicado a um nó interno, que foi alcançado através de um caminho a
partir da raiz†
, ao longo do qual foi gerada uma string (um código parcial) de 0s
e 1s, o método aplica-se recursivamente
 ao filho esquerdo, acrescentando um 0 à string gerada,
 e ao filho direito, acrescentando um 1 à string gerada;
 quando aplicado a um nó externo (uma folha), o método coloca no HashMap
codes o código de Huffman correspondente ao carácter que está guardado
nessa folha, ou seja, coloca em codes a sequência de 0s e 1s gerada no
caminho desde a raiz até essa folha, sem acrescentar nenhum 0 ou 1;
 após a inserção em codes do código gerado até à folha, o método retorna pois
não há mais processamento a efectuar abaixo dessa folha.
