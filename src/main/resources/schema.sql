-- DDL script for clinic.db (SQLite)
-- JPA with ddl-auto=update creates these automatically;
-- this script documents the schema for submission.

CREATE TABLE IF NOT EXISTS doctors (
    id        INTEGER PRIMARY KEY AUTOINCREMENT,
    name      TEXT NOT NULL,
    specialty TEXT
);

CREATE TABLE IF NOT EXISTS patients (
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    name       TEXT NOT NULL,
    age        INTEGER,
    ai_summary TEXT
);

-- Join table for the many-to-many relationship
CREATE TABLE IF NOT EXISTS doctor_patient (
    patient_id INTEGER NOT NULL,
    doctor_id  INTEGER NOT NULL,
    PRIMARY KEY (patient_id, doctor_id),
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id)  REFERENCES doctors(id)
);