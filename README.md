# Bolsa de Valores BOVESPA 1994-2020

## Instruções de Execução

1. Baixe o arquivo CSV [Aqui](https://drive.google.com/file/d/112vpfihBAFcPSfFW0MQ2UqOrUsn561t4/view?usp=sharing)
2. Coloque-o na pasta filesCsv
3. Execute o Main.java
4. Após a execução **(Vai demorar)**, os arquivos estarão na pasta **setdata**.

## Observações

- Qualque problema de path, alterar o método `TransformDataCsv.PathJoin`
- O **Couting Sort** é um algoritmo que usa a frequência de elementos para fazer a ordenção, sendo assim limitado para uso de campos inteiros, com base nisso não é possivel fazer a ordenação para o caso "Tricker" que é um campo **String**.
- Na ordenação dos arquivos, pode existir valores iguais, então alguns arquivos podem estar em ordem diferente de outros.
- Não é possível ordenar o campo de volume usando o cunting sort deivo a seus valores serem muito altos (A JVM não deixar criar o array) mas o algoritmo foi implementado. 

## Justificativas das Estrutura de Dados

### 1. Tabela Hash

- Arquivo: Cases/TransformDataCsv - [filterHighestVolumePerDay]

### Motivo: 
- Fazer uma filtragem para que fique apenas um registro por dia. Esse registro deve ser apenas quele que possuir o maior volume negociado em bolsa.

### Lógica
1. **Leitura de Linhas:**
O loop while ((line = reader.readLine()) != null) lê cada linha do arquivo CSV.

2. **Extração da Data**:
A variável date recebe o valor da primeira parte (índice 0) do array parts.
Essa parte representa a data no formato YYYY-MM-DD.

3. **Extração do Volume**:
A variável volume é obtida convertendo a sexta parte (índice 6) do array parts para um valor numérico (double).
Essa parte representa o volume negociado em bolsa.

4. **Atualização do Mapa**:
O bloco condicional if (!highestVolumePerDay.containsKey(date) || volume > Double.parseDouble(highestVolumePerDay.get(date)[6])) verifica se a data já está no mapa ou se o volume atual é maior do que o volume previamente registrado.
Se uma dessas condições for verdadeira, o registro é atualizado no mapa com a nova data e os dados correspondentes.

---

### 2. Queue

- Arquivo: Cases/TransformDataCsv - [filterAboveAverageVolume]

### Motivo: Processar dados de um arquivo CSV

1. A Queue crescem dinamicamente conforme necessário,
2. Não precisa definir um tamanho fixo ao invês do array.
3Os arrays as vezes precisam fazer cópias para outro array caso precisa aumentar de tamanho.
3. Essêncial pois não sabemos o tamanho do arquivo CSV
4. Semântica de FIFO (First In, First Out) para preservar a ordem.

### 3. ArraysList (LinkedList)

- Arquivo: Main

### Motivo: Armazear valores sem precisar definir valores fixos, exemplo uma lista de algoritmos.

