package agh.ics.oop.elements;

import agh.ics.oop.options.BehaviourVariant;
import agh.ics.oop.options.GeneVariant;
import agh.ics.oop.options.MapVariant;
import agh.ics.oop.options.PuszczaVariant;

import static agh.ics.oop.options.BehaviourVariant.*;
import static agh.ics.oop.options.GeneVariant.*;
import static agh.ics.oop.options.MapVariant.*;
import static agh.ics.oop.options.PuszczaVariant.*;

public class Constants {
    //Parametry symulacji
    //wysokość i szerokość mapy,

    public static int MAP_WIDTH;
    public static int MAP_HEIGHT;
    //wariant mapy (wyjaśnione w sekcji poniżej),
    public static MapVariant MAP_VARIANT;
    //startowa liczba roślin,
    public static int START_GRASS_COUNT;
    //energia zapewniana przez zjedzenie jednej rośliny,
    public static int GRASS_EATEN_ENERGY;
    //liczba roślin wyrastająca każdego dnia,
    public static int DAILY_GRASS_COUNT;
    //wariant wzrostu roślin (wyjaśnione w sekcji poniżej),
    public static PuszczaVariant PUSZCZA_VARIANT;
    //startowa liczba zwierzaków
    public static int START_ANIMAL_COUNT;
    //startowa energia zwierzaków
    public static int START_ANIMAL_ENERGY;
    //energia konieczna, by uznać zwierzaka za najedzonego (i gotowego do rozmnażania),
    public static int FULL_ANIMAL_ENERGY;
    //energia rodziców zużywana by stworzyć potomka,
    public static int BREEDING_ENERGY;
    //minimalna i maksymalna liczba mutacji u potomków (może być równa 0 ),
    public static int MINIMUM_MUTATIONS;
    public static int MAXIMUM_MUTATIONS;
    //wariant mutacji (wyjaśnione w sekcji poniżej),
    public static GeneVariant GENE_VARIANT;
    //długość genomu zwierzaków,
    public static int GENOME_LENGTH;
    //wariant zachowania zwierzaków (wyjaśnione w sekcji poniżej).
    public static BehaviourVariant GENE_JUMP_VARIANT;
    //WLASNE: ilość energii zmniejszającej się każdego dnia
    public static int DAILY_ENERGY_DECREASE;
    //WLASNE: opoznienie jednego dnia symulacji
    public static int DAY_DELAY;
    //WLASNE: ustawienia dot. siatki grid
    public static int UI_GRID_SIZE;
    public static int UI_BOX_SIZE;
    public static int UI_ANIMAL_SIZE;
    public static boolean GRIDLINES_VISIBLE;

}