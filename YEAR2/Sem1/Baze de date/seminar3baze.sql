create database ParcDeDistractii

go
use ParcDeDistractii

create table Categorie
(
cod_c int primary key identity,
nume varchar(20)
);

create table Vizitatori
(
cod_v int primary key identity,
nume varchar(20),
email varchar(20),
cod_c int foreign key references Categorie(cod_c)
);

create table Atractii
(
cod_a int primary key identity,
nume varchar(20),
descriere varchar(20),
varsta_min int,
cod_s int foreign key references Sectiuni(cod_s),

);

create table Sectiuni
(
cod_s int primary key identity,
nume varchar(20),
descriere varchar(20),

);

create table Note
(
nota float,
cod_a int,
cod_v int,
constraint fk_AtractiiNote foreign key(cod_a) references Atractii(cod_a)
on update cascade on delete cascade,
constraint fk_VizitatorNote foreign key(cod_v) references Vizitatori(cod_v)
on update cascade on delete cascade,
constraint pk_Note primary key (cod_a, cod_v)
);


insert into Sectiuni(nume, descriere) values ('s1', 'assda');
insert into Atractii(nume, descriere, varsta_min, cod_s) values ('roller', 'sadsad', 12, 1);
insert into Sectiuni values ('s2', 'assda');

update Sectiuni set descriere='asdsadsad' where nume='s1';

delete from Sectiuni where nume='s1';



select * from Sectiuni;
select cod_s, nume, descriere from Sectiuni;

select distinct varsta_min from Atractii;
--nu alege campuri cu aceleasi valori

select nume, varsta_min from Atractii where nume<>'Castelul negru';
--oricare au numele dif de 

select nume from Atractii where varsta_min=12;

select * from atractii where varsta_min<=14;
--toate coloanele cu vrst<=14

select nume from Atractii where varsta_min between 12 and 15;
--kvykkkb