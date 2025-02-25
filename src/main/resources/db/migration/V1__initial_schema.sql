-- ************************* SqlDBM: PostgreSQL *************************
-- *** Generated by SqlDBM: yourWorkout by lucasterracontato@gmail.com **


-- ************************************** "public"."User"
CREATE TABLE "public"."User"
(
 user_id           serial NOT NULL,
 username          varchar(50) NOT NULL,
 email             varchar(100) NOT NULL,
 password          varchar(16) NOT NULL,
 sex               varchar(20) NULL,
 dateOfBirth       date NULL,
 levelOfExperience varchar(20) NULL,
 height            smallint NULL,
 weight            smallint NULL,
 workoutGoal       varchar(50) NULL,
 CONSTRAINT PK_1 PRIMARY KEY ( user_id )
);

COMMENT ON TABLE "public"."User" IS 'Os usuários poderão ter seus próprios treinos, sendo que um usuário poderá ter vários treinos';

COMMENT ON COLUMN "public"."User".user_id IS 'identificador único da pessoa';
COMMENT ON COLUMN "public"."User".username IS 'Esse campo referencia o nome que o usuário vai usar para ser referenciado no sistema';
COMMENT ON COLUMN "public"."User".email IS 'Referencia o email que será utilizado para poder haver futuras comunicações e para validações de login';
COMMENT ON COLUMN "public"."User".password IS 'Senha criada pelo usuário usada para que o mesmo possa fazer o login no sistema.';
COMMENT ON COLUMN "public"."User".sex IS 'Gênero da pessoa';
COMMENT ON COLUMN "public"."User".dateOfBirth IS 'data de nascimento da pessoa';
COMMENT ON COLUMN "public"."User".levelOfExperience IS 'Nível de experiência de treino da pessoa referenciada como
- iniciante
- intermediário
- avançado';
COMMENT ON COLUMN "public"."User".height IS 'altura da pessoa';
COMMENT ON COLUMN "public"."User".weight IS 'peso da pessoa';
COMMENT ON COLUMN "public"."User".workoutGoal IS 'Objetivo específico da pessoa que determina qual tipo de treino seria o mais viável';

CREATE TABLE "public".Workout
(
 workout_id   serial NOT NULL,
 user_id      int NOT NULL,
 workout_name varchar(100) NOT NULL,
 isCurrent    boolean NOT NULL,
 description  varchar(1000) NULL,
 CONSTRAINT PK_2 PRIMARY KEY ( workout_id ),
 CONSTRAINT FK_1 FOREIGN KEY ( user_id ) REFERENCES "public"."User" ( user_id )
);

COMMENT ON TABLE "public".Workout IS 'Cada treino pertence a apenas um usuário, e cada treino pode ter vários exercícios.';

COMMENT ON COLUMN "public".Workout.workout_id IS 'Identificador único do treino';
COMMENT ON COLUMN "public".Workout.user_id IS 'identificador único do usuário que possui esse treino';
COMMENT ON COLUMN "public".Workout.workout_name IS 'nome do treino';
COMMENT ON COLUMN "public".Workout.isCurrent IS 'Diz se um treino faz parte dos treinos atuais que estão sendo realizados no momento pela pessoa';
COMMENT ON COLUMN "public".Workout.description IS 'Descrição sobre as informações específicas do treino';

CREATE TABLE "public".Exercise
(
 exercise_id     serial NOT NULL,
 workout_id      int NOT NULL,
 exercise_name   varbit(50) NOT NULL,
 photo           bytea NOT NULL,
 bodyPart_id     integer NOT NULL,
 equipment       varchar(100) NOT NULL,
 target          varchar(100) NOT NULL,
 instructions    jsonb NOT NULL,
 sets            smallint NULL,
 repeats         smallint NULL,
 weight          smallint NULL,
 restBetweenSets time NULL,
 CONSTRAINT PK_3 PRIMARY KEY ( exercise_id ),
 CONSTRAINT FK_1 FOREIGN KEY ( workout_id ) REFERENCES "public".Workout ( workout_id )
);

