# Guia de Execução do Projeto Sinerji Challenge (JSF + PrimeFaces)
Este guia descreve os passos necessários para configurar e executar o projeto Sinerji Challenge, um aplicativo web usando JavaServer Faces (JSF) com PrimeFaces. O projeto utiliza o banco de dados MySQL para armazenamento de dados.

## Pré-requisitos
Antes de iniciar, certifique-se de ter instalado o seguinte em seu ambiente de desenvolvimento:

- Java Development Kit (JDK): Versão 8 ou superior
- Apache Maven: Para gerenciamento de dependências
- MySQL Server: Instalado e configurado localmente ou em um servidor remoto
- Git: Para clonar o repositório do projeto
# Configuração do Banco de Dados
## Criação do Banco de Dados:
- Abra o MySQL Workbench ou qualquer ferramenta de administração de banco de dados MySQL.
- Execute o seguinte comando SQL para criar o banco de dados:

> CREATE DATABASE sinerji-challenge-db;

## Configuração do Usuário e Senha:
- Certifique-se de que o usuário "root" do MySQL tenha acesso ao banco de dados.
- Caso não tenha configurado uma senha para o usuário "root", use o seguinte comando para definir a senha como "root":

- ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
- Substitua 'root' pela senha desejada.
## Clonando o Repositório
Abra um terminal e execute o seguinte comando para clonar o repositório do projeto:

git clone https://github.com/samuelvictorol/sinerji-challenge.git
## Instalando Dependências
Navegue até o diretório raiz do projeto clonado e execute o seguinte comando Maven para instalar as dependências do projeto:

- cd sinerji-challenge
- mvn clean install
- Configuração do Projeto
- Configuração do Banco de Dados:
- Abra o arquivo src/main/resources/META-INF/persistence.xml.
- Verifique e, se necessário, atualize as propriedades de conexão com o banco de dados:

<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/meu_banco"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="root"/>

Certifique-se de substituir sinerji-challenge-db, root e root pelos valores correspondentes ao seu banco de dados e credenciais.

## Executando o Projeto:
- Abra um terminal e navegue até o diretório do projeto.
- Adicione o projeto no servidor tomcat.
- Execute o seguinte comando Maven para iniciar o servidor web (por exemplo, Tomcat):
- mvn tomcat7:run
- O servidor será iniciado e o aplicativo estará acessível em http://localhost:8080/sinerji-challenge.
# O projeto

![image](https://github.com/samuelvictorol/Sinerji-Challenge/assets/95868897/8565b3ac-8acf-48e6-9260-2d25162c03d4)
![image](https://github.com/samuelvictorol/Sinerji-Challenge/assets/95868897/170a3b5e-ef69-4920-be24-7211457b01de)
