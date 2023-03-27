insert into todolist.tb_member(email, password, nickname, activated) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
insert into todolist.tb_member (email, password, nickname, activated) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

insert into tb_authority values ('ROLE_USER');
insert into tb_authority values ('ROLE_ADMIN');

insert into todolist.tb_member_authority (member_id, authority_id) values (1, 'ROLE_USER');
insert into todolist.tb_member_authority (member_id, authority_id) values (1, 'ROLE_ADMIN');
insert into todolist.tb_member_authority (member_id, authority_id) values (2, 'ROLE_USER');

select * from todolist.tb_member;
select * from todolist.tb_member_authority;
select * from todolist.tb_authority;
