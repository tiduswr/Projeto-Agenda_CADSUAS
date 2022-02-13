package database;

import java.sql.SQLException;
import java.sql.Statement;
import util.SQL_ERROR_LOG;

public class CreateDataBase {
    
    private static final String[] sqlTables = {
        
        "CREATE TABLE IF NOT EXISTS municipios (\n" +
            "id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "nome                   TEXT(100) NOT NULL,\n" +
            "uf                     TEXT(2) NOT NULL\n" +
        ");",
        
        "CREATE TABLE IF NOT EXISTS leis (\n" +
            "id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "num                    INTEGER NOT NULL,\n" +
            "data                   TEXT(10) NOT NULL,\n" +
            "agrupamento            TEXT(100) NOT NULL,\n" +
            "descricao              TEXT(100) NOT NULL,\n" +
            "id_municipio           INTEGER,\n" +
            "FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
        ");",
        
        "CREATE TABLE IF NOT EXISTS contas (\n" +
            "id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "nome                   TEXT(100) NOT NULL,\n" +
            "numero                 TEXT(50) NOT NULL,\n" +
            "agencia                TEXT(50) NOT NULL,\n" +
            "tipo                   TEXT(50) NOT NULL,\n" +
            "status                 INTEGER NOT NULL,\n" +
            "id_municipio           INTEGER,\n" +
            "FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
        ");",
        
        "CREATE TABLE IF NOT EXISTS emails (\n" +
            "id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "email                  TEXT(50) NOT NULL UNIQUE,\n" +
            "senha                  TEXT(50) NOT NULL,\n" +
            "tipo                   TEXT(50) NOT NULL,\n" +
            "id_municipio           INTEGER,\n" +
            "FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
        ");",
        
        "CREATE TABLE IF NOT EXISTS equipamentos (\n" + 
            "id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "numIdentificador       TEXT(30) NOT NULL,\n" +
            "nome                   TEXT(100) NOT NULL,\n" +
            "email                  TEXT(50) NOT NULL,\n" +
            "ddd_telefone           INTEGER NOT NULL,\n" +
            "num_telefone           TEXT(9) NOT NULL,\n" +
            "num_endereco           INTEGER NOT NULL,\n" +
            "rua_endereco           TEXT(100) NOT NULL,\n" +
            "bairro_endereco        TEXT(100) NOT NULL,\n" +
            "cidade_endereco        TEXT(100) NOT NULL,\n" +
            "estado_endereco        TEXT(100) NOT NULL,\n" +
            "id_municipio           INTEGER NOT NULL,\n" +
            "FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
        ");",
        
        "CREATE TABLE IF NOT EXISTS vinculos (\n" +
            "id                 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "cpf                TEXT(14) NOT NULL,\n" +
            "cargo_enum      	INTEGER NOT NULL,\n" +
            "equipamento_enum	INTEGER NOT NULL,\n" +
            "id_equipamento     INTEGER,\n" +
            "id_municipio       INTEGER,\n" +
            "FOREIGN KEY(id_equipamento) REFERENCES equipamentos(id) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
            "FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
        ");",
        
        "CREATE TABLE IF NOT EXISTS funcionarios (\n" +
            "id                 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "nome               TEXT(100) NOT NULL,\n" +
            "cpf                TEXT(14) NOT NULL,\n" +
            "escolaridade	TEXT(100) NOT NULL,\n" +
            "profissao          TEXT(100) NOT NULL,\n" +
            "email              TEXT(100) NOT NULL,\n" +
            "dt_nascimento	TEXT(10) NOT NULL,\n" +
            "num_rg             TEXT(50) NOT NULL,\n" +
            "og_rg              TEXT(10) NOT NULL,\n" +
            "uf_rg              TEXT(2) NOT NULL,\n" +
            "dt_emissao_rg	TEXT(10) NOT NULL,\n" +
            "ddd_telefone	INTEGER NOT NULL,\n" +
            "num_telefone	TEXT(9) NOT NULL,\n" +
            "num_endereco	INTEGER NOT NULL,\n" +
            "rua_endereco	TEXT NOT NULL,\n" +
            "bairro_endereco	TEXT NOT NULL,\n" +
            "cidade_endereco	TEXT NOT NULL,\n" +
            "estado_endereco	TEXT NOT NULL,\n" +
            "id_municipio       INTEGER,\n" +
            "FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
        ");"
            
    };
    
    private static boolean createTable(String sql,DatabaseConnect con) {
        System.out.println("DB_LOG -> Creating table if not exist in database!");
        try {
            Statement stmt = con.getConnection().createStatement();
            stmt.execute(sql);
            stmt.close();
            return true;
        } catch (SQLException ex) {
            SQL_ERROR_LOG.message("Error in Table Creation!", ex);
            return false;
        }
    }
    
    public static boolean createDataBaseAndTables(DatabaseConnect con){
        for(String t : sqlTables){
            if(!createTable(t, con)) return false;
        }
        return true;
    }
    
}
