<Query Kind="SQL">
  <Connection>
    <ID>811174b9-ee17-4fd6-a68a-2c3db9782a3e</ID>
    <Persist>true</Persist>
    <Server>rjmm13b7xj.database.windows.net</Server>
    <SqlSecurity>true</SqlSecurity>
    <Database>TwentyWork</Database>
    <UserName>starbooksuser@rjmm13b7xj</UserName>
    <Password>AQAAANCMnd8BFdERjHoAwE/Cl+sBAAAAeFE78T7uAECEvU6TGEvmuAAAAAACAAAAAAAQZgAAAAEAACAAAACaja/1h0oXmFnwumjafZIaX6hyuAqHXnoQYRGxIFUuFgAAAAAOgAAAAAIAACAAAABK8Q5unRv1UuOVL4PceQd/Zg2GDXc+QWWP2NxInBi/gxAAAAAlAqOCUtN4FcDPxekE6CmDQAAAAGokhrtAEcXEnznazq8RXrgLcfX8u7d8NRNbvJ5enmCyHZgFL7QKSWvTeKmdG+JvQjQ2//fdyANKDDV/APEC6ck=</Password>
    <DbVersion>Azure</DbVersion>
  </Connection>
</Query>

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
	notifyID varchar(32) NOT NULL PRIMARY KEY  default replace(NEWID(),'-',''),
	teamID varchar(32) references Team  (teamID),
    userID varchar(32) references Users (userID),
	sendUserID varchar(32) references Users (userID),
    fileId int references ShareFile(fileId),
	shareTime datetime not null,
	comment varchar(max) ,
	readState  varchar(5),
    
)
GO


