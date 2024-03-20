
insert into university_table values(now(),1,now(),'세종대학교');

insert into group_table values (now(),1,now(),1,'컴퓨터공학과');
insert into group_table values (now(),2,now(),1,'경영학과');
insert into group_table values (now(),3,now(),1,'지능기전공학과');

insert into user_table values(NOW(),1,NOW(),1,'jaepyo99@sju.ac.kr','asd','이재표','dlwovy','ROLE_USER');

insert into user_group_table values(now(),1,now(),1,1);

insert into keyword_table values (now(),1,now(),'학사공지입니다.','학사');
insert into keyword_table values (now(),2,now(),'장학공지입니다.','장학');
insert into keyword_table values (now(),3,now(),'졸업공지입니다.','졸업');

insert into USERKEYWORD_TABLE  values (now(),1,1,now(),1);
insert into USERKEYWORD_TABLE  values (now(),2,2,now(),1);