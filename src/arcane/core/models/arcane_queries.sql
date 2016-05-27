-- name: all-responses
-- Selects all responses
SELECT id
       ,name
       ,phone
       ,email
       ,place
       ,secret
FROM responses;

-- name: insert-response<!
-- Queries a single response
INSERT INTO responses (name, phone, email, place)
    VALUES (:name, :phone, :email, :place);

-- name: drop-responses-table!
-- drop the responses table
DROP TABLE responses;

-- name: create-responses-table-if-not-exists!
-- create the responses table if it does not exist
CREATE TABLE IF NOT EXISTS responses (
   id serial PRIMARY KEY,
   name VARCHAR (20) NOT NULL,
   phone VARCHAR (14) NOT NULL,
   email VARCHAR (25) NOT NULL,
   place VARCHAR (20) NOT NULL,
   secret INTEGER NOT NULL DEFAULT 1
   );

-- name: delete-response<!
-- Deletes a single response
DELETE FROM responses
    WHERE id = :id;

-- name: update-response<!
-- Update a single response
UPDATE responses SET name = :name, phone = :phone, email = :email, place = :place, secret = :secret
    WHERE id = :id;