/* Modelagem básica * - ENTIDADE=TABELA 
CAMPOS = ATRIBUTOS*/

CLIENTE

NOME - CARACTER(30)
CPF - NUMERICO(11)
EMAIL - CARACTER(30)
TELEFONE - CARACTER(30)
ENDERECO - CARACTER(100)
SEXO - CARACTER(1)

/* PROCESSOS DE MODELAGEM */

/*FASE 01 E FASE 02 - AD (ADMINISTRADOR DE DADOS) */
MODELAGEM CONCEITUAL - RASCUNHO, NECESSIDADES
MODELAGEM LÓGICA - QUALQUER PROGRAMA DE MODELAGEM

/*FASE 03 - DBA/AD */
MODELAGEM FÍSICA - SCRIPTS DE BANCO

/* INICIANDO A MODELAGEM FÍSICA*/

/* CRIANDO O BD*/

CREATE DATABASE EXEMPLO;

CREATE DATABASE PROJETO;
/* CONECTANDO- SE AO BD*/
USE PROJETO;

/* CRIANDO A TABELA DE CLIENTES*/
CREATE TABLE CLIENTE(
	NOME VARCHAR(30),
	SEXO CHAR(1),
	EMAIL VARCHAR(30),
	CPF INT(11),
	TELEFONE VARCHAR(30),
	ENDERECO VARCHAR(100)
);

/*VERIFICANDO AS TABELAS DO BANCO*/
SHOW TABLES;
SHOW DATABASES;

/*DESCOBRINDO A ESTRUTURA DA TABELA */

DESC CLIENTE;

/*sintaxe basica para inserção - INSERT INTO TABELA */

/*FORMA 01 - OMITINDO AS COLUNAS*/

INSERT INTO CLIENTE VALUES ('JOAO','M','JOAO@GMAIL.COM',988638273,'22923110','MAIA LACERDA -ESTACIO - RIO DE JANEIRO - RJ');
INSERT INTO CLIENTE VALUES ('CELIA','F','CELIA@GMAIL.COM',541521456,'25078869','RIACHUELO - CENTRO - RIO DE JANEIRO - RJ');
INSERT INTO CLIENTE VALUES ('JORGE','M','JORGE@GMAIL.COM',885755896,'58748895','OSCAR CURY - BOM RETIRO - TIJUCA - MG');

INSERT INTO CLIENTE(NOME,SEXO,ENDERECO,TELEFONE,CPF) VALUES('LILIAN','F','SENADOR DA SILVA','88885555',976543210);

INSERT INTO CLIENTE VALUES ('MARIA','F','MARIA@GMAIL.COM',123456789,'159852369','PEIXE- MG'),
('JOANA','F','JOANA@GMAIL.COM',159852344,'159656565','CARAMUJO - RR');

/*COMANDO SELECT
seleção projeção junção*/

SELECT NOW() as DATA_HORA, 'LUCAS BAUCHSPIESS' as ALUNO;
SELECT 'LUCAS BAUCHSPIESS';
SELECT 'BANCO DE DADOS';

/*ALIAS DE COLUNAS*/

SELECT NOME as NOMECITO, SEXO as 'M/F', EMAIL as 'contato', ENDERECO as RUA, now() as hora FROM CLIENTE;

SELECT NOME, ENDERECO, SEXO AS 'M/F' FROM CLIENTE
WHERE ENDERECO LIKE '%RIO DE JANEIRO%';

/*CARACTER CORINGA % -> QUALQUER COISA*/


/*LIVRARIA*/
CREATE TABLE LIVROS(
	LIVRO VARCHAR(80),
	PAGINAS INT(5),
	EDITORA VARCHAR(50),
	AUTOR VARCHAR(100),
	SEXO CHAR(1),
	UF_EDITORA CHAR(2),
	VALOR FLOAT(5,2),
	ANO INT(4)
);

INSERT INTO LIVROS(LIVRO, AUTOR, SEXO, PAGINAS, EDITORA, VALOR, UF_EDITORA, ANO)
VALUES
('SQL para leigos','João Nunes','M',450,'Addison',98,'SP',2018),
('Receitas Caseiras','Celia Tavares','F',210,'Atlas',45,'RJ',2008),
('Pessoas Efetivas','Eduardo Santos','M',390,'Beta',78.99,'RJ',2018),
('Habitos Saudáveis','Eduardo Santos','M',630,'Beta',150.98,'RJ',2019),
('A Casa Marrom','Hermes Macedo','M',250,'Bubba',60,'MG',2016),
('Estacio Querido','Geraldo Francisco','M',310,'Insignia',100,'ES',2015),
('Pra sempre amigas','Leda Silva','F',510,'Insignia',78.98,'ES',2011),
('Copas Inesqueciveis','Marco Alcantara','M',200,'Larson',130.98,'RS',2018),
('O poder da mente','Clara Mafra','F',120,'Continental',56.58,'SP',2017);

