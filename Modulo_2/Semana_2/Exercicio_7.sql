-- Exercicio 7

INSERT INTO university.departamentos
VALUES
	(900,'Programação'),
	(304,'QA'),
	(103,'Arquitetura de Softwate'),
	(203,'Java');

INSERT INTO university.cursos
VALUES
	(1023,'Algoritmos',900),
	(1123,'Complexidade de Algoritmos',304),
	(1000,'Estrutura de Dados',103),
	(1010,'POO',203);
	
INSERT INTO university.disciplinas
VALUES 
	(10001,'Estruturas de repetição',100.00),	
	(10010,'Grafos',400.00),
	(10011,'Big O Notation',900.00),
	(10100,'Objetos/Dicionários',90.00);


INSERT INTO university.alunos 
VALUES
	(1233233211,'Gabriel da Silva','04/02/2012',1123,'S'),
	(2123421232,'Carlos Nascimento','05/12/2014',1123,'N'),
	(3233657757,'Bruce Banner','12/12/2020',1123,'N'),
	(8904593534,'Roberto Torvalds','01/23/2015',1023,'S'),
	(9301312328,'Sebastião dos Reis','08/19/2017',1023,'S'),
	(0281280214,'Daniel Orivaldo da Silva','09/03/2014',1000,'N'),
	(9291417742,'Jean Secco','07/19/2001',1000,'N'),
	(1204921824,'Guilherme Oliveira','03/03/2013',1000,'S'),
	(2841289422,'André Vitor','09/08/2003',1023,'N'),
	(1289412821,'Eric Gabriel','07/18/2001',1023,'S');

INSERT INTO university.matriculas
VALUES
	(1,1233233211,10001,10.00,301,'R'),
	(4,2123421232,10001,40.00,20,'R'),
	(3,3233657757,10001,70.00,10,'A'),
	(8,8904593534,10011,61.00,25,'R'),
	(8,9301312328,10011,100.00,2,'A'),
	(7,0281280214,10010,35.00,40,'R'),
	(5,9291417742,10010,76.50,5,'A'),
	(3,1204921824,10010,100.00,0,'A'),
	(1,2841289422,10011,100.00,1,'A'),
	(2,1289412821,10011,84.00,8,'A');
	
	
INSERT INTO university.matrizes_cursos
VALUES
	(1123,10001,1),
	(1123,10010,2),
	(1123,10011,3),
	(1123,10100,1),
	(1023,10010,2),
	(1023,10100,5),
	(1023,10011,3),
	(1000,10011,1),
	(1000,10010,2),
	(1000,10001,3);