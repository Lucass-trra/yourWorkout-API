ALTER TABLE "User"
ADD CONSTRAINT unique_username UNIQUE (username),
ADD CONSTRAINT unique_email UNIQUE (email),
ADD CONSTRAINT unique_password UNIQUE (password);
