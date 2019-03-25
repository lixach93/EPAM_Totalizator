insert into category (name) value ("football");
insert into category (name) value ("hockey");
insert into category (name) value ("basketball");

insert into league (category_id , name) 
	values
		(1, "Spain"),
		(1, "Seria A"),
		(1, "APL"),
		(2, "NHL"),
		(2, "KHL"),
		(3, "NBA");


insert into team (league_id, name) 
	values 
		(1, "Real Madrid"),
        (1, "Barselona"),
        (1, "Deportivo"),
        (1, "Atletico Madrid"),
        (2, "Milan"),
        (2, "Inter"),
        (2, "Lazio"),
        (2, "Roma"),
        (3, "Arsenal"),
		(3, "Liverpool"),
		(3, "Chelsea"),
        (3, "Totenham"),
        (4, "Colorado"),
        (4, "Dallas"),
        (5, "Metallurg"),
        (5, "AK Bars"),
        (6, "Houston Rokets"),
        (6, "LA LAKERS");



insert into rate_type (name) value ("total");
insert into rate_type (name) value ("team");

insert into competition (team_first,team_second)
 values 
	(5,7),
    (13,14),
    (3,11),
    (16,15),
    (17,18);
    
insert into event (competition_id, rate_id)
	values 
    (1,2),
    (2,1),
    (3,2),
    (2,2),
    (4,2),
    (5,2);
    


insert into user (login,email,password,role)
	values
    ("sanek","some@gmail.com","$2a$12$c7lGyrhAg9RPjde9t/oQVOs3cDWB/6fuJBlAFV8N3OZ75cd/oeeIe","user"),
	("admin","some1@gmail.com","$2a$12$eF4ItY8.4Uko1pjc5nlM1ePyXtWyafC1v3n7nEHqdFINIwXrho4xi","administrator"),
	("moderator","some2@gmail.com","$2a$12$Mqv5V4nnIdQ5mMwZIoBbV.E4lNz9pf6z357IDOML64LxaYTL3og1S","moderator"),
	("user1","some3@gmail.com","$2a$12$3g.rqtqYQJTsU8tSjiP63eUENNEUbdNdutdZDIdGbc7ZLcl9Je9X2","user"),
	("user2","some4@gmail.com","$2a$12$JV2QPhWy3xaNCkzUKHY67eNA7aHxtkHHPXiKGq/hqOR1LHuXxj5Pq","user"),
	("user3","some5@gmail.com","$2a$12$gjMjGRDHHfqgs6FM8p7QPOpRE.0qyELjcUp.FZ6DKHwPhFfKYogmu","user");
    