SELECT * FROM LIVROS
WHERE SEXO ='M' AND
(UF_EDITORA = 'SP' OR UF_EDITORA = 'RJ');

DELETE FROM CLIENTE
WHERE EMAIL = 'JORGE@GMAIL.COM';

SELECT SEXO, COUNT(*) FROM CLIENTE
GROUP BY SEXO;


drop TABLE funcionarios;


create table funcionarios
  (
      idFuncionario integer,
      nome varchar(100),
      email varchar(200),
      sexo varchar(10),
      departamento varchar(100),
      admissao varchar(10),
      salario integer,
      cargo varchar(100),
      idRegiao int,
      primary key (idFuncionario)
  );

select departamento, count(*) from funcionarios
group by departamento
order by 1;

/*INVERTE A ORDEM DAS COLUNAS*/
select count(*), departamento from funcionarios 
group by departamento
order by 1;

select count(*), sexo from funcionarios
group by sexo
order by 1;

SELECT nome as 'funcionário',departamento, sexo FROM funcionarios
where departamento = 'roupas' or departamento = 'filmes';

SELECT nome as 'funcionária', sexo, departamento, email FROM funcionarios
where 
(departamento = 'filmes' and sexo = 'feminino') or
(departamento = 'lar' and sexo = 'feminino'); 


SELECT nome, sexo, departamento FROM funcionarios
where 
sexo = 'masculino' or
departamento = 'jardim';


SELECT * FROM cliente
where email IS NULL;

SELECT * FROM cliente
where email IS NOT NULL;


drop TABLE funcionarios;
drop DATABASE funcionario;


INSERT INTO CLIENTE VALUES ('JORGE','M',NULL,885755896,'58748895','OSCAR CURY - BOM RETIRO - TIJUCA - MG');

UPDATE CLIENTE
SET TELEFONE = 'LILIAN@HOTMAIL.COM'
WHERE NOME = 'LILIAN' 
AND EMAIL IS NULL;

SELECT 10-8 AS 'SUBTRAÇÃO'; 


/* 1. PRIMEIRA FORMA NORMAL

1.1 TODO CAMPO VETORIZADO SE TORNARÁ OUTRA TABELA.
OBS: Vetor = agrupamento de elementos da mesma família (coerentes).
Exemplos de vetor:
a) [verde, azul, rosa, roxo] - vetor de COR
b) [PR, SC, SP, RS] - vetor de UD

1.2 TODO CAMPO MULTIVALORADO SE TORNARÁ OUTRA TABELA, OU QUANDO O CAMPO FOR DIVISÍVEL.
Exemplos de Campo multivalorado:
a) Rua das Catapimbas, bairro de tal, cidade das cidades, estado misterioso
São complementares, não da mesma família.

1.3 TODA TABELA NECESSITA DE PELO MENOS UM CAMPO QUE IDENTIFIQUE TODO O REGISTRO COMO SENDO ÚNICO.
PARA ISSO, COMUMENTE É CRIADA UMA CHAVE ARTIFICIAL (CHAVE PRIMÁRIA) ATRIBUIR UMA ID PARA O CLIENTE
Exemplo:
a) É criada uma CP, com código alfanumérico para cada cliente.

*/

/* OBRIGATORIEDADE E CARDINALIDADE*/
/*- (X,Y) - 
X representa obrigatoriedade (0 para false, 1 para true)
Y segundo representa a cardinalidade (qual o limite de itens)
POSSÍVEIS CASOS:
(0,1)
(0,n)
(1,1)
(1,n)

Possui dos 2 lados: verificar quantos são necessários de cada parte para que possa existir.*/

CREATE DATABASE COMERCIO;
USE COMERCIO;
CREATE TABLE CLIENTE(
	IDCLIENTE INT PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(30) NOT NULL,
	SEXO ENUM('M','F') NOT NULL,
	EMAIL VARCHAR(50) UNIQUE,
	CPF VARCHAR(15) UNIQUE
);

CREATE TABLE ENDERECO(
	IDENDERECO INT PRIMARY KEY AUTO_INCREMENT,
	RUA VARCHAR(30) NOT NULL,
	BAIRRO VARCHAR(30) NOT NULL,
	CIDADE VARCHAR(30) NOT NULL,
	ESTADO CHAR(2) NOT NULL,
	ID_CLIENTE INT UNIQUE,
	FOREIGN KEY(ID_CLIENTE)
	REFERENCES CLIENTE(IDCLIENTE) 
);

