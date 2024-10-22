DROP TABLE IF EXISTS Tenista;
CREATE TABLE IF NOT EXISTS Tenista (
     id INTEGER PRIMARY KEY,
     nombre TEXT NOT NULL,
     pais TEXT NOT NULL,
     altura INTEGER NOT NULL,
     peso INTEGER NOT NULL,
     puntos INTEGER NOT NULL,
     mano TEXT NOT NULL,
     fecha_nacimiento TEXT NOT NULL,
     created_at TEXT NOT NULL,
     updated_at TEXT NOT NULL
);
