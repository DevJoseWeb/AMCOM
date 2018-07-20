AMCOM

[![Build Status](https://travis-ci.org/DevJoseWeb/AMCOM.svg?branch=master)](https://travis-ci.org/DevJoseWeb/AMCOM)


Requisitos
Ler o arquivo CSV das cidades para a base de dados;
Retornar somente as cidades que são capitais ordenadas por nome;
Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades;
Retornar a quantidade de cidades por estado;
Obter os dados da cidade informando o id do IBGE;
Retornar o nome das cidades baseado em um estado selecionado;
Permitir adicionar uma nova Cidade;
Permitir deletar uma cidade;
Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string;
Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais;
Retornar a quantidade de registros total;
Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta);
O código fonte deve ser comitado no GitHub, por favor nos passe sua conta para acompanharmos.


Solução
Leitura e manutenção de cidades de um arquivo CSV feito com Java


Versões
JAVA Wildfly-Swarm [COMPLETO] : https://github.com/DevJoseWeb/AMCOM/tree/master/amcom-systems-jboss -


Implementação
Nesse projeto utilizei wildfly-Swarm para executar minha aplicação JavaEE com banco relacional PostgreSQL. Funcionalidades:


Ler o arquivo CSV das cidades para a base de dados (/import-csv)
Retornar somente as cidades que são capitais ordenadas por nome (capitals)
Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades (/states-bigger-and-smaller-number-of-cities)
Retornar a quantidade de cidades por estado (/number-of-cities-by-state)
Obter os dados da cidade informando o id do IBGE (/find-city-by-ibge/{ibge})
Retornar o nome das cidades baseado em um estado selecionado (/find-cities-by-state/{state})
Permitir adicionar uma nova Cidade (POST)
Permitir deletar uma cidade (DELETE /{id})
Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string (/find-cities-by-csv-column/{column}/{filter})
Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais (/count-register-by-column/{column})
Retornar a quantidade de registros total (/count-all)
Instalação
mvn wildfly-swarm:run


Testes
JAVA SpringBoot : https://github.com/DevJoseWeb/AMCOM/tree/master/amcom-systems-springboot

JAVA Jersey : https://github.com/DevJoseWeb/AMCOM/tree/master/amcom-systems-Jersey

Go : https://github.com/DevJoseWeb/AMCOM/tree/master/amcom-systems-go

