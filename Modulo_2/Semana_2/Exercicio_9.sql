-- Exercício 9

SELECT COUNT(1) "Cotistas Reprovados"
FROM university.alunos a
INNER JOIN university.matriculas m
ON a.mat_alu=m.mat_alu
WHERE a.cotista='S'
AND m.status='R'
ORDER BY "Cotistas Reprovados";

SELECT 
	m.cod_disc AS "Código da Disciplina",
	COUNT(1) "Cotistas Matriculados por Disciplina"
FROM university.alunos a
INNER JOIN university.matriculas m
ON a.mat_alu=m.mat_alu
WHERE a.cotista='S'
GROUP BY m.cod_disc;