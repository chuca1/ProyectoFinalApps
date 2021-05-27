package com.powermango.myapplication;

import java.util.HashMap;

public final class Constants {
    public static final int EXERCISES_ARRAY_SIZE = 5;
    public static final int EXERCISES_AVAILABLE = 3;

    public enum ExerciseType {
        GENERAL_CATEGORIAS(1), GENERAL_DEFINICIONES_1(2), GENERAL_DEFINICIONES_2(3);

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

        public static ExerciseType valueOf(int pageType) {
            return (ExerciseType) map.get(pageType);
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