CREATE TABLE TELEFONE(
	IDTELEFONE INT PRIMARY KEY AUTO_INCREMENT,
	TIPO ENUM('RES','COM','CEL') NOT NULL,
	NUMERO VARCHAR(15) NOT NULL,
	ID_CLIENTE INT,
	FOREIGN KEY(ID_CLIENTE)
	REFERENCES CLIENTE(IDCLIENTE)
);

/*CHAVE ESTRANGEIRA É A CHAVE PRIMÁRIA DE UMA TABELA QUE VAI ATÉ A OUTRA TABELA
PARA FAZER REDERÊNCIA ENTRE REGISTROS*/

/*EM RELACIONAMENTO 1 x 1 (CARDINALIDADES), A CHAVE ESTRANGEIRA FICA NA TABELA MAIS FRACA*/

/*EM RELACIONAMENTOS 1 x N A FK (FOREIGN KEY) FICARÁ SEMPRE NA CHAVE N*/

insert into cliente VALUES(Null,'JOAO','M','JOAO@gmail.com','7656787457752');
insert into cliente VALUES(Null,'CARLOS','M','CARLOS@gmail.com','56897412');
insert into cliente VALUES(Null,'ANA','F','ANA@gmail.com','9876543210');
insert into cliente VALUES(Null,'CLARA','F',NULL,'0123456789');
insert into cliente VALUES(Null,'CELIA','F','CELIA@gmail.com','555866554');
insert into cliente VALUES(Null,'JORGE','M','JORGE@gmail.com','957865414');

insert into ENDERECO VALUES(Null,'RUA DAS CATAPIMBAS','IT NORTE','BLUMENAU','SC',4);
insert into ENDERECO VALUES(Null,'RUA DAS PÉROLAS','IT CENTRAL','BLUMENAU','RO',5);
insert into ENDERECO VALUES(Null,'RUA DAS ARAUCÁRIAS','BELA VISTA','GASPAR','RS',6);
insert into ENDERECO VALUES(Null,'RUA DAS COUVES','BOM RETIRO','JOINVILLE','PR',1);
insert into ENDERECO VALUES(Null,'RUA DAS RUAS','CEDRO ALTO','ITAPUERA','SP',3);
insert into ENDERECO VALUES(Null,'RUA DAS PLANICIES','RIBEIRÃO SECO','ITAJAÍ','BA',2);

insert into TELEFONE VALUES(Null,'CEL','47988546525',5);
insert into TELEFONE VALUES(Null,'RES','5789845631',5);
insert into TELEFONE VALUES(Null,'COM','4985632142',5);
insert into TELEFONE VALUES(Null,'CEL','9998744562',1);
insert into TELEFONE VALUES(Null,'RES','9874158411',3);
insert into TELEFONE VALUES(Null,'CEL','5987566218',4);
insert into TELEFONE VALUES(Null,'COM','44174798126',6);
insert into TELEFONE VALUES(Null,'RES','9998744562',1);

/*SELEÇÃO, PROJEÇÃO E JUNÇÃO*/
/*PROJEÇÃO -> TUDO QUE SERÁ VISTO NA TELA*/
/*SELEÇÃO É UM SUBCONJUNTO DE UMA TABELA, UM FILTRO - CLÁUSULA DE SELEÇÃO É WHERE*/
insert into TELEFONE VALUES(Null,'CEL','47988546525',5);

DELETE FROM TELEFONE
WHERE IDTELEFONE = 9;

SELECT NOME, SEXO, EMAIL /*PROJEÇÃO*/
FROM CLIENTE /*ORIGEM*/
WHERE SEXO ='F'; /*SELEÇÃO*/

/*JUNÇÃO: vincula as tabelas - comando Inner Join*/

select nome, sexo, bairro, cidade /*PROJEÇÃO*/
from cliente /*ORIGEM*/
	inner join endereco /*JUNÇÃO*/
	on idcliente = id_cliente 
where sexo = 'F'; /*SELEÇÃO*/

select nome, sexo, email, tipo, numero /*PROJEÇÃO*/
from cliente /*ORIGEM*/
	inner join telefone /*JUNÇÃO*/
	on idcliente = id_cliente 
where sexo = 'F'; /*SELEÇÃO*/


/*JUNÇÃO DE MAIS DE DUAS TABELAS*/

