SELECT * FROM university.alunos a
INNER JOIN university.matriculas m
ON a.mat_alu=m.mat_alu;

SELECT * FROM university.matriculas m
LEFT JOIN university.disciplinas d
ON m.cod_disc = d.cod_disc;

SELECT * FROM university.matrizes_cursos mc
RIGHT JOIN university.cursos c
ON mc.cod_curso=c.cod_curso;

SELECT * FROM university.alunos a
FULL OUTER JOIN  university.matriculas m
ON a.mat_alu=m.mat_alu;