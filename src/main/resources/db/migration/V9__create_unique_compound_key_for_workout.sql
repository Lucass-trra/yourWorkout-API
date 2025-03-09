ALTER TABLE public.workout
ADD CONSTRAINT "compound_key_userId_name" UNIQUE(user_id, workout_name)