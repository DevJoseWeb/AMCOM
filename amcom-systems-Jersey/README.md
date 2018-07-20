### Build

```Shell Session
$ mvn clean compile install
```

### Execução do projeto

```Shell Session
$ mvn tomee:run
```

## Respostas das questões do desafio:
Para refazer a importação, foi escrita a classe 'Init'.

Segue abaixo uma lista de url's que exemplificam o serviço. A mesma lista encontra-se
em http://localhost:8080/ e nesse endereço será mais fácil testar no navegador.

1.  Ao iniciar o projeto, o mesmo carrega os dados para a base de dados.
2.  http://localhost:8080/rest/cidades/capitais/
3.  http://localhost:8080/rest/cidades/estado_maior_menor/
4.  http://localhost:8080/rest/cidades/estados/
5.  http://localhost:8080/rest/cidades/cidade/2800308
6.  http://localhost:8080/rest/cidades/MG/
7.  http://localhost:8080/rest/cidades/cadastrar/ibge_id=5300110,uf=DF,name=Brasília Alternativa,capital=true,lon=1,lat=2,no_accents=Brasilia Alternativa,alternative_names=Alternativa,microregion=proria,mesoregion=propria
8.  http://localhost:8080/rest/cidades/remove/1100023
9.  http://localhost:8080/rest/cidades/filtro/ibge_id=3100104
    http://localhost:8080/rest/cidades/filtro/uf=MG
    http://localhost:8080/rest/cidades/filtro/lat=-22.199764
    http://localhost:8080/rest/cidades/filtro/name=Alta Floresta D'Oeste
    obs: conforme solicitado, foram usados os nomes das colunas do CSV.
    Coloquei aqui várias URL's para demonstrar mais claramente as opções.
10. http://localhost:8080/rest/cidades/quantidade/uf=MG
    (utilizar parâmetros do exemplo 9 funcionará no 10 também)
11. http://localhost:8080/rest/cidades/quantidade/
12. http://localhost:8080/rest/cidades/mais_distantes/4305439

## Tecnologias utilizadas

* Apache Derby 
* JPA (Hibernate como implementador)
* CDI (implementado pelo Apache Weld)
* Apache Commons CSV
* Maven
* jersey-server
* jersey-json

