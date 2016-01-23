<Query Kind="SQL">
  <Connection>
    <ID>da7e50f7-1cf7-4e53-85c3-84626b3b01bc</ID>
    <Server>twentywork.database.windows.net</Server>
    <SqlSecurity>true</SqlSecurity>
    <Database>twentywork</Database>
    <UserName>twentyworkuser</UserName>
    <Password>AQAAANCMnd8BFdERjHoAwE/Cl+sBAAAA4yrylCWV90Ssw9BHCr8T0AAAAAACAAAAAAAQZgAAAAEAACAAAAC4pdVy01CdQ9Jd34pk4OXfQAQboGIAgVwzpB0M+r6uEQAAAAAOgAAAAAIAACAAAAAzq2pSmM4P2pb/54lbUDNAOcAIHDlzIZUm1G+Is6x+rBAAAACSM/3wGxyk90JmxNLMvXeYQAAAACLhomKhkd69PoBtlrNUOoBrgcZztihvQ7NwFy+WFlybLSiUyNpFzfhKY3aivDHjBnbZ1cbUq4KVtpMCXJms/Go=</Password>
    <DbVersion>Azure</DbVersion>
  </Connection>
</Query>

/*
drop table Sub
drop table Msg
drop table GroupUser
drop table Groups
drop table Images
drop table Notify
drop table ShareFile

drop table TeamSchedule
drop table MyFav
drop table Participant
drop table MySchedule
drop table Board
drop table BoardClass

drop table TeamUser
drop table Users
drop table Team

DROP PROCEDURE [dbo].[gen_folder_tree]
DROP PROCEDURE [dbo].[find_delete_files]
  */
--1--INSERT INTO Users (userName, email, password, birth, userImage, cellPhone, phone)
create table Users
(   userID varchar(32) NOT NULL PRIMARY KEY  default replace(NEWID(),'-',''),
    userName varchar(10) NOT NULL,
    email varchar(50) NOT NULL,
    password varbinary(35) NOT NULL,
    birth date,
    userImage image,
    cellPhone varchar(15),
    phone varchar(15),
) 
go
--2--
create table Team
(
    teamID varchar(32) NOT NULL PRIMARY KEY  default replace(NEWID(),'-',''),
    teamName varchar(20) not null,
    teamImage image,
    teamAbout varchar(max)
)
--3--
create table TeamUser
(
    userID varchar(32) references Users (userID),
    teamID varchar(32) references Team  (teamID),
	/*
    post varchar(10),
    department varchar(10),
    extension varchar(15),
    activeDate date ,
    rights int 
	*/
    constraint teamUserID primary key (userID,teamID),

)
go
--4--
create table Groups 
(
    groupID varchar(50) not null primary key,
    groupName varchar(40) not null,
)
GO
--5--
create table Images
(
    imageID varchar(50) not null primary key,
    msgimage image,
)
GO
--6--
create table Msg
(
    msgID varchar(50) not null primary key,
    imageID varchar(50) references Images(imageID),
    groupID varchar(50) references Groups(groupID),
    msgTime datetime not null,
    msgText varchar(max),
    msgFile varbinary(max),
    msgFileName varchar(50),
    msgFileType varchar(10),
    msgFIleSize int,
    userID varchar(32) references Users (userID),
)
GO
--7--
create table GroupUser
(
    groupID varchar(50) references Groups (groupID),
    userID varchar(32) references Users (userID),
    constraint groupUser_ID primary key(groupID,userID)
)
GO
--8--
create table TeamSchedule
(
    eventID varchar(32) NOT NULL PRIMARY KEY  default replace(NEWID(),'-',''),
    eventName varchar(50) not null,
    eventStartDate datetime not null,
    eventEndDate datetime not null,
    eventDesp varchar(max),
    location varchar(50),
    reminder datetime default null,
	eventAllday varchar(5) default null,
	eventColor varchar(20) default 'red',
    teamScheduleUserID varchar(32) ,
    teamID varchar(32) references Team  (teamID),
)
--9--
create table MySchedule
(
    myEventID varchar(32) NOT NULL PRIMARY KEY  default replace(NEWID(),'-',''),
    myEvent varchar(50) not null,
    myEventStartDate datetime not null,
    myEventEndDate datetime not null,
	myEventDesp varchar(max) default null,
    myLocation varchar(50) default null,
    myRminder datetime default null,
	myEventAllday varchar(5) default null,
	myEventColor varchar(20) default NULL,
    myUserID varchar(32),
)
--10--
create table Participant
(
    MyEventID varchar(32) references MySchedule(MyEventID),
    userID varchar(32) references Users (userID),
    constraint ParticipantID primary key (MyEventID,userID),
)
GO
--11--
create table BoardClass
(
    boardClassID varchar(50) not null primary key ,
    boardClassText varchar(50),
)
GO
--12--
create table Board
(
    boardID varchar(50) primary key not null,
    boardTitle varchar(50) not null,
    boardText varchar(max),
    boardTime datetime not null,
    userID varchar(32) references Users (userID),
    teamID varchar(32) references Team  (teamID),
    boardClassID varchar(50) references BoardClass(boardClassID),
)
GO
--13--
create table Sub
(
    subID varchar(50) not null primary key,
    subText varchar(max),
    userID varchar(32) references Users (userID),
    boardID varchar(50) references Board(boardID),
)
GO
--14--
create table MyFav
(
    userID varchar(32) references Users (userID),
    boardID varchar(50) references Board(boardID),
    constraint MyFavID primary key(userID,boardID),
)
GO
--15--
   
