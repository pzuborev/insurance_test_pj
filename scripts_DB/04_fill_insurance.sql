use db_insurance;

insert into insurancescheme 
 ( id, code, name )
values 
 ( 1, 'Life', 'Лайф' );

 insert into insurancescheme 
 ( id, code, name )
values 
 ( 2, 'Pension', 'Пенсия' );

 insert into insurancescheme 
 ( id, code, name )
values 
 ( 3, 'Goodness', 'Добродетель' );
 
-- risks
insert into insuracerisktype (id, code, name) values (1, 'Д', 'Дожитие');
insert into insuracerisktype (id, code, name) values (2, 'С', 'Смерть');
insert into insuracerisktype (id, code, name) values (3, 'С(НС)', 'Смерть от несчастного случая');
insert into insuracerisktype (id, code, name) values (4, 'С(ДТП)', 'Смерть в результате ДТП');
insert into insuracerisktype (id, code, name) values (5, 'Инв', 'Ивалидность');
-- scheme rules
insert into insuranceschemerule (id, name) values (1, 'Правило 1');
insert into insuranceschemerule (id, name) values (2, 'Правило 2');
-- currency
insert into currency (code, name) values ('UAH', 'гривна');
insert into currency (code, name) values ('USD', 'доллар');
insert into currency (code, name) values ('EUR', 'евро');
-- frequency
insert into frequency (code, name) values ('Y', 'Ежегодно');
insert into frequency (code, name) values ('M', 'Ежемесячно');
insert into frequency (code, name) values ('H', 'Раз в пол года');
insert into frequency (code, name) values ('Q', 'Ежеквартально');
insert into frequency (code, name) values ('E', 'Единовременно');
-- region
insert into region (id, name) values (1, 'Украина');
-- gender
insert into gender (code, name) values ('M', 'Мужчина');
insert into gender (code, name) values ('F', 'Женщина');   

insert into forindividualtype (id, name) values (1, 'Застрахованное лицо');
insert into forindividualtype (id, name) values (2, 'Страхователь');
insert into forindividualtype (id, name) values (3, 'Третье лицо');

insert into insuranceschemerisk (insuranceschemeid, insurancerisktypeid, forindividualtypeid)
values (1, 1, 1);
insert into insuranceschemerisk (insuranceschemeid, insurancerisktypeid, forindividualtypeid)
values (1, 2, 1);
insert into insuranceschemerisk (insuranceschemeid, insurancerisktypeid, forindividualtypeid)
values (1, 3, 1);
insert into insuranceschemerisk (insuranceschemeid, insurancerisktypeid, forindividualtypeid)
values (1, 4, 1);
insert into insuranceschemerisk (insuranceschemeid, insurancerisktypeid, forindividualtypeid)
values (1, 5, 1);
insert into insuranceschemerisk (insuranceschemeid, insurancerisktypeid, forindividualtypeid)
values (2, 1, 1);
insert into insuranceschemerisk (insuranceschemeid, insurancerisktypeid, forindividualtypeid)
values (2, 2, 1);
insert into insuranceschemerisk (insuranceschemeid, insurancerisktypeid, forindividualtypeid)
values (2, 3, 1);

-- tariff
insert into tabletariff (id, name) values (1, 'Табличные тарифы 1');
insert into tabletariff (id, name) values (2, 'Табличные тарифы 2');
insert into tabletariff (id, name) values (3, 'Табличные тарифы 3');

insert into schemetabletariff (
  insuranceschemeid, 
  regionid,
  insuracerisktypeid,
  tabletariffid,
  validfrom,
  validtill)
values (
  1,
  1,
  1, -- risk
  1, -- tabletariff
  '2000-01-01',
  '2999-01-01'
);

insert into schemetabletariff (
  insuranceschemeid, 
  regionid,
  insuracerisktypeid,
  tabletariffid,
  validfrom,
  validtill)
values (
  1,
  1,
  2, -- risk
  1, -- tabletariff
  '2000-01-01',
  '2999-01-01'
);

insert into schemetabletariff (
  insuranceschemeid, 
  regionid,
  insuracerisktypeid,
  tabletariffid,
  validfrom,
  validtill)
values (
  1,
  1,
  3, -- risk
  3, -- tabletariff
  '2000-01-01',
  '2999-01-01'
);

insert into schemetabletariff (
  insuranceschemeid, 
  regionid,
  insuracerisktypeid,
  tabletariffid,
  validfrom,
  validtill)
values (
  1,
  1,
  4, -- risk
  1, -- tabletariff
  '2000-01-01',
  '2999-01-01'
);

insert into schemetabletariff (
  insuranceschemeid, 
  regionid,
  insuracerisktypeid,
  tabletariffid,
  validfrom,
  validtill)
