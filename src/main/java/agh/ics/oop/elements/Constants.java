package agh.ics.oop.elements;

import agh.ics.oop.options.MapVariant;

import static agh.ics.oop.options.MapVariant.*;

public class Constants {
    //Parametry symulacji
    //wysokość i szerokość mapy,
    public static final int MAP_WIDTH = 5;
    public static final int MAP_HEIGHT = 5;
    //wariant mapy (wyjaśnione w sekcji poniżej),
    public static final MapVariant MAP_VARIANT = KULA_ZIEMSKA;
    //startowa liczba roślin,
    public static final int START_GRASS_COUNT = 6;
    //energia zapewniana przez zjedzenie jednej rośliny,
    //liczba roślin wyrastająca każdego dnia,
    public static final int DAILY_GRASS_COUNT = 1;
    //wariant wzrostu roślin (wyjaśnione w sekcji poniżej),
    //startowa liczba zwierzaków
    public static final int START_ANIMAL_COUNT = 1;
    //startowa energia zwierzaków
    public static final int START_ANIMAL_ENERGY = 50;
    //energia konieczna, by uznać zwierzaka za najedzonego (i gotowego do rozmnażania),
    //energia rodziców zużywana by stworzyć potomka,
    public static final int BREEDING_ENERGY = 10;
    //minimalna i maksymalna liczba mutacji u potomków (może być równa 0 ),
    //wariant mutacji (wyjaśnione w sekcji poniżej),
    //długość genomu zwierzaków,
    public static final int GENOME_LENGTH = 25;
    //wariant zachowania zwierzaków (wyjaśnione w sekcji poniżej).
    //WLASNE: ilość energii zmniejszającej się każdego dnia
    public static final int DAILY_ENERGY_DECREASE = 1;
}
