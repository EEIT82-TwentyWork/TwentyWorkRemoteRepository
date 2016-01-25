<Query Kind="SQL">
  <Connection>
    <ID>da7e50f7-1cf7-4e53-85c3-84626b3b01bc</ID>
    <Persist>true</Persist>
    <Server>twentywork.database.windows.net</Server>
    <SqlSecurity>true</SqlSecurity>
    <Database>twentywork</Database>
    <UserName>twentyworkuser</UserName>
    <Password>AQAAANCMnd8BFdERjHoAwE/Cl+sBAAAA4yrylCWV90Ssw9BHCr8T0AAAAAACAAAAAAAQZgAAAAEAACAAAAC4pdVy01CdQ9Jd34pk4OXfQAQboGIAgVwzpB0M+r6uEQAAAAAOgAAAAAIAACAAAAAzq2pSmM4P2pb/54lbUDNAOcAIHDlzIZUm1G+Is6x+rBAAAACSM/3wGxyk90JmxNLMvXeYQAAAACLhomKhkd69PoBtlrNUOoBrgcZztihvQ7NwFy+WFlybLSiUyNpFzfhKY3aivDHjBnbZ1cbUq4KVtpMCXJms/Go=</Password>
    <DbVersion>Azure</DbVersion>
  </Connection>
</Query>

drop table Sub
drop table Msg
drop table GroupUser
drop table Groups
drop table Images
drop table ShareFile

drop table TeamSchedule
drop table MyFav
drop table Participant
drop table MySchedule
drop table Board
drop table BoardClass
drop table fakeTeamUser

drop table TeamUser
drop table Users
drop table Team


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
    boardClassID varchar(32) NOT NULL PRIMARY KEY  default replace(NEWID(),'-',''),
    boardClassText varchar(50),
)
GO
--12--
create table Board
(
    boardID varchar(32) NOT NULL PRIMARY KEY  default replace(NEWID(),'-',''),
    boardTitle varchar(50) not null,
    boardText varchar(max),
    boardTime datetime not null,
    userID varchar(32) references Users (userID),
    teamID varchar(32) references Team  (teamID),
    boardClassID varchar(32) references BoardClass(boardClassID),
)
GO



--13--

