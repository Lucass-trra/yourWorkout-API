CREATE TABLE public.workout_exercise (
    workout_id INT NOT NULL,
    exercise_id INT NOT NULL,
    sets SMALLINT NOT NULL,
    repeats SMALLINT NOT NULL,
    weight SMALLINT NOT NULL,
    rest_between_sets TIME,

    PRIMARY KEY (workout_id, exercise_id),
    CONSTRAINT fk_workout FOREIGN KEY (workout_id) REFERENCES workout (workout_id) ON DELETE CASCADE,
    CONSTRAINT fk_exercise FOREIGN KEY (exercise_id) REFERENCES exercise (exercise_id) ON DELETE CASCADE
);
