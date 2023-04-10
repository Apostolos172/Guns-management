
-- create tables

CREATE TABLE "GUN" (
	"Number"	TEXT NOT NULL UNIQUE,
	"Id"	INTEGER NOT NULL,
	"Type"	TEXT NOT NULL,
	PRIMARY KEY("Id" AUTOINCREMENT)
)

CREATE TABLE "SOLDIER" (
	"Id"	INTEGER NOT NULL,
	"Name"	TEXT,
	"Surname"	TEXT NOT NULL,
	"Gun"	INTEGER NOT NULL,
	PRIMARY KEY("Id" AUTOINCREMENT)
)

CREATE TABLE "MOVEMENT" (
	"Id"	INTEGER NOT NULL,
	"Type"	TEXT NOT NULL,
	"Gun"	INTEGER NOT NULL,
	"Quartermaster"	INTEGER,
    "Rationale" TEXT NOT NULL DEFAULT "ΤΕΛΟΣ",
    "Date" TEXT NOT NULL,
	PRIMARY KEY("Id" AUTOINCREMENT),
	FOREIGN KEY("Gun") REFERENCES "GUN"("Id"),
	FOREIGN KEY("Quartermaster") REFERENCES "SOLDIER"("Id")
)

-- insert data

INSERT INTO GUN (Number, Type)
VALUES
    (substr( CAST( CAST(ABS(RANDOM()) AS INT) AS TEXT), 1, 3 ), "G3A3"),
    (substr( CAST( CAST(ABS(RANDOM()) AS INT) AS TEXT), 1, 3 ), "G3A3");

-- https://epiloges.tv/ayta-einai-ta-pio-synithismena-eponyma-stin-ellada/

INSERT INTO SOLDIER (Surname, Name, Gun)
VALUES

    ("Καραγιάννης", "Γιώργος", 4),
    ("Ιωαννίδης", "Γιώργος", 5),
    ("Οικονόμου", "Γιώργος", 6),
    ("Παπαγεωργίου", "Γιώργος", 7),
    ("Μακρής", "Γιώργος", 8),
    ("Κωνσταντινίδης", "Γιώργος", 9),
    ("Δημόπουλος", "Γιώργος", 10),
    ("Γεωργιάδης", "Γιώργος", 11),
    ("Παπαδάκης", "Γιώργος", 12),
    ("Αντωνίου", "Γιώργος", 13),
    ("Παπανικολάου", "Γιώργος", 14),
    ("Παναγιωτόπουλος", "Γιώργος", 15),
    ("Βασιλείου", "Γιώργος", 16),
    ("Γιαννόπουλος", "Γιώργος", 17),
    ("Αντωνόπουλος", "Γιώργος", 18),
    ("Λαζαρίδης", "Γιώργος", 19),
    ("Βλάχος", "Γιώργος", 20);

-- queries

-- θέλω να καταχωρίσω εγγραφή 
INSERT INTO MOVEMENT (Type, Gun, Quartermaster, Rationale, Date)
VALUES ("input", 1, 2, "ΤΕΛΟΣ", "10:27 07/04"); 

INSERT INTO MOVEMENT (Type, Gun, Quartermaster, Rationale, Date)
VALUES ("output", 2, 2, "ΠΕΡ", "10:27 07/04"); 

-- Id οπλίτη που έχω το επώνυμο
SELECT Id 
FROM SOLDIER
WHERE Surname="Παπαδημητρίου"

-- έχω το επώνυμο οπλίτη και θέλω να αντλήσω την πληροφορία για το όπλο που κατέχει
SELECT Gun 
FROM SOLDIER
WHERE SOLDIER.Id=(
    SELECT Id 
    FROM SOLDIER
    WHERE Surname="Παπαδημητρίου"
)

-- input movements
SELECT Type, Number, Quartermaster_Surname, Rationale, Date
FROM ( 
    SELECT Id, Gun, Quartermaster, Rationale, Date
    FROM MOVEMENT
    WHERE Type = "input"
) as movements JOIN (
    SELECT Id, Surname AS Quartermaster_Surname
    FROM SOLDIER
) as soldiers
ON movements.Quartermaster = soldiers.Id
JOIN (
    SELECT Id, Type, Number
    FROM GUN
) as guns
ON movements.Gun = guns.Id
ORDER BY movements.Id DESC; 

-- output movements
SELECT Type, Number, Soldier_Surname, Rationale, Date
FROM ( 
    SELECT Id, Gun, Rationale, Date
    FROM MOVEMENT
    WHERE Type = "output"
) as movements 
JOIN (
    SELECT Type, Number, Gun, Surname AS Soldier_Surname
    FROM SOLDIER 
    JOIN GUN
    ON SOLDIER.Gun = GUN.Id
) as soldiers
ON movements.Gun = soldiers.Gun
ORDER BY movements.Id DESC

-- count guns
SELECT count(*) AS guns
FROM GUN

-- ABS(inputGuns - outputGuns) AS guns

SELECT count(*) AS inputGuns
FROM MOVEMENT
WHERE Type="input"

SELECT count(*) AS outputGuns
FROM MOVEMENT
WHERE Type="output"

-- check for non valid movements 

-- για κάθε όπλο δες αν είναι έξω ή μέσα
-- ζυγός-άρτιος ο αριθμός το όπλο είναι μέσα
-- περιττός αριθμός το όπλο είναι έξω 
SELECT count(*) movements_of_the_gun_1
FROM MOVEMENT
WHERE Gun = 1

-- περιττός αριθμός το όπλο είναι έξω
-- τότε για αυτό το όπλο μπορώ να εκτελέσω είσοδο μόνο
-- ειδάλλως αν ζητηθεί έξοδος το απορρίπτω με μήνυμα

-- αν το όπλο είναι μέσα, βλέπω ζητήθηκε να βγει, το αφήνω, 
-- ζήτησε να μπει του λέω είναι ήδη μέσα.
