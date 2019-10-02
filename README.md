# CTIS Hackathon

Projeto base Back-end para hackathon na fábrica de código de Campina Grande - PB.

## Getting Started

Para iniciar o desenvolvimento siga as instruções seguintes.

### Pré-requisitos

* [Github](https://github.com/)
* [Java SE Development Kit 8](https://www.oracle.com/)
* [Maven 3.x](https://maven.apache.org/)
* [Wildfly 14](https://wildfly.org/news/2018/08/30/WildFly14-Final-Released/)

### Instalação

Após instalar todos os pré-requisitos necessários, execute os próximos passos para conseguir disponibilizar a aplicação no seu ambiente local de desenvolvimento.

```sh
# Clone o repositório do projeto
$ git clone https://github.com/autscc/ctis-hackathon.git
# Navegue até a pasta root da aplicação
$ cd ctis-hackathon
# Instale todas as dependências
$ mvn clean install
```
## Executando a Aplicação

Importe o projeto em uma IDE de suas preferência.

> **importante:** Para o desenvolvimento do projeto base utilizei o Eclipse.

Agora build a aplicação na sua IDE.

```sh
# Maven goals
$  clean install
```

Após isso, instale os adapters necessários para configuração do novo servidor de aplicação Wildfly 14 em sua IDE. 

> **importante:** Para a IDE Eclipse, o JBoss Tools será necessário. Link para download: https://tools.jboss.org/downloads/

Agora, configure um novo servidor referenciado a pasta onde o Wildfly 14 foi baixado.

Acrescente a configuração de DataSource abaixo no arquivo **standalone.xml** do seu servidor.

```xml
<datasource jndi-name="java:jboss/datasources/HackatonDS" pool-name="HackatonDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:h2:mem:ctis;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;INIT=CREATE SCHEMA IF NOT EXISTS hackaton</connection-url>
    <driver>h2</driver>
    <security>
        <user-name>root</user-name>
        <password>root</password>
    </security>
</datasource>
```

> **importante:** O Widlfly 14 já vem com os módulos para o banco de dados H2 instalados por default.

Após concluir a configuração do servidor, coloque o **hackathon-ear** como o artefato que será feito o deploy e incie o server.

> **importante:** O contexto da aplicação é **/hackathon-backend/api/v1/**.

## Swagger UI

Para acessar o Swagger UI, abra o seguinte link no seu navegador: http://localhost:8080/hackathon-backend/doc/index.htm

## Constrúido com

* [Maven](https://maven.apache.org/) - Gerenciador de dependências

## Autor

* **Alysson Tiago S. Cordeiro** - *Arquiteto de Software*
