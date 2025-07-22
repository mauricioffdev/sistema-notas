# 📚 Sistema de Notas - Boletim Escolar com MySQL e Java

Este projeto é um sistema simples de boletim escolar em Java com persistência de dados em banco de dados MySQL. Através de um menu interativo no terminal, é possível cadastrar, listar, 
editar, verificar e excluir alunos e suas notas.

## Funcionalidades

- Adicionar aluno com três notas
- Calcular média e indicar situação (Aprovado, Recuperação ou Reprovado)
- Listar todos os alunos cadastrados
- Editar dados e notas de um aluno
- Excluir aluno da lista
- Persistir dados no banco MySQL com JDBC

## Tecnologias usadas

- Java (JDK 8+)
- JDBC
- MySQL
- IntelliJ IDEA 


## Estrutura da Tabela MySQL
sql  
CREATE DATABASE IF NOT EXISTS sistema_notas;
USE sistema_notas;

CREATE TABLE IF NOT EXISTS alunos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  nota1 DECIMAL(5,2),
  nota2 DECIMAL(5,2),
  nota3 DECIMAL(5,2)
);

## Como executar  
Clone o repositório:

git clone https://github.com/mauricioffdev/sistema-notas.git
Configure a URL, usuário e senha do MySQL na classe AlunoDAO.

Compile e execute a classe Main.java

Acompanhe os dados inseridos no terminal ou diretamente no banco com:

sql  
SELECT * FROM alunos;

## Melhorias futuras  
Listar alunos diretamente do banco  
Atualizar e excluir registros via MySQL  
Interface gráfica com JavaFX ou Swing  
Exportar boletins em PDF ou CSV  
Autenticação de usuários

## Autor
Desenvolvido por mauricioffdev


