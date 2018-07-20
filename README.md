# AMCOM
A empresa XPTO Systems precisa construir um sistema web para leitura e manutenção de uma
lista de cidades que hoje está em um arquivo CSV.
Como a empresa está antenada com as novas tecnologias ela precisa de um sistema web, pois
os usuários irão acessar os dados do sistema via smartphone.
A empresa contratou uma agência e a agência decidiu separar o trabalho em duas partes: uma
equipe irá fazer a interface e equipe a integração com a base de dados.
Para a integração com a base de dados foi sugerida a criação de uma API rest para fazer o
controle das cidades cadastradas. Todos o tráfego de dados do serviço deve ser feito no
formato JSON.
Foi definido que a API terá serviços para atender os seguintes requisitos:
1. Ler o arquivo CSV das cidades para a base de dados;
2. Retornar somente as cidades que são capitais ordenadas por nome;
3. Retornar o nome do estado com a maior e menor quantidade de cidades e a
quantidade de cidades;
4. Retornar a quantidade de cidades por estado;
5. Obter os dados da cidade informando o id do IBGE;
6. Retornar o nome das cidades baseado em um estado selecionado;
7. Permitir adicionar uma nova Cidade;
8. Permitir deletar uma cidade;
9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para
filtrar. retornar assim todos os objetos que contenham tal string;
10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens
iguais;
11. Retornar a quantidade de registros total;
12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base
na localização (distância em KM em linha reta);

O código fonte deve ser comitado no GitHub, por favor nos passe sua conta para
acompanharmos.

# Solução

Leitura e manutenção de cidades de um arquivo CSV feito com Java

# Versões

JAVA Wildfly-Swarm [COMPLETO] : https://github.com/DevJoseWeb/AMCOM/tree/master/amcom-systems-jboss -

JAVA SpringBoot : https://github.com/DevJoseWeb/AMCOM/tree/master/amcom-systems-springboot

Go : https://github.com/DevJoseWeb/AMCOM/tree/master/amcom-systems-go


# Implementação

Nesse projeto utilizei wildfly-swarm para executar minha aplicação JavaEE com banco relacional PostgreSQL disponibilizado na RDS e criado uma imagem Docker com a aplicação para deploy via Elastic Beanstalk na AWS
**Funcionalidades**:
* Ler o arquivo CSV das cidades para a base de dados **(/import-csv)**
* Retornar somente as cidades que são capitais ordenadas por nome **(capitals)**
* Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades **(/states-bigger-and-smaller-number-of-cities)**
* Retornar a quantidade de cidades por estado **(/number-of-cities-by-state)**
* Obter os dados da cidade informando o id do IBGE **(/find-city-by-ibge/{ibge})**
* Retornar o nome das cidades baseado em um estado selecionado **(/find-cities-by-state/{state})**
* Permitir adicionar uma nova Cidade **(POST)**
* Permitir deletar uma cidade **(DELETE /{id})**
* Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string **(/find-cities-by-csv-column/{column}/{filter})**
* Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais **(/count-register-by-column/{column})**
* Retornar a quantidade de registros total **(/count-all)**

# Instalação

Apenas execute
```
mvn wildfly-swarm:run
``` 
na raiz do projeto e tudo estará funcionando
