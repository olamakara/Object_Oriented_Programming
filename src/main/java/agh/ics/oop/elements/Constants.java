package agh.ics.oop.elements;

import agh.ics.oop.options.MapVariant;

import static agh.ics.oop.options.MapVariant.*;

public class Constants {
    //Parametry symulacji
    //wysokość i szerokość mapy,
    public static final int MAP_WIDTH = 30;
    public static final int MAP_HEIGHT = 30;
    //wariant mapy (wyjaśnione w sekcji poniżej),
    public static final MapVariant MAP_VARIANT = KULA_ZIEMSKA;
    //startowa liczba roślin,
    public static final int START_GRASS_COUNT = 3;
    //energia zapewniana przez zjedzenie jednej rośliny,
    public static final int GRASS_EATEN_ENERGY = 50;
    //liczba roślin wyrastająca każdego dnia,
    public static final int DAILY_GRASS_COUNT = 1;
    //wariant wzrostu roślin (wyjaśnione w sekcji poniżej),
    //startowa liczba zwierzaków
    public static final int START_ANIMAL_COUNT = 3;
    //startowa energia zwierzaków
    public static final int START_ANIMAL_ENERGY = 50;
    //energia konieczna, by uznać zwierzaka za najedzonego (i gotowego do rozmnażania),
    public static final int FULL_ANIMAL_ENERGY = 75;
    //energia rodziców zużywana by stworzyć potomka,
    public static final int BREEDING_ENERGY = 10;
    //minimalna i maksymalna liczba mutacji u potomków (może być równa 0 ),
    //wariant mutacji (wyjaśnione w sekcji poniżej),
    //długość genomu zwierzaków,
    public static final int GENOME_LENGTH = 35;
    //wariant zachowania zwierzaków (wyjaśnione w sekcji poniżej).
    //WLASNE: ilość energii zmniejszającej się każdego dnia
    public static final int DAILY_ENERGY_DECREASE = 1;
    //WLASNE: opoznienie jednego dnia symulacji
    public static final int DAY_DELAY = 20;
    public static final int UI_GRID_SIZE = 22;
    public static final int UI_BOX_SIZE = 20;
    public static final int UI_ANIMAL_SIZE = 16;
}