CREATE TABLE ShareFile(
    fileId          int     IDENTITY(900,1)         PRIMARY KEY,--
    fileName_       varchar(50) not null,
    fileType        varchar(10) not null,
    fileSize        int,
    updateTime      datetime ,
    userId          varchar(32),                
    teamId          varchar(32),            
    upperFolderId   int,                
    CONSTRAINT FK_ShareFile_upperFolderId   foreign key (upperFolderId) references ShareFile (fileId) ,
    CONSTRAINT FK_ShareFile_userId          foreign key (userId)        references Users(userID) ,
    CONSTRAINT FK_ShareFile_teamId          foreign key (teamId)        references Team(teamID),
)
go
INSERT INTO ShareFile VALUES ( 'WebApp根目錄' , '資料夾' ,null ,null,null,null, null);--900
go
create procedure gen_folder_tree ( @v_teamId  varchar(32) )
  AS
  BEGIN
	 WITH findFile (fileId, fileName_,fileType,fileSize,updateTime,userId, teamId,upperFolderId , fileLevel)AS 
	(
		SELECT fileId, fileName_,fileType,fileSize,updateTime,userId, teamId,upperFolderId , 0 as level
		FROM   ShareFile
		WHERE  fileSize IS NULL
		AND upperFolderId=900 --IS NULL
		AND teamId =@v_teamId

		UNION ALL

		SELECT shr.fileId , shr.fileName_ , shr.fileType , shr.fileSize , shr.updateTime , shr.userId,shr.teamId, shr.upperFolderId, tree.fileLevel + 1
		FROM ShareFile shr INNER JOIN findFile tree
		ON shr.upperFolderId  = tree.fileId 
		WHERE  shr.fileSize IS NULL 
	)
	SELECT * FROM findFile 
	order by fileLevel 
  END;
  GO


  create procedure find_delete_files ( @v_fileId  varchar(32))
  AS
  BEGIN
 WITH findFile (fileId, fileLevel)AS
    (
        SELECT fileId , 1 as level
        FROM   ShareFile
        WHERE  fileId= @v_fileId
   
        UNION ALL
   
        SELECT shr.fileId , tree.fileLevel + 1 
        FROM ShareFile shr INNER JOIN findFile tree
        ON shr.upperFolderId  = tree.fileId 
           
    )
    DELETE ShareFile 
    WHERE fileId IN(
    SELECT fileId FROM findFile
    )
 END;
 GO

