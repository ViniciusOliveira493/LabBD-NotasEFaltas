CREATE DATABASE bdNotasEFaltas
use bdNotasEFaltas
--=========
select * from tbFaltas
select * from tbDisciplina
select * from tbAluno
delete from tbDisciplina
delete from tbFaltas
--=============================
INSERT INTO tbDisciplina(codigo,nome,numAulas,sigla,turno)
    VALUES
        (1,'Arquitetura e Organização de Computadores',80,'AOC','Vespertino')
        ,(2,'Arquitetura e Organização de Computadores',80,'AOC','Noturno')
        ,(3,'Laboratório de Hardware',80,'ILH001','Vespertino')
        ,(4,'Banco de Dados',80,'IBD001','Vespertino')

--============================
GO
CREATE PROCEDURE sp_criarAlunos
AS
BEGIN
    DECLARE @i INT;
    SET @i = 1;

    WHILE(@i<41)
    BEGIN
        INSERT INTO tbAluno VALUES(@i,('aluno' + CAST(@i AS VARCHAR(20))))
        SET @i = @i + 1;
    END
END

exec sp_criarAlunos
--============================
GO
CREATE PROCEDURE sp_criarPresencas
AS
BEGIN
    DECLARE @dataF DATETIME;
    DECLARE @id INT;
    DECLARE @disciplina INT;
    DECLARE @count INT;

    SET @id = 1;
     SET @disciplina = 1;
    
    SET @count = 1
    SET @dataF = GETDATE();
    WHILE(@count < 5)
    BEGIN
        SET @dataF = DATEADD(DAY,2,@dataF);
        SET @disciplina = 1;
        WHILE(@disciplina<4)
        BEGIN
            SET @id = 1;
            WHILE(@id<41)
            BEGIN
                INSERT INTO tbFaltas(raAluno,codigoDisciplina,data,presenca)
                    VALUES
                        (@id,@disciplina,@dataF,CAST(((RAND()*4)+0) AS INT))
                SET @id = @id + 1;
            END
            SET @disciplina = @disciplina+1;
        END
        SET @count = @count + 1;
    END   
END
exec sp_criarPresencas
--=======================
GO
CREATE FUNCTION fn_contarFaltas(@faltas INT)
RETURNS VARCHAR(4)
AS
BEGIN
    DECLARE @f VARCHAR(4)
    DECLARE @i INT
    DECLARE @fal INT
    set @fal = @faltas
    SET @f = '';
    SET @i = 1
    SET @fal = 4 - @fal;
    WHILE(@i < 5)
    BEGIN
        IF(@fal>0)
        BEGIN
            SET @f = @f + 'P';
        END
        ELSE
        BEGIN
            SET @f = @f + 'F';
        END
        SET @fal = @fal - 1;
        SET @i = @i + 1;
    END
    return @f;
END
go
Select dbo.fn_contarFaltas(3)
go
--=======
CREATE FUNCTION fn_somarQtdFaltas(@ra BIGINT,@disciplina BIGINT)
RETURNS INT
AS
BEGIN
    DECLARE @qtd INT
    
    SET @qtd = (SELECT 
        SUM(f.presenca)
    FROM tbFaltas AS f
    WHERE 
        codigoDisciplina = @disciplina
        AND 
        raAluno = @ra)
    RETURN @qtd    
END
GO
--===================UDF FALTAS=======
CREATE FUNCTION fn_buscarAluno(@ra BIGINT,@disciplina BIGINT)
RETURNS @tab TABLE(
    ra BIGINT
    ,nome VARCHAR(100)
    , total INT 
)
AS
BEGIN
    DECLARE @colunas VARCHAR(max)
    DECLARE @query VARCHAR(max)
    SET @colunas = STUFF((
        SELECT
            distinct ','+QUOTENAME(f.data)
        FROM tbFaltas AS f
        FOR XML PATH('')
        ),1,1,'')
    print @colunas
    SET @query = 
        'SELECT * FROM 
        (
            SELECT
                a.ra
                , a.nome
                , sum(presenca) AS presenca
                , f.data
                , dbo.fn_somarQtdFaltas(a.ra,f.codigoDisciplina) as total
            FROM tbFaltas AS f, tbAluno AS a
            WHERE 
                a.ra = f.raAluno
                AND
                codigoDisciplina = '+'2'+'
                AND a.ra = '+'2'+'
            GROUP BY a.ra,a.nome,f.data,f.codigoDisciplina
        ) girar
        PIVOT(SUM(presenca)  FOR data IN ('+@colunas+')) emColunas
        ORDER BY ra'
    --INSERT INTO @tab
    execute (@query)
    RETURN 
END
--=====================

GO
---============= TESTES
SELECT
    a.ra
    , a.nome
    , dbo.fn_contarFaltas(sum(presenca)) AS Presenca
    , f.data
FROM tbFaltas AS f, tbAluno AS a
WHERE 
    a.ra = f.raAluno
    AND
    codigoDisciplina = 2
    AND a.ra = 2
GROUP BY a.ra,a.nome,f.data
GO