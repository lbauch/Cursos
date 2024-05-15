CREATE database teste;
use teste;

CREATE table usuarios(
idUser int primary key,
usuario varchar(100) not null,
fone varchar(15),
login varchar(20) not null unique,
senha varchar(20) not null
);

desc usuarios;

insert into usuarios(iduser,usuario,fone,login,senha) values
(1,'Lucas B','47 9 9999-4747','lucasB','1234'),
(2,'LucasB','47 9 9999-4744','lucasB1','1234');

select * from usuarios;

create table clientes(
idCliente int primary key auto_increment,
nomeCliente varchar(100) not null,
endereco varchar(100) not null,
foneCliente varchar(50) not null,
emailCliente varchar(50)
);

insert into clientes(nomeCliente, endereco, foneCliente, emailCliente) values
('Cliente1','Rua Cli1','foneCli1','EmailCli1'),
('Cliente2','Rua Cli2','foneCli2','EmailCli2');

create table OS(
idOS int primary key auto_increment,
data_os timestamp default current_timestamp,
equipamento varchar(150) not null,
defeito varchar(150) not null,
servico varchar(50),
tecnico varchar(30),
valor decimal(10,2),
id_Cliente int not null,
foreign key (id_cliente) references clientes(idCliente)
);

desc OS;

insert into OS(equipamento, servico, defeito, tecnico, valor, id_Cliente) values
('equi1','servi1','def1','tec1',87.7,1),
('equi2','servi2','def2','tec2',98.2,2);

select * from OS;

select O.idOS, equipamento, defeito, servico, C.nomeCliente
from OS O, clientes C
where (O.id_Cliente = C.idCliente);