package br.edu.fateczl.NotasEFaltas.repository.relatorioFaltas.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.fateczl.NotasEFaltas.repository.relatorioFaltas.model.DataFalta;
import br.edu.fateczl.NotasEFaltas.repository.relatorioFaltas.model.RelatorioFalta;

public class RelatorioFaltasDAO {
	Conexao cn = new Conexao();
	
	public List<RelatorioFalta> obterRelatorioDisciplina(BigInteger a){
		List<RelatorioFalta> l = new ArrayList<>();
		try {
			Connection conn = cn.getConexao();
			String query = " 	DECLARE @colunas VARCHAR(max)"
					+ "    		DECLARE @query VARCHAR(max)"
					+ "			DECLARE @id VARCHAR(max)"
					+ "			SET @id = ?"
					+ "    		SET @colunas = STUFF(("
					+ "        	SELECT"
					+ "            distinct ','+QUOTENAME(f.data)"
					+ "        	FROM tbFaltas AS f"
					+ "        	FOR XML PATH('')"
					+ "        	),1,1,'')"
					+ "    		SET @query = "
					+ "        	'SELECT * FROM "
					+ "        ("
					+ "            select * from fn_buscarFaltasAlunos('+@id+')"
					+ "        ) girar"
					+ "        PIVOT(MAX(presenca)  FOR data IN ('+@colunas+')) emColunas"
					+ "        ORDER BY ra'"
					+ "    execute (@query)";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setObject(1, a, java.sql.Types.BIGINT);			
			ResultSet rs = pstm.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int qtdColunas = rsmd.getColumnCount()+1;
			String colunas[] = new String[qtdColunas];
			
			String query2 = " SELECT nome FROM tbDisciplina WHERE codigo = ?";
			PreparedStatement pstm2 = conn.prepareStatement(query2);
			pstm2.setObject(1, a, java.sql.Types.BIGINT);			
			ResultSet rs2 = pstm2.executeQuery();
			String disc ="";
			while(rs2.next()) {
				disc = rs2.getString(1);
				break;
			}
			for(int i=1;i<qtdColunas;i++) {
				colunas[i]=rsmd.getColumnName(i);
			}			
			while(rs.next()) {
				RelatorioFalta r = new RelatorioFalta();
				r.setRa(rs.getObject("ra")+"");
				r.setNome(rs.getString("nome"));
				r.setTotal(rs.getInt("total"));
				r.setDisciplina(disc);
				List<DataFalta> list = new ArrayList<>();
				for(int i=4;i<qtdColunas;i++) {
					DataFalta falta = new DataFalta();
					falta.setData(colunas[i]);
					falta.setFalta(rs.getString(i));
					list.add(falta);
				}
				r.setFaltas(list);
				l.add(r);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;		
	}
}