/*NOME, SEXO, BAIRRO, CIDADE, TIPO, NUMERO*/
select cliente.nome, cliente.sexo, endereco.bairro, endereco.cidade, telefone.tipo, telefone.numero
from cliente
	inner join telefone
	on cliente.idcliente = telefone.id_cliente 
	inner join endereco /*JUNÇÃO*/
	on cliente.idcliente = endereco.id_cliente
	WHERE SEXO ='F';

select CLIENTE.nome, CLIENTE.sexo, E.bairro, E.cidade, T.tipo, T.numero
from cliente C /*RENOMEIA A TABELA CLIENTE COMO "C" */
	inner join telefone T /*RENOMEIA A TABELA TELEFONE COMO "T" */
	on CLIENTE.idcliente = T.id_cliente 
	inner join endereco E /*RENOMEIA A TABELA ENDEREÇO COMO "E" */
	on CLIENTE.idcliente = E.id_cliente;


/*
	DML- DATA MANIPULATION LANGUAGE - altera os dados 
	insert; select, delete, update; 
*/


/*	DDL - DATA DEFINITION LANGUAGE - tipagem de dados
	create; alter; change; modify; add
*/

create table PRODUTO(
	IDPRODUTO INT PRIMARY KEY AUTO_INCREMENT,
	NOME_PRODUTO VARCHAR(30) NOT NULL,
	PESO FLOAT(10,2) NOT NULL,
	PRECO INT NOT NULL,
	FRETE FLOAT(10,2) NOT NULL
);

/* ALTER TABLE - chance/alter table */ 

ALTER TABLE produtos /*- define a tabela a ser alterada*/
CHANGE PRECO VALOR_UNITARIO INT NOT NULL;
/*Altera o nome de preço para valor_unitário  e define como sendo não nulo*/

alter table produtos
modify valor_unitario varchar(50) NOT NULL;

ALTER TABLE produtos /*- define a tabela a ser alterada*/
CHANGE VALOR_UNITARIO VALOR_UNITARIO INT NOT NULL;

alter table produtos
ADD PESO PESO FLOAT(10,2) NOT NULL;

ALTER TABLE produtos
DROP COLUMN PESO;


/*ADICIONAR COLUNA NA ORDEM ESPECÍFICA*/
ALTER TABLE produtos
ADD COLUMN PESO FLOAT(10,2) NOT NULL
AFTER NOME_PRODUTO;

ALTER TABLE produtos
ADD COLUMN PESO FLOAT(10,2) NOT NULL
FIRST;


/*
	DCL - DATA CONTROL LANGUAGE - controle de acesso aos dados


	TCL - TRANSACTION CONTROL LANGUAGE - controle de transação
*/

select C.IDCLIENTE, C.nome, C.sexo, C.EMAIL,C.CPF, E.rua, E.bairro, E.cidade, E.estado, T.tipo, T.numero
from cliente C
	inner join telefone T
	on C.idcliente = T.id_cliente 
	inner join endereco E
	on C.idcliente = E.id_cliente
WHERE SEXO ='M';


SELECT *FROM CLIENTE
WHERE IDCLIENTE IN (11,12,16,17);

SELECT SEXO, COUNT(*) AS QUANTIDADE FROM CLIENTE
GROUP BY SEXO;

UPDATE CLIENTE SET SEXO = 'F'
WHERE IDCLIENTE IN (11,12,16,17);

select C.IDCLIENTE, C.nome, C.EMAIL, C.sexo, T.tipo, E.bairro, E.cidade
from cliente C
	inner join telefone T
	on C.idcliente = T.id_cliente 
	inner join endereco E
	on C.idcliente = E.id_cliente
WHERE SEXO ='F' AND BAIRRO = 'CENTRO' AND CIDADE = 'RIO DE JANEIRO'
AND (TIPO = 'RES' OR TIPO = 'COM');


select C.IDCLIENTE, C.nome, C.EMAIL, C.sexo, T.tipo, E.bairro, E.cidade
from cliente C
	inner join telefone T
	on C.idcliente = T.id_cliente 
	inner join endereco E
	on C.idcliente = E.id_cliente
WHERE SEXO ='F' AND BAIRRO = 'CENTRO' AND CIDADE = 'RIO DE JANEIRO'
AND TIPO IN('RES','COM');


select 	C.nome,
		IFNULL(C.EMAIL,'NÃO INFORMADO') AS 'EMAIL',
		E.ESTADO,
		T.NUMERO
from cliente C
	inner join telefone T
	on C.idcliente = T.id_cliente 
	inner join endereco E
	on C.idcliente = E.id_cliente;

CREATE VIEW V_RELATORIO AS
select 	C.nome,
		C.sexo, 
		IFNULL(C.EMAIL,'NÃO INFORMADO') AS 'EMAIL',
		T.tipo,
		T.NUMERO,
		E.bairro, 
		E.cidade,
		E.ESTADO
