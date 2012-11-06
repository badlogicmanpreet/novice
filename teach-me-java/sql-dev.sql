create table dev_test1 (id number, name varchar(10), description varchar(20));

select * from DEV_TEST1;

desc dev_test1;

//TODO
insert into DEV_TEST1(id, name, description)
select level n, 'abc' name, 'def' description from dual connect by level <= 1000000 ;

select count(*) from dev_test1;
