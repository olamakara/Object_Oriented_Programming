package agh.ics.oop;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Vector2d;

public class Simulation {
    public WorldMap map;
    //Parametry symulacji
    //wysokość i szerokość mapy,
    public int mapWidth = 4;
    public int mapHeight = 4;
    //wariant mapy (wyjaśnione w sekcji poniżej),
    //startowa liczba roślin,
    //energia zapewniana przez zjedzenie jednej rośliny,
    //liczba roślin wyrastająca każdego dnia,
    //wariant wzrostu roślin (wyjaśnione w sekcji poniżej),
    //startowa liczba zwierzaków
    public int startAnimalCount = 5;
    //startowa energia zwierzaków
    public int startAnimalEnergy = 50;
    //energia konieczna, by uznać zwierzaka za najedzonego (i gotowego do rozmnażania),
    //energia rodziców zużywana by stworzyć potomka,
    //minimalna i maksymalna liczba mutacji u potomków (może być równa 0 ),
    //wariant mutacji (wyjaśnione w sekcji poniżej),
    //długość genomu zwierzaków,
    public int genomeLength = 5;
    //wariant zachowania zwierzaków (wyjaśnione w sekcji poniżej).

    public Simulation() {
        map = new WorldMap(mapWidth, mapHeight);
        generateAnimals();
        System.out.print(map.generuj());
        map.moveAnimals();
        System.out.print(map.generuj());
        map.moveAnimals();
        System.out.print(map.generuj());
        map.moveAnimals();
        System.out.print(map.generuj());
        map.moveAnimals();
        System.out.print(map.generuj());
        map.moveAnimals();
        System.out.print(map.generuj());
        map.moveAnimals();
        System.out.print(map.generuj());
        map.moveAnimals();
        System.out.print(map.generuj());
    }

    public void generateAnimals() {
        for(int i = 0; i < startAnimalCount; i++) {
            generateAnimalRandomly();
        }
    }

    public void generateAnimalRandomly() {
        Animal animal = new Animal(map, Vector2d.random(new Vector2d(mapWidth, mapHeight)),
                startAnimalEnergy, genomeLength);
        map.addAnimal(animal);
    }
}