COMMENT ON TABLE "public".Exercise IS 'Um exercício precisa pertencer a apenas um treino montado pelo usuário.
Pode ocorrer de mais de um exercício ter os mesmos grupos musculares secundários ativados, ou pelo menos um grupo secundário, com isso é preciso existir uma tabela associativa entre a parte do corpo e o exercício.';

COMMENT ON COLUMN "public".Exercise.exercise_id IS 'Identificador único do exercício';
COMMENT ON COLUMN "public".Exercise.workout_id IS 'Identificador único do treino ao qual esse exercício pertence';
COMMENT ON COLUMN "public".Exercise.exercise_name IS 'nome do exercício';
COMMENT ON COLUMN "public".Exercise.photo IS 'GIF de um boneco modelo realizando o exercício para ficar mais fácil entender como fazer o exercício';
COMMENT ON COLUMN "public".Exercise.bodyPart_id IS 'Referencia o grupo muscular principal ativado por esse exercício';
COMMENT ON COLUMN "public".Exercise.equipment IS 'Equipamento necessário para realizar o exercício.';
COMMENT ON COLUMN "public".Exercise.target IS 'Alvo principal mais específico dentro do grupo muscular principal ativado.
Ex:
o grupo principal ativado poderia ser costas, mas o alvo seria uma região mais específica como o trapézio.';
COMMENT ON COLUMN "public".Exercise.instructions IS 'Lista de instruções necessárias para realizar um exercício, cada exercício tem somente uma forma específica de ser realizado, e cada instrução pertence a apenas um exercício.';
COMMENT ON COLUMN "public".Exercise.sets IS 'Quantidade de séries';
COMMENT ON COLUMN "public".Exercise.repeats IS 'quantidade de repetições';
COMMENT ON COLUMN "public".Exercise.weight IS 'Peso usado para realizar o exercício';
COMMENT ON COLUMN "public".Exercise.restBetweenSets IS 'Tempo de descanso utilizado entre as séries';

CREATE TABLE "public".BodyPart
(
 bodyPart_id   serial NOT NULL,
 bodypart_name varchar(50) NOT NULL,
 CONSTRAINT PK_4 PRIMARY KEY ( bodyPart_id )
);

COMMENT ON TABLE "public".BodyPart IS 'Tabela responsável por armazenar todas as partes do corpo que podem ser ativadas por um exercício.';

COMMENT ON COLUMN "public".BodyPart.bodyPart_id IS 'Identificador único do grupo muscular.';
COMMENT ON COLUMN "public".BodyPart.bodypart_name IS 'Nome do grupo muscular que pode ser ativado por um exercício';


CREATE TABLE "public".Exercise_bodyPart_secundary
(
 exercise_id    integer NOT NULL,
 bodypart_id    integer NOT NULL,
 fk_exercise_id int NOT NULL,
 fk_bodyPart_id int NOT NULL,
 CONSTRAINT PK_5 PRIMARY KEY ( exercise_id, bodypart_id ),
 CONSTRAINT FK_2 FOREIGN KEY ( fk_bodyPart_id ) REFERENCES "public".BodyPart ( bodyPart_id ),
 CONSTRAINT FK_exercise_id FOREIGN KEY ( fk_exercise_id ) REFERENCES "public".Exercise ( exercise_id ) ON DELETE CASCADE
);

COMMENT ON TABLE "public".Exercise_bodyPart_secundary IS 'Tabela associativa que conecta as informações das tabelas de exercícios e parte do corpo ativada para esse exercício.
Como um exercício pode ter mais de um grupo muscular secundário usado e um grupo muscular secundário pode ser o mesmo para outro exercício, essa tabela associativa é necessária.';

COMMENT ON COLUMN "public".Exercise_bodyPart_secundary.exercise_id IS 'Identificador único do exercício';
COMMENT ON COLUMN "public".Exercise_bodyPart_secundary.bodypart_id IS 'Identificador único do grupo muscular.';
COMMENT ON COLUMN "public".Exercise_bodyPart_secundary.fk_exercise_id IS 'Chave estrangeira que referencia o ID do exercício';
COMMENT ON COLUMN "public".Exercise_bodyPart_secundary.fk_bodyPart_id IS 'Chave estrangeira que referencia o ID do grupo muscular';



