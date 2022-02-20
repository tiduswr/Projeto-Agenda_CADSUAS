CREATE TABLE IF NOT EXISTS municipios (
id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
nome                   TEXT(100) NOT NULL,
uf                     TEXT(2) NOT NULL,
iconPath               TEXT(255));

CREATE TABLE IF NOT EXISTS leis (
id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
num                    INTEGER NOT NULL,
data                   TEXT(10) NOT NULL,
agrupamento            TEXT(100) NOT NULL,
descricao              TEXT(100) NOT NULL,
id_municipio           INTEGER NOT NULL,
FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS contas (
id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
nome                   TEXT(100) NOT NULL,
numero                 TEXT(50) NOT NULL,
agencia                TEXT(50) NOT NULL,
tipo                   TEXT(50) NOT NULL,
status                 INTEGER NOT NULL,
id_municipio           INTEGER NOT NULL,
FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS emails (
id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
email                  TEXT(50) NOT NULL UNIQUE,
senha                  TEXT(50) NOT NULL,
tipo                   TEXT(50) NOT NULL,
id_municipio           INTEGER NOT NULL,
FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS equipamentos (
id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
numIdentificador       TEXT(30) NOT NULL,
nome                   TEXT(100) NOT NULL,
equipamento_enum	   INTEGER NOT NULL,
email                  TEXT(50) NOT NULL,
ddd_telefone           INTEGER NOT NULL,
num_telefone           TEXT(9) NOT NULL,
num_endereco           INTEGER NOT NULL,
rua_endereco           TEXT(100) NOT NULL,
bairro_endereco        TEXT(100) NOT NULL,
cidade_endereco        TEXT(100) NOT NULL,
estado_endereco        TEXT(100) NOT NULL,
id_municipio           INTEGER NOT NULL,
FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS vinculos (
id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
cpf                    TEXT(14) NOT NULL UNIQUE,
cargo_enum      	   INTEGER NOT NULL,
id_equipamento         INTEGER NOT NULL,
id_municipio           INTEGER NOT NULL,
FOREIGN KEY(cpf) REFERENCES funcionarios(cpf) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(id_equipamento) REFERENCES equipamentos(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS funcionarios (
id                     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
nome                   TEXT(100) NOT NULL,
cpf                    TEXT(14) NOT NULL UNIQUE,
escolaridade	       TEXT(100) NOT NULL,
profissao              TEXT(100) NOT NULL,
email                  TEXT(100) NOT NULL,
dt_nascimento	       TEXT(10) NOT NULL,
num_rg                 TEXT(50) NOT NULL,
og_rg                  TEXT(10) NOT NULL,
uf_rg                  TEXT(2) NOT NULL,
dt_emissao_rg	       TEXT(10) NOT NULL,
ddd_telefone	       INTEGER NOT NULL,
num_telefone	       TEXT(9) NOT NULL,
num_endereco	       INTEGER NOT NULL,
rua_endereco	       TEXT NOT NULL,
bairro_endereco	       TEXT NOT NULL,
cidade_endereco	       TEXT NOT NULL,
estado_endereco	       TEXT NOT NULL,
id_municipio           INTEGER NOT NULL,
FOREIGN KEY(id_municipio) REFERENCES municipios(id) ON DELETE CASCADE ON UPDATE CASCADE)
