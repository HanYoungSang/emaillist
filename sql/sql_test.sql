-- SQL Test

insert
  into emaillist
values ( seq_emaillist.nextval, '둘', '리', 'dooly@gmail.com');

select no, first_name, last_name, email from emaillist order by no;

rollback;  