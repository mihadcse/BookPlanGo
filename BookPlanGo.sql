SELECT * FROM sys.userinfo;

Insert into userinfo (Username,Password,Contact) values ('bool','1234','010101')
Insert into userinfo (Username,Password,Contact) values ('qube','123','01101')
Insert into userinfo (Username,Password,Contact) values ('kole','124','0101')

Select count(1) from userinfo where Username = 'bool' and Password = '1234'