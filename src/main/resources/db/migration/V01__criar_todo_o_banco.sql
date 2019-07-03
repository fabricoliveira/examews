DROP DATABASE IF EXISTS exames;
CREATE DATABASE exames;
USE exames;

CREATE TABLE pacientes (
	idPaciente BIGINT PRIMARY KEY AUTO_INCREMENT,
    nomePaciente VARCHAR(50) NOT NULL,
    idadePaciente INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pacientes (nomePaciente, idadePaciente) VALUES ('Juliao Petruchio', 32);
INSERT INTO pacientes (nomePaciente, idadePaciente) VALUES ('Catarina', 31);
INSERT INTO pacientes (nomePaciente, idadePaciente) VALUES ('Cornelio', 31);


CREATE TABLE medicos (
	idMedico BIGINT PRIMARY KEY AUTO_INCREMENT,
    nomeMedico VARCHAR(50) NOT NULL,
    crmMedico VARCHAR(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO medicos (nomeMedico, crmMedico) VALUES ('Calixto', '0123456');
INSERT INTO medicos (nomeMedico, crmMedico) VALUES ('Dinora', '4313131');
INSERT INTO medicos (nomeMedico, crmMedico) VALUES ('Januario', '5451541');


CREATE TABLE exames (
	idExame BIGINT PRIMARY KEY AUTO_INCREMENT,
    dataExame DATE NOT NULL,
    horaExame TIME NOT NULL,
	diagnosticoExame VARCHAR(250),
    idPaciente BIGINT NOT NULL, 
    idMedico BIGINT NOT NULL,
    FOREIGN KEY (idPaciente) REFERENCES pacientes (idPaciente),
    FOREIGN KEY (idMedico) REFERENCES medicos (idMedico)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) VALUES ('2019-06-27', '10:33', 'Sofrendo de amor', 1, 1);
INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) VALUES ('2019-06-27', '10:33', 'Sofrendo de amor', 1, 2);
INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) VALUES ('2019-06-27', '10:33', 'Sofrendo de amor', 1, 3);
INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) VALUES ('2019-06-27', '10:33', 'Sofrendo de amor', 2, 1);
INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) VALUES ('2019-06-27', '10:33', 'Sofrendo de amor', 2, 2);
INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) VALUES ('2019-06-27', '10:33', 'Sofrendo de amor', 2, 3);
INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) VALUES ('2019-06-27', '10:33', 'Sofrendo de amor', 3, 1);
INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) VALUES ('2019-06-27', '10:33', 'Sofrendo de amor', 3, 2);
INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) VALUES ('2019-06-27', '10:33', 'Sofrendo de amor', 3, 3);


CREATE TABLE tokens (
	idToken BIGINT PRIMARY KEY AUTO_INCREMENT,
	token VARCHAR(10) NOT NULL,
	validade DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tokens (token, validade) VALUES ('AAA', '2019-07-02');
INSERT INTO tokens (token, validade) VALUES ('BBB', '2019-07-03');