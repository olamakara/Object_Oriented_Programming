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
    public static final int MAP_WIDTH = 50;
    public static final int MAP_HEIGHT = 50;
    //wariant mapy (wyjaśnione w sekcji poniżej),
    public static final MapVariant MAP_VARIANT = PIEKIELNY_PORTAL;
    //startowa liczba roślin,
    public static final int START_GRASS_COUNT = 500;
    //energia zapewniana przez zjedzenie jednej rośliny,
    public static final int GRASS_EATEN_ENERGY = 15;
    //liczba roślin wyrastająca każdego dnia,
    public static final int DAILY_GRASS_COUNT = 10;
    //wariant wzrostu roślin (wyjaśnione w sekcji poniżej),
    public static final PuszczaVariant PUSZCZA_VARIANT = ZALESIONE_ROWNIKI;
    //startowa liczba zwierzaków
    public static final int START_ANIMAL_COUNT = 40;
    //startowa energia zwierzaków
    public static final int START_ANIMAL_ENERGY = 50;
    //energia konieczna, by uznać zwierzaka za najedzonego (i gotowego do rozmnażania),
    public static final int FULL_ANIMAL_ENERGY = 30;
    //energia rodziców zużywana by stworzyć potomka,
    public static final int BREEDING_ENERGY = 10;
    //minimalna i maksymalna liczba mutacji u potomków (może być równa 0 ),
    public static final int MINIMUM_MUTATIONS = 0;
    public static final int MAXIMUM_MUTATIONS = 0;
    //wariant mutacji (wyjaśnione w sekcji poniżej),
    public static final GeneVariant GENE_VARIANT = LEKKA_KOREKTA;
    //długość genomu zwierzaków,
    public static final int GENOME_LENGTH = 5;
    //wariant zachowania zwierzaków (wyjaśnione w sekcji poniżej).
    public static final BehaviourVariant GENE_JUMP_VARIANT = PELNA_PROKRASTYNACJA;
    //WLASNE: ilość energii zmniejszającej się każdego dnia
    public static final int DAILY_ENERGY_DECREASE = 1;
    //WLASNE: opoznienie jednego dnia symulacji
    public static final int DAY_DELAY = 50;
    //WLASNE: ustawienia dot. siatki grid
    public static final int UI_GRID_SIZE = 16;
    public static final int UI_BOX_SIZE = 16;
    public static final int UI_ANIMAL_SIZE = 11;
    public static final boolean GRIDLINES_VISIBLE = false;
}