SELECT * FROM GROUP_TABLE ;
select * from group_table;
select * from university_table;
insert into university_table values(now(),1,now(),'광운대','@asd.ac.kr');
insert into group_table values(now(),1,now(),1,'학생회');
select * from group_admin;
insert into group_admin values(1,1,'sadasda','SAD','ROLE_ADMIN');
select * from user_table;
insert into user_table values (now(),1,now(),1,'asdas@sju.ac.kr','fs9jG652SHipXgKr8xb7sr:APA91bES9cG9hDaKD1iZ1KLRvsZD6daqLyh54VnZp25RPglNGPHZoiaCfUbjBt4hrv7GrugID7E02_i2g6ef9tirkV0B-Y4q-TjSI_e4nD3VgRSf02I6PQWaRNM_pPqCIj1IwPzvQuBh','asd','asd','asd','ROLE_USER');

select * from keyword_table;
insert into keyword_table values(now(),1,now(),'학사 테스트','학사');
insert into userkeyword_table values (true,now(),1,1,now(),1);