-- Exercicio 1

create schema university;

create table university.alunos(
	MAT_ALUNO INT primary key,
  	NOME VARCHAR(100),
  	DAT_ENTRADA DATE,
  	COD_CURSO INT,
  	COTISTA CHAR(1)
);

alter table university.alunos
alter column COD_CURSO type VARCHAR(3);

alter table university.alunos
alter column MAT_ALUNO type VARCHAR(10);

create table university.cursos(
	COD_CURSO INT primary key,
  	NOM_CURSO VARCHAR(80),
  	COD_DPTO INT
);

alter table university.cursos
alter column COD_CURSO type VARCHAR(4);

alter table university.cursos
alter column COD_DPTO type VARCHAR(3);

create table university.departamentos(
	COD_DPTO INT,
  	NOME_DPTO VARCHAR(50)
);

alter table university.departamentos
alter column COD_DPTO type VARCHAR(3);

create table university.matrizes_cursos(
	COD_CURSO INT,
  	COD_DISC INT,
  	PERIODO INT
);

alter table university.matrizes_cursos
alter column COD_CURSO type VARCHAR(4);

alter table university.matrizes_cursos
alter column COD_DISC type VARCHAR(5);

alter table university.matrizes_cursos
alter column PERIODO type VARCHAR(2);

create table university.disciplinas(
	COD_DISC INT,
  	NOME_DISC VARCHAR(60),
  	CARGA_HORARIA numeric(5,
2)
);

alter table university.matrizes_cursos
alter column COD_DISC type VARCHAR(5);

create table university.matriculas(
	SEMESTRE INT,
  	MAT_ALU INT,
  	COD_DISC INT,
  	NOTA numeric(5,
2),
  	FALTAS INT,
  	STATUS CHAR(1)
);

alter table university.matriculas
alter column MAT_ALU TYPE VARCHAR(10);
alter table university.matriculas
alter column COD_DISC TYPE VARCHAR(5);

