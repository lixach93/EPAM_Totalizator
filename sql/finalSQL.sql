create database totalizator DEFAULT CHARACTER SET utf8;
use totalizator;

create table category (
	category_id bigint not null auto_increment,
    name varchar (20) not null,
    primary key (category_id)
);

create table league (
	league_id bigint not null auto_increment,
    category_id bigint not null,
    name varchar(20) not null,
    primary key (league_id),
    foreign key (category_id) references category(category_id)
);

create table team(
	team_id bigint not null auto_increment,
    league_id bigint not null,
    name varchar(64) not null,
    primary key (team_id),
    foreign key (league_id) references league(league_id)
);

create table competition(
	competition_id bigint not null auto_increment,
	team_first bigint not null,
    team_second bigint not null,
    status varchar(10) not null default 'new',
    winner int default 0,
    team_first_result int(3) default 0,
    team_second_result int(3) default 0,
    primary key (competition_id),
    foreign key (team_first) references team(team_id),
    foreign key (team_second) references team(team_id)
);

create table rate_type(
	rate_id bigint not null auto_increment,
    name varchar(20) not null,
    primary key (rate_id)
);

create table event(
	event_id bigint not null auto_increment,
	competition_id bigint not null,
	rate_id bigint not null,
    payment TINYINT(4) default '0',
    percent double default '0',
    win_percent int default 0,
	primary key (event_id),
	foreign key (competition_id) references competition(competition_id),
	foreign key (rate_id) references rate_type (rate_id)
);

create table user (
	user_id bigint not null auto_increment,
    login varchar(20) not null unique,
    email varchar(30) not null unique,
    password varchar(255) not null,
    money double default 0,
    role varchar(20) default 'user',
    primary key (user_id)
);

create table bet (
	bet_id bigint not null auto_increment,
    user_id bigint not null,
	event_id bigint not null,
    winner_id bigint default null,
    team_first_score int(2) default null,
    team_second_score int(2) default null,
    money double not null,
    win_money double default 0,
    primary key (bet_id),
    foreign key (event_id) references event(event_id),
    foreign key (user_id) references user(user_id)
);

drop table bet;
drop table user;
drop table event;
drop table competition;
drop table team;
drop table league;
drop table category;
drop table rate_type