create table Notify
(
	teamID varchar(32) references Team  (teamID),
    userID varchar(32) references Users (userID),
    fileId int references ShareFile(fileId),
	shareTime datetime not null,
	comment varchar(max) ,
	readState  varchar(5),
    constraint PK_Notify primary key(userID,fileId),
)
GO

/*
  declare @teamId1 varchar(32) 
  set @teamId1=NEWID()
  INSERT INTO Team VALUES(@teamId1,'TinaTeam',null,null)
  declare @teamId2 varchar(32) 
  set @teamId2=NEWID()
  INSERT INTO Team VALUES(@teamId2,'QubeTeam',null,null)
  
  declare @userId1 varchar(32) 
  set @userId1=NEWID()
  INSERT INTO Users VALUES(@userId1,'Tina','tina@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
  declare @userId2 varchar(32) 
  set @userId2=NEWID()
  INSERT INTO Users VALUES(@userId2,'John','john@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
  declare @userId3 varchar(32) 
  set @userId3=NEWID()
  INSERT INTO Users VALUES(@userId3,'Andro','andro@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
  declare @userId4 varchar(32) 
  set @userId4=NEWID()
  INSERT INTO Users VALUES(@userId4,'Qube','qube@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
  declare @userId5 varchar(32) 
  set @userId5=NEWID()
  INSERT INTO Users VALUES(@userId5,'Puffy','puffy@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
 
  INSERT INTO TeamUser values(@userId1,@teamId1)
  INSERT INTO TeamUser values(@userId2,@teamId1)
  INSERT INTO TeamUser values(@userId3,@teamId1)
  INSERT INTO TeamUser values(@userId1,@teamId2)
  INSERT INTO TeamUser values(@userId4,@teamId2)
  INSERT INTO TeamUser values(@userId5,@teamId2)
  
 -- fileName_ not null, fileType  not null,   fileSize ,   updateTime , userId , teamId ,   upperFolderId   int,     
 --INSERT INTO ShareFile VALUES ( 'WebApp根目錄' , '資料夾' ,null ,null,null,null, null);--900
 INSERT INTO ShareFile VALUES ( 'Group1根目錄' , '資料夾' ,null ,null,null,@teamId1, '900');--901
 INSERT INTO ShareFile VALUES ( 'Group2根目錄' , '資料夾' ,null ,null ,null,@teamId2, '900');--902
 INSERT INTO ShareFile VALUES ( 'Group2-1' , '資料夾' ,null ,null ,null,@teamId2, '902');
 INSERT INTO ShareFile VALUES ( 'Group1-1' , '資料夾' ,null ,null ,null,@teamId1, '901');
 INSERT INTO ShareFile VALUES ( 'Group1-2' , '資料夾' ,null ,null,null,@teamId1, '901');
 INSERT INTO ShareFile VALUES ( 'Group1-3' , '資料夾' ,null ,null ,null,@teamId1, '901');
 INSERT INTO ShareFile VALUES ( 'Group2-1-1' , '資料夾' ,null ,null ,null,@teamId2, '903');
 INSERT INTO ShareFile VALUES ( 'Group2-1-2' , '資料夾' ,null ,null ,null,@teamId2, '903');
 INSERT INTO ShareFile VALUES ( 'Group1-1-1' , '資料夾' ,null ,null,null,@teamId1, '904');--10
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,@userId1,@teamId1, '901');
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,@userId2,@teamId1, '901');
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,@userId3,@teamId1, '901');
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,@userId1,@teamId1, '905')
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,@userId2,@teamId1, '905')
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,@userId1,@teamId1, '906')
 INSERT INTO ShareFile VALUES ( 'catcat.jpg' , 'jpg' ,115181 ,'2015-12-18 19:31:07.680' ,@userId2,@teamId1, '906')--17
go


   /*

--create Users data--
insert into Users values ('100', 'Kirin', 'x6041500@hotmail.com',0x41,'1990-05-13',null,'0933-456-781','03-4567891');
insert into Users values ( '101','Rita', 'Rita@hotmail.com',0x41,'1989-06-14',null,'0928-456-782','02-24847892');
insert into Users values ( '102','Wu', 'Wu@hotmail.com',0x41,'1991-07-25',null,'0988-456-783','03-3304693');
insert into Users values ('103', 'Paul', 'Paul@hotmail.com',0x41,'1988-07-08',null,'0936-763-784','04-24368994');
insert into Users values ( '104','Sandy', 'Sandy@gmail.com',0x41,'1979-11-28',null,'0937-893-785','07-5622875');
insert into Users values ( '105','Jimmy', 'Jimmy@gmail.com',0x41,'1977-09-20',null,'0963-466-786','03-3367426');
insert into Users values ('106', 'Tina', 'Tina@hotmail.com',0x41,'1988-04-30',null,'0961-456-787','02-24268297');
insert into Users values ('107', 'Cindy', 'Cindy@gmail.com',0x41,'1990-01-12',null,'0902-17-788','04-4567898');
insert into Users values ( '108','Mow', 'MowMow@gmail.com',0x41,'1990-01-12',null,'0902-17-788','04-4567898');
go
  
--create Team data--
insert into Team values ('200', 'KukuCoding','this is test' ,null);
insert into Team values ('201', 'TwentyWorkOfficial','gogogo' ,null);
insert into Team values ('202', 'Group3','debug' ,null);
go
  
--create TeamUser data--
insert into TeamUser values ( '100' , '200' , null , null , null , '2015-12-25' , 1 );
insert into TeamUser values ( '101' , '200' , null , null , null , '2015-12-25' , 1 );
insert into TeamUser values ( '102' , '200' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '103' , '200' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '104' , '200' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '105' ,'200' , null , null , null , '2015-12-25' , 2 );
 
insert into TeamUser values ( '106' , '201' , null , null , null , '2015-12-25' , 1 );
insert into TeamUser values ( '107' , '201' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '108' , '201' , null , null , null , '2015-12-25' , 2 );
 
insert into TeamUser values ( '100' , '202' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '101' , '202' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '102' , '202' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '103' , '202' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '104' , '202' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '105' , '202' , null , null , null , '2015-12-25' , 2 );
insert into TeamUser values ( '106' , '202' , null , null , null , '2015-12-25' , 1 );
insert into TeamUser values ( '107' , '202' , null , null , null , '2015-12-25' , 1 );
insert into TeamUser values ( '108' , '202' , null , null , null , '2015-12-25' , 1 );
go


--create TeamSchedule  data--

insert into TeamSchedule  values ( 'TeamEvent101', '2016-01-14 01:01:01.000', '2016-01-14 02:02:02.111', null , null , null , '100' , '200' );
insert into TeamSchedule  values ( 'TeamEvent202', '2016-01-15 02:02:02.111', '2016-01-15 03:03:03.222', null , null , null , '101' , '200' );
insert into TeamSchedule  values ( 'TeamEvent103', '2016-01-16 03:03:03.000', '2016-01-16 04:04:04.111', null , null , null , '100' , '202' );
insert into TeamSchedule  values ( 'TeamEvent204', '2016-01-17 04:04:04.111', '2016-01-17 05:05:05.222', null , null , null , '101' , '202' );
insert into TeamSchedule  values ( 'TeamEvent105', '2016-01-18 05:05:05.000', '2016-01-18 06:06:06.111', null , null , null , '100' , '200' );
insert into TeamSchedule  values ( 'TeamEvent206', '2016-01-19 06:06:06.111', '2016-01-19 07:07:07.222', null , null , null , '101' , '200' );
insert into TeamSchedule  values ( 'TeamEvent107', '2016-01-20 07:07:07.000', '2016-01-20 08:08:08.111', null , null , null , '100' , '201' );
insert into TeamSchedule  values ( 'TeamEvent208', '2016-01-21 08:08:08.111', '2016-01-21 09:09:09.222', null , null , null , '101' , '202' );
insert into TeamSchedule  values ( 'TeamEvent309', '2016-01-22 09:09:09.222', '2016-01-22 10:10:10.333', null , null , null , '102' , '200' );
insert into TeamSchedule  values ( 'TeamEvent310', '2016-01-23 10:10:10.222', '2016-01-23 11:11:11.333', null , null , null , '102' , '202' );
go


--create MySchedule data--
insert into MySchedule values ( 'MyEvent101', '2016-01-04 01:01:01.000', '2016-01-04 02:02:02.111', null , null , null , '100');
insert into MySchedule values ( 'MyEvent202', '2016-01-05 02:02:02.111', '2016-01-05 03:03:03.222', null , null , null , '101');
insert into MySchedule values ( 'MyEvent103', '2016-01-06 03:03:03.000', '2016-01-06 04:04:04.111', null , null , null , '100');
insert into MySchedule values ( 'MyEvent204', '2016-01-07 04:04:04.111', '2016-01-07 05:05:05.222', null , null , null , '101');
insert into MySchedule values ( 'MyEvent105', '2016-01-08 05:05:05.000', '2016-01-08 06:06:06.111', null , null , null , '100');
insert into MySchedule values ( 'MyEvent206', '2016-01-09 06:06:06.111', '2016-01-09 07:07:07.222', null , null , null , '101');
insert into MySchedule values ( 'MyEvent107', '2016-01-10 07:07:07.000', '2016-01-10 08:08:08.111', null , null , null , '100');
insert into MySchedule values ( 'MyEvent208', '2016-01-11 08:08:08.111', '2016-01-11 09:09:09.222', null , null , null , '101');
insert into MySchedule values ( 'MyEvent309', '2016-01-12 09:09:09.222', '2016-01-12 10:10:10.333', null , null , null , '102');
insert into MySchedule values ( 'MyEvent310', '2016-01-13 10:10:10.222', '2016-01-13 11:11:11.333', null , null , null , '102');
go


--create Participant data--
insert into Participant values ( '700', '100' );
insert into Participant values ( '700', '101' );
insert into Participant values ( '700', '102' );

insert into Participant values ( '701', '101' );

insert into Participant values ( '702', '100' );
insert into Participant values ( '702', '102' );

insert into Participant values ( '703', '101' );
insert into Participant values ( '704', '100' );
insert into Participant values ( '705', '101' );
insert into Participant values ( '706', '100' );
insert into Participant values ( '707', '101' );

insert into Participant values ( '708', '102' );
insert into Participant values ( '708', '100' );

insert into Participant values ( '709', '102' );
insert into Participant values ( '709', '101' );
go

 
   
  
 create procedure find_file_by_fileName ( @v_fileId  int,@v_queryString nvarchar(50))
  AS
  BEGIN
 WITH findFile (fileId, fileName_,fileType,fileSize,updateTime,userId, teamId,upperFolderId , fileLevel)AS
    (
        SELECT fileId, fileName_,fileType,fileSize,updateTime,userId, teamId,upperFolderId , 1 as level
        FROM   ShareFile
        WHERE  fileId=@v_fileId
  
        UNION ALL
  
        SELECT shr.fileId , shr.fileName_ , shr.fileType , shr.fileSize , shr.updateTime , shr.userId,shr.teamId, shr.upperFolderId, tree.fileLevel + 1
        FROM ShareFile shr INNER JOIN findFile tree
        ON shr.upperFolderId  = tree.fileId 
          
    )
    SELECT * FROM findFile
    WHERE  fileName_ LIKE '%'+@v_queryString+'%'
    and fileLevel>1
    order by fileLevel 
 END;
 GO
 -- exec find_file_by_fileName 2,'g'
  
  
   */