values (
  1,
  1,
  5, -- risk
  1, -- tabletariff
  '2000-01-01',
  '2999-01-01'
);

----

insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 1, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 2, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 3, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 4, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 5, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 6, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 7, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 8, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 9, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 10, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 11, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 12, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 13, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 14, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 15, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 16, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 17, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 18, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 19, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 20, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 21, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 22, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 23, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 24, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 25, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 26, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 27, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 28, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 29, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 30, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 31, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 32, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 33, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 34, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 35, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 36, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 37, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 38, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 39, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 40, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 41, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 42, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 43, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 44, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 45, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 46, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 47, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 48, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 49, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 50, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 51, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 52, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 53, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 54, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 55, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 56, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 57, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 58, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 59, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 60, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 61, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 62, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 63, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 64, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 65, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 66, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 67, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 68, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 69, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 70, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 71, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 72, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 73, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 74, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 75, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 76, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 77, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 78, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 79, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 80, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 81, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 82, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 83, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 84, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 85, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 86, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 87, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 88, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 89, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 90, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 91, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 92, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 93, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 94, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 95, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 96, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 97, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 98, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 99, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 100, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 101, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 102, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 103, 'M', 0.001);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 104, 'M', 0.001);

insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 1,   'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 2,   'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 3,   'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 4,   'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 5,   'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 6,   'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 7,   'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 8,   'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 9,   'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 10,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 11,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 12,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 13,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 14,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 15,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 16,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 17,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 18,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 19,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 20,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 21,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 22,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 23,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 24,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 25,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 26,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 27,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 28,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 29,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 30,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 31,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 32,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 33,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 34,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 35,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 36,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 37,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 38,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 39,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 40,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 41,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 42,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 43,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 44,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 45,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 46,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 47,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 48,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 49,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 50,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 51,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 52,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 53,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 54,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 55,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 56,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 57,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 58,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 59,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 60,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 61,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 62,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 63,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 64,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 65,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 66,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 67,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 68,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 69,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 70,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 71,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 72,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 73,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 74,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 75,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 76,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 77,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 78,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 79,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 80,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 81,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 82,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 83,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 84,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 85,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 86,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 87,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 88,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 89,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 90,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 91,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 92,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 93,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 94,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 95,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 96,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 97,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 98,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 99,  'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 100, 'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 101, 'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 102, 'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 103, 'F', 0.0011);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (1, 104, 'F', 0.0011);

insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 1,   'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 2,   'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 3,   'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 4,   'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 5,   'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 6,   'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 7,   'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 8,   'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 9,   'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 10,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 11,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 12,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 13,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 14,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 15,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 16,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 17,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 18,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 19,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 20,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 21,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 22,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 23,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 24,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 25,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 26,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 27,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 28,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 29,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 30,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 31,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 32,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 33,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 34,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 35,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 36,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 37,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 38,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 39,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 40,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 41,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 42,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 43,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 44,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 45,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 46,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 47,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 48,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 49,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 50,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 51,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 52,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 53,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 54,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 55,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 56,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 57,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 58,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 59,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 60,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 61,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 62,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 63,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 64,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 65,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 66,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 67,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 68,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 69,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 70,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 71,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 72,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 73,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 74,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 75,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 76,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 77,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 78,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 79,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 80,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 81,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 82,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 83,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 84,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 85,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 86,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 87,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 88,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 89,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 90,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 91,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 92,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 93,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 94,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 95,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 96,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 97,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 98,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 99,  'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 100, 'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 101, 'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 102, 'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 103, 'M', 0.003);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 104, 'M', 0.003);

insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 1,   'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 2,   'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 3,   'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 4,   'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 5,   'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 6,   'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 7,   'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 8,   'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 9,   'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 10,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 11,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 12,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 13,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 14,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 15,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 16,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 17,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 18,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 19,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 20,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 21,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 22,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 23,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 24,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 25,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 26,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 27,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 28,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 29,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 30,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 31,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 32,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 33,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 34,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 35,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 36,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 37,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 38,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 39,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 40,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 41,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 42,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 43,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 44,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 45,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 46,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 47,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 48,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 49,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 50,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 51,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 52,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 53,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 54,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 55,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 56,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 57,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 58,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 59,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 60,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 61,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 62,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 63,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 64,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 65,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 66,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 67,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 68,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 69,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 70,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 71,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 72,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 73,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 74,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 75,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 76,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 77,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 78,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 79,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 80,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 81,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 82,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 83,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 84,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 85,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 86,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 87,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 88,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 89,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 90,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 91,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 92,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 93,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 94,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 95,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 96,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 97,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 98,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 99,  'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 100, 'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 101, 'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 102, 'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 103, 'F', 0.0031);
insert into tariffvalue ( tabletariffid, age, gendercode, value ) values (3, 104, 'F', 0.0031);

commit;        