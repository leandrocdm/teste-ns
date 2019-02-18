Com as ParallelStreams é possível trabalhar com um conjunto de dados de forma paralela.
Significa dizer para o Java que todo o processamento deve ser feito e que esteja contido dentro de um ParallelStream, deve ser tratado de forma distribuída,
na prática o compilador abrirá mais de uma Thread, porém não de forma aleatória, existem no Java 8 regras implícita que se responsabilizando por fazer automaticamente 
um estudo da quantidade de Threads devem ser abertas levando em consideração dentre vários parâmetros, a capacidade da máquina e o tamanho do conjunto, para que seja distribuído 
na maior quantidade possível afim de se obter um resultado factível e de alta performance.
Comparação:
Stream/ParallelStream
Thread única dedicada de processamento / Threads selecionadas para processamento distribuído;
Recursos básicos do Java 8 / Recursos avançados do Java 8 para melhor distribuição possível por exemplo "Analise de Máquina";
Ideal para conjunto reduzido de dados / Ideal para grande de conjunto dados;