from cliente C
	inner join telefone T
	on C.idcliente = T.id_cliente 
	inner join endereco E
	on C.idcliente = E.id_cliente;

SELECT * FROM RELATORIO;
/*SHOW TABLES -> IRÁ MOSTRAR OS RELATÓRIOS*/
DROP VIEW RELATORIO;

SELECT NOME, NUMERO, ESTADO FROM V_RELATORIO;
DESC V_RELATORIO;

/*Não é possível fazer insert ou delete em views que possuem join,
MAS É POSSÍVEL USAR UPDATE*/
/*Entretanto, é possível utilizar os 3 comandos caso a view não possua join
deve-se cuidar com as restrições ao inserir/remover */
SELECT * FROM CLIENTE
ORDER BY SEXO;

SELECT * FROM CLIENTE
ORDER BY 3;

SELECT * FROM CLIENTE
ORDER BY SEXO, 2;

UPDATE CLIENTE SET SEXO = 'M'
WHERE IDCLIENTE = 14;

SELECT SEXO,NOME,CPF FROM CLIENTE
ORDER BY 1, NOME;

SELECT NOME FROM CLIENTE
ORDER BY 1, 2; /*RESULTA EM ERRO, POIS NÃO POSSUI COLUNA 2*/

SELECT NOME FROM CLIENTE
ORDER BY SEXO, NOME; /*VAI MOSTRAR O NOME, POIS CONSEGUE IDENTIFICAR*/ 

SELECT SEXO,NOME,CPF FROM CLIENTE
ORDER BY 1 DESC, 2;

SELECT SEXO,NOME,CPF FROM CLIENTE
ORDER BY NOME DESC, 2 DESC; 

SELECT * FROM V_RELATORIO
ORDER BY 2 DESC, 1;


DELIMITER $ 
/*Altera o delimitador. Padrão é ';' - Necessário o espaço, mas os espaços
são desconsiderados para o delimitador
IMPORTANTE: UTILIZAR SOMENTE 1 CARACTER*/
DELIMITER ;

/* STORED PROCEDURES - PROCEDIMENTOS ARMAZENADOS*/

DELIMITER $

STATUS

CREATE PROCEDURE NOME_EMPRESA() /*PROCEDIMENTO SEM PARÂMETRO*/
BEGIN

	 SELECT 'NOME' AS EMPRESA;
	
END

$

DELIMITER ;

DROP PROCEDURE CONTA;

/*CHAMANDO UMA PROCEDURE*/
/* TAMBÉM PODE SER FEITO SEM ALTERAR O DELIMITER*/
CALL NOME_EMPRESA();
SHOW TABLES;


DELIMITER $
CREATE PROCEDURE CONTA(NUMERO1 INT, NUMERO2 INT) /*PROCEDIMENTO COM PARÂMETROS*/
BEGIN

	 SELECT NUMERO1 + NUMERO2 AS SOMA;
	
END
$
DELIMITER ;

create DATABASE projeto;

create table cursos(
	IDcurso int primary KEY AUTO_INCREMENT,
	nome varchar(30) not null,
	horas int(3) not null,
	valor FLOAT(10,2) not null

);

insert into cursos values(Null,'JAVA',30,500.00);
insert into cursoS values(Null,'FUNDAMENTOS DE BANCO DE DADOS',40,700.00);

DELIMITER #

status

create PROCEDURE CAD_CURSO(P_NOME VARCHAR(30),
						P_HORAS INT(3),
						P_PRECO FLOAT(10,2))

/*Os parâmetros informados devem ser coerentes com os parâmetros da tabela*/


BEGIN

	insert into CURSOS values(NULL,P_NOME,P_HORAS,P_PRECO);
	/*Parâmetros da procedure - utilizar P_ (por exemplo, para facilitar)*/
END
#

DELIMITER ;

CALL CAD_CURSO ('BI SQL SERVER',35,3000.00);
CALL CAD_CURSO ('POWER BI',20,1000.00);
CALL CAD_CURSO ('TABLEAU',30,1200.00);

DELETE FROM CURSOS
WHERE NOME = 'TABELEAU';



DELIMITER #

create PROCEDURE LER_CURSO(P_IDCURSO INT(6))

/*Os parâmetros informados devem ser coerentes com os parâmetros da tabela*/


BEGIN

	SELECT IDcurso, HORAS, VALOR FROM cursos
	WHERE IDcurso = P_IDCURSO;

	/*Parâmetros da procedure - utilizar P_ (por exemplo, para facilitar)*/
END
#

CALL LER_CURSO(4);




























































