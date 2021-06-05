package com.powermango.myapplication;

import java.util.HashMap;

public final class Constants {
    public static final String DEFAULT_TEXT_VALUE = "default";
    public static final int DEFAULT_INTEGER_VALUE = 0;

    public static final int EXERCISES_ARRAY_SIZE = 10;
    public static final int EXERCISES_AVAILABLE = 6;

    public static final double SCORE_INITIAL = 1.0;
    public static final double SCORE_DECREMENT = 0.25;

    public static final String TOAST_CORRECT_ANSWER = "¡Bien hecho!";
    public static final String TOAST_WRONG_ANSWER = "Intenta de nuevo";

    public enum ExerciseType {
        GENERAL_CATEGORIAS(1), GENERAL_DEFINICIONES_1(2), GENERAL_DEFINICIONES_2(3), DIPTONGO_HIATO(4),
        ESPECIALES_INTERROGATIVOS_EXCLAMATIVOS(5), ESPECIALES_MONOSILABOS(6), INTEGRADOR(7);

        private int value;
        private static HashMap map = new HashMap<>();

        ExerciseType(int value) {
            this.value = value;
        }

        static {
            for (ExerciseType exerciseType : ExerciseType.values()) {
                map.put(exerciseType.value, exerciseType);
            }
        }

        public static ExerciseType valueOf(int exerciseType) {
            return (ExerciseType) map.get(exerciseType);
        }

        //public int getValue() {
          //  return value;
        //}
    };

    public enum LessonType {
        REGLA_GENERAL(1), DIPTONGO_HIATO(2), CASOS_ESPECIALES(3);

        private int value;
        private static HashMap map = new HashMap<>();

        LessonType(int value) {
            this.value = value;
        }

        static {
            for (LessonType lessonType : LessonType.values()) {
                map.put(lessonType.value, lessonType);
            }
        }

        public static LessonType valueOf(int lessonType) {
            return (LessonType) map.get(lessonType);
        }

        public int getValue() {
          return value;
        }
    };
}
