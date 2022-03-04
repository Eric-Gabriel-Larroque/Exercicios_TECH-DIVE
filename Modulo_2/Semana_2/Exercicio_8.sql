-- Exercicio 8

SELECT 
	a.cod_curso AS "Código do Curso",
	c.nom_curso AS "Nome do curso",
	count(*) AS "Total de alunos em cada curso" 
FROM university.alunos a
INNER JOIN university.cursos c
ON a.cod_curso=c.cod_curso
GROUP BY a.cod_curso,c.nom_curso
ORDER BY "Total de alunos em cada curso" desc;	

SELECT d.nome_disc AS "Nome da Disciplina",
SUM(m.faltas) AS "Nº de Faltas por Disciplina" 
FROM university.disciplinas d
INNER JOIN university.matriculas m
ON d.cod_disc=m.cod_disc
GROUP BY d.nome_disc
ORDER BY "Nº de Faltas por Disciplina" DESC;