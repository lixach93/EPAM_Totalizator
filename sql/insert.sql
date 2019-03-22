insert into category (name) value ("football");
insert into category (name) value ("hockey");
insert into category (name) value ("basketball");

insert into league (category_id , name) value (1, "Spain");
insert into league (category_id , name) value (1, "Italian");
insert into league (category_id , name) value (1, "APL");

insert into team (league_id, name) value (1, "Real Madrid");
insert into team  (league_id, name) value (1, "Barselona");
insert into team  (league_id, name) value (1, "Atletico");

insert into team  (league_id, name) value (3, "Arsenal");
insert into team  (league_id, name) value (3, "Liverpool");
insert into team  (league_id, name) value (3, "Chelsea");


insert into rate_type (name) value ("total");
insert into rate_type (name) value ("team");


insert into user (login,email,password,role)
	values
    ("sanek","some@gmail.com","$2a$12$c7lGyrhAg9RPjde9t/oQVOs3cDWB/6fuJBlAFV8N3OZ75cd/oeeIe","user"),
	("admin","some1@gmail.com","$2a$12$eF4ItY8.4Uko1pjc5nlM1ePyXtWyafC1v3n7nEHqdFINIwXrho4xi","administrator"),
	("moderator","some2@gmail.com","$2a$12$Mqv5V4nnIdQ5mMwZIoBbV.E4lNz9pf6z357IDOML64LxaYTL3og1S","moderator")
