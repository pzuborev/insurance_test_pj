create database if not exists db_insurance
CHARACTER SET utf8 COLLATE utf8_unicode_ci;

use db_insurance;

drop table if exists tariffvalue;
drop table if exists schemetabletariff;
drop table if exists tabletariff;

drop table if exists insuranceschemerisk;
drop table if exists forindividualtype;
drop table if exists insurancescheme;
drop table if exists insuracerisktype;
drop table if exists insuranceschemerule;
drop table if exists currency;
drop table if exists frequency;
drop table if exists region;
drop table if exists gender;    


         

create table insurancescheme (
  id int,
  code varchar(30),
  name varchar(255) not null,
  constraint pk_insurancescheme primary key (id)
);

create table insuracerisktype ( 
	id int, 
	name varchar(255) not null, 
	code varchar(30) not null, 
	constraint pk_insuracerisktype primary key (id) 
);
create table insuranceschemerule    ( id int, name varchar(255) not null, constraint pk_insuranceschemerule primary key (id) );
create table region                 ( id int, name varchar(255) not null, constraint pk_region              primary key (id) );
create table currency               ( code varchar(30), name varchar(255) not null, constraint pk_currency  primary key (code) );
create table frequency              ( code varchar(30), name varchar(255) not null, constraint pk_frequency primary key (code) );
create table gender                 ( code varchar(30), name varchar(255) not null, constraint pk_gender    primary key (code) );

create table forindividualtype (
	id int,
	name varchar(255) not null,
	constraint pk_forindividualtype primary key (id)
);

create table insuranceschemerisk (
  insuranceschemeid int,
  insurancerisktypeid int,
  forindividualtypeid int,
  constraint pk_insuranceschemerisk primary key (insuranceschemeid, insurancerisktypeid, forindividualtypeid),
  constraint fk_schemerisk_scheme              foreign key (insuranceschemeid)   references insurancescheme(id),
  constraint fk_schemerisk_risk                foreign key (insurancerisktypeid)  references insuracerisktype(id),
  constraint fk_schemerisk_forindividualtypeid foreign key (forindividualtypeid) references forindividualtype(id)
);

create table tabletariff (
	id int,
	name varchar(255) not null,
	constraint pk_tabletariff primary key (id)
);

create table schemetabletariff (
  insuranceschemeid int,
  regionid int,
  insuracerisktypeid int,
  tabletariffid int,
  validfrom date,
  validtill date not null,
  constraint pk_schemetabletariff primary key (insuranceschemeid, regionid, insuracerisktypeid, tabletariffid, validfrom),
  constraint fk_schemetabletariff_scheme foreign key (insuranceschemeid) references insurancescheme (id),
  constraint fk_schemetabletariff_region foreign key (regionid) references region (id),
  constraint fk_schemetabletariff_risk foreign key (insuracerisktypeid) references insuracerisktype (id),
  constraint fk_schemetabletariff_tariff foreign key (tabletariffid) references tabletariff (id)
);

create table tariffvalue (
    tabletariffid int, 
	age int not null,
	gendercode varchar(30),
	value decimal(9,6) not null,
	constraint pk_tariffvalue primary key (tabletariffid, age, gendercode),
	constraint fk_tariffvalue_gender foreign key (gendercode) references gender(code),
	constraint fk_tariffvalue_tarifftable foreign key (tabletariffid) references tabletariff(id)
);