create table Sub
(
    subID varchar(32) NOT NULL PRIMARY KEY  default replace(NEWID(),'-',''),
    subText varchar(max),
	subTime datetime not null,
    userID varchar(32) references Users (userID),
    boardID varchar(32) references Board(boardID),
)
GO
--14--
create table MyFav
(
	teamID varchar(32) references Team  (teamID),
    userID varchar(32) references Users (userID),
    boardID varchar(32) references Board(boardID),
	activeTime datetime not null,
	favTitle varchar(50) not null,
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
CREATE TABLE fakeTeamUser(
	userId		varchar(32), 
	teamId		varchar(32), 
	activeDate	date			,
	rights		varchar(5) not null,
	CONSTRAINT FK_fakeTeamUser_userId  foreign key (userId) references Users(userID) ,
	CONSTRAINT FK_fakeTeamUser_teamId  foreign key (teamId) references Team (teamId) ,
	CONSTRAINT PK_fakeTeamUser PRIMARY KEY (userId,teamId)
);


go
/*
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
*/
 
  declare @teamId1 varchar(32) 
  set @teamId1=replace(NEWID(),'-','')
  INSERT INTO Team VALUES(@teamId1,'TinaTeam',null,null)
  declare @teamId2 varchar(32) 
  set @teamId2=replace(NEWID(),'-','')
  INSERT INTO Team VALUES(@teamId2,'QubeTeam',null,null)
  
  declare @userId1 varchar(32) 
  set @userId1=replace(NEWID(),'-','')
  INSERT INTO Users VALUES(@userId1,'Tina','tina@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
  declare @userId2 varchar(32) 
  set @userId2=replace(NEWID(),'-','')
  INSERT INTO Users VALUES(@userId2,'John','john@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
  declare @userId3 varchar(32) 
  set @userId3=replace(NEWID(),'-','')
  INSERT INTO Users VALUES(@userId3,'Andro','andro@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
  declare @userId4 varchar(32) 
  set @userId4=replace(NEWID(),'-','')
  INSERT INTO Users VALUES(@userId4,'Qube','qube@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
  declare @userId5 varchar(32) 
  set @userId5=replace(NEWID(),'-','')
  INSERT INTO Users VALUES(@userId5,'Puffy','puffy@gmail.com.tw',CAST('A' AS VARBINARY(MAX)),null,null,null,null)
 
  INSERT INTO TeamUser values(@userId1,@teamId1)
  INSERT INTO TeamUser values(@userId2,@teamId1)
  INSERT INTO TeamUser values(@userId3,@teamId1)
  INSERT INTO TeamUser values(@userId1,@teamId2)
  INSERT INTO TeamUser values(@userId4,@teamId2)
  INSERT INTO TeamUser values(@userId5,@teamId2)
  
 -- fileName_ not null, fileType  not null,   fileSize ,   updateTime , userId , teamId ,   upperFolderId   int,     
 INSERT INTO ShareFile VALUES ( 'WebApp根目錄' , '資料夾' ,null ,null,null,null, null);--900
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



declare @boardClass1 varchar(32) set @boardClass1 = replace(NEWID(),'-','')

declare @boardID1 varchar(32) set @boardID1 = replace(NEWID(),'-','')
declare @boardID2 varchar(32) set @boardID2 = replace(NEWID(),'-','')

declare @subID1 varchar(32) set @subID1 = replace(NEWID(),'-','')
declare @subID2 varchar(32) set @subID2 = replace(NEWID(),'-','')
declare @subID3 varchar(32) set @subID3 = replace(NEWID(),'-','')
declare @subID4 varchar(32) set @subID4 = replace(NEWID(),'-','')
declare @subID5 varchar(32) set @subID5 = replace(NEWID(),'-','')

insert into BoardClass values (@boardClass1,'notthing here')

insert into Board (boardID,boardTitle,boardText,boardTime,userID,teamID,boardClassID) values 
				(@boardID1,'測試1','這是測試1','2016-01-01',@userId1,@teamId1,@boardClass1)
insert into Board (boardID,boardTitle,boardText,boardTime,userID,teamID,boardClassID) values 
				(@boardID2,'測試2','這是測試2','2016-01-02',@userId2,@teamId1,@boardClass1)
insert into Sub (subID,subText,subTime,userID,boardID) values
				(@subID1,'測試的文字1','2016-01-02',@userId2,@boardID1)
				
insert into Sub (subID,subText,subTime,userID,boardID) values
				(@subID2,'測試的文字2','2016-01-03',@userId1,@boardID1)
insert into Sub (subID,subText,subTime,userID,boardID) values
				(@subID3,'測試的文字3','2016-01-04',@userId3,@boardID1)
insert into Sub (subID,subText,subTime,userID,boardID) values
				(@subID4,'woooooo2','2016-01-05',@userId3,@boardID2)
insert into Sub (subID,subText,subTime,userID,boardID) values
				(@subID5,'mowmowmowomow3','2016-01-08',@userId1,@boardID2)

insert into MyFav(teamID,userID,boardID,activeTime,favTitle) values
			(@teamId1,@userId1,@boardID1,'2016-01-22','測試1捷徑')
insert into MyFav(teamID,userID,boardID,activeTime,favTitle) values
			(@teamId1,@userId1,@boardID2,'2016-01-23','測試2捷徑')
				
				
go


select r.userID,e1.userName,r.teamID,e2.teamName
from TeamUser as r
Join Users as e1 
	on r.userID = e1.userID
Join Team as e2 
	on r.teamID = e2.teamID

select * from Board

select * from Team
select * from TeamUser
select * from Users
select * from Board
select * from Sub

/*
  
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