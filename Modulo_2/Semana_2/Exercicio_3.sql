-- Exercicio 3 

-- UPDATE DE ALUNOS
UPDATE university.alunos
SET cotista='N'
WHERE nome='Batman';
UPDATE university.alunos
SET nome='Aaaa' 
WHERE nome='Aaa';

-- UPDATE DE CURSOS
UPDATE university.cursos
SET nom_curso='Typescript'
WHERE cod_curso='1322';
UPDATE university.cursos
SET nom_curso='Java EE'
WHERE cod_curso='2123';

-- UPDATE DE DEPARTAMENTOS
UPDATE university.departamentos
SET nome_dpto='IoT'
WHERE cod_dpto='007';
UPDATE university.departamentos
SET nome_dpto='Programação Web III'
WHERE cod_dpto='006';

-- UPDATE DE DISCIPLINAS
UPDATE university.disciplinas
SET nome_disc='Linguagem de Programação Tipada'
WHERE cod_disc='00007';
UPDATE university.disciplinas
SET nome_disc='Anatomia'
WHERE cod_disc='00003';


-- UPDATE DE MATRICULAS
UPDATE university.matriculas
SET status='A'
WHERE mat_alu='2941041244';
UPDATE university.matriculas
SET nota=10.00
WHERE mat_alu='2838409832';

-- UPDATE DE MATRIZES_CURSOS
UPDATE university.matrizes_cursos
SET periodo='5º'
WHERE periodo='6º';
UPDATE university.matrizes_cursos
SET periodo='1º'
WHERE cod_curso='9999';
