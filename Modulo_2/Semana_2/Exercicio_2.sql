-- Exericicio 2

INSERT INTO university.alunos 
VALUES
	('1234567890','Gabriel','01/01/2010','2123','S'),
	('2131323421','Henrique','04/20/2021','2123','N'),
	('2838409832','Camila','02/13/2012','1213','N'),
	('1039473822','Aaa','12/12/2012','1433','N'),
	('2823300211','Bill Gates','04/04/2014','1322','S'),	
	('2941041244','Batman','03/03/2022','4444','S'),
	('8721912422','Chewbacca','12/04/1995','5555','S'),
	('9200424244','Sei La','05/11/2003','5555','N'),
	('2328232933','Nome','08/14/2019','1213','S'),
	('2812484844','Null','04/15/2015','9999','N');

INSERT INTO university.cursos
VALUES
	('1213','Gastronomia','000'),
	('5555','Tiro Esportivo','001'),
	('4444','Ciência Forense','002'),
	('9999','Javascript','003'),
	('2123','Java Web','004'),
	('1433','AAA','005'),
	('1322','Por Que Typescript é feio','006'),
	('1111','Microcontroladores','007'),
	('1112','R','008'),
	('1122','F','009');

INSERT INTO university.departamentos
VALUES
	('000','Comida'),
	('001','Esportes'),
	('002','Biomedicina'),
	('003','Programação Web'),
	('004','Programação Web II'),
	('005','aaa'),
	('006','Opnião Popular'),
	('007','Iot'),
	('008','Data Science'),
	('009','Não Sei');

INSERT INTO university.matrizes_cursos	
VALUES
	('1213','00001','2º'),
	('5555','00002','3º'),
	('4444','00003','1º'),
	('9999','00004','4º'),
	('2123','00005','5º'),
	('1433','00006','6º'),
	('1322','00007','6º'),
	('1111','00008','1º'),
	('1112','00009','2º'),
	('1122','00010','3º');

INSERT INTO university.disciplinas
VALUES
	('00001','Como usar uma panela',100.00),
	('00002','Como atirar',100.00),
	('00003','Química I',1.00),
	('00004','this no Javascript',999.99),
	('00005','Java EE',100.00),
	('00006','aaaa',1.00),
	('00007','Filosofia',999.99),
	('00008','Automação',900.00),
	('00009','Dados para previsão de resultados',100.00),
	('00010','Não sei também',1.00);

INSERT INTO university.matriculas
VALUES
	(1,'1234567890','00005',100.00,0,'A'),
	(2,'2131323421','00005',40.00,18,'R'),
	(4,'2838409832','00001',0.00,170,'R'),
	(7,'1039473822','00006',18.00,4,'R'),
	(1,'2823300211','00007',74.00,3,'A'),
	(8,'2941041244','00003',97.34,45,'R'),
	(3,'8721912422','00002',83.50,2,'A'),
	(8,'9200424244','00002',90.00,1,'A'),
	(7,'2328232933','00001',100.00,0,'A'),
	(5,'2812484844','00004',100.00,0,'A');
