-- Exercicio 4

-- SELECT EM ALUNOS

SELECT * FROM university.alunos
WHERE cotista='N'
GROUP BY mat_aluno;

-- SELECT EM CURSOS

SELECT * FROM university.cursos
WHERE cod_curso LIKE '1%'
GROUP BY cod_curso;

-- SELECT EM DEPARTAMENTOS

SELECT * FROM university.departamentos
WHERE nome_dpto LIKE 'Programação%'
GROUP BY cod_dpto, nome_dpto;

-- SELECT EM DISCIPLINAS

SELECT * FROM university.disciplinas
WHERE carga_horaria=100.00
GROUP BY cod_disc,nome_disc,carga_horaria
ORDER BY nome_disc;

-- SELECT EM MATRICULAS

SELECT * FROM university.matriculas
WHERE nota<100
GROUP BY nota,semestre,mat_alu,cod_disc,faltas,status
ORDER BY nota;

-- SELECT EM MATRIZES_CURSOS

SELECT * FROM university.matrizes_cursos
WHERE periodo='1º'
GROUP BY cod_curso,cod_disc,periodo;
