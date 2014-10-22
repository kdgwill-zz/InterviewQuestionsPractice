/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap03_Stacks_And_Queues;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * An animal shelter holds only dogs and cats, and operates on a strictly "first
 * in, first out" basis. People must adopt either the "oldest" (based on arrival
 * time) of all animals at the shelter, or they can select whether they would
 * prefer a dog or a cat (and will recieve the oldest animal of that type). They
 * cannot select which specific animal they would like. Create a data structure
 * to maintain this system and implement operations such as enqueue,
 * dequeueAny,dequeueDog and dequeueCat. You may use the build-in LinkedList
 * data structure.
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapThree_Quest7 {

    public static class Shelter {

        public final List<ChapThree_Quest7.Cat> cats;
        public final List<ChapThree_Quest7.Dog> dogs;

        public Shelter() {
            cats = new LinkedList<>();
            dogs = new LinkedList<>();
        }

        public boolean enqueue(ChapThree_Quest7.Animal animal) {
            if (animal instanceof ChapThree_Quest7.Cat) {
                return cats.add((ChapThree_Quest7.Cat) animal);
            } else {
                return dogs.add((ChapThree_Quest7.Dog) animal);
            }
        }

        public ChapThree_Quest7.Animal dequeueAny() {
            if (cats.isEmpty()) {
                return dequeueDog();
            } else if (dogs.isEmpty()) {
                return dequeueCat();
            }

            ChapThree_Quest7.Dog dg = dogs.get(0);
            ChapThree_Quest7.Cat ct = cats.get(0);
            ChapThree_Quest7.Animal animal = dg.getOlder(ct);
            if (animal.equals(dg)) {
                return dequeueDog();
            } else {
                return dequeueCat();
            }
        }

        public ChapThree_Quest7.Dog dequeueDog() {
            if (dogs.isEmpty()) {
                return null;
            }
            return dogs.remove(0);
        }

        public ChapThree_Quest7.Cat dequeueCat() {
            if (cats.isEmpty()) {
                return null;
            }
            return cats.remove(0);
        }
    }

    public static void main(String[] args) {
        ChapThree_Quest7.Shelter shelter = new ChapThree_Quest7.Shelter();
        shelter.enqueue(new ChapThree_Quest7.Dog("Roger"));
        shelter.enqueue(new ChapThree_Quest7.Cat("Mittens"));
        shelter.enqueue(new ChapThree_Quest7.Dog("Rex"));
        shelter.enqueue(new ChapThree_Quest7.Cat("Snowball"));
        shelter.enqueue(new ChapThree_Quest7.Cat("Fluffy"));
        shelter.enqueue(new ChapThree_Quest7.Dog("Paulie"));
        System.out.println("Any: " + shelter.dequeueAny());
        System.out.println("Cat: " + shelter.dequeueCat());
        System.out.println("Dog: " + shelter.dequeueDog());
        System.out.println("Any: " + shelter.dequeueAny());
        System.out.println("Cat: " + shelter.dequeueCat());
        System.out.println("Dog: " + shelter.dequeueDog());
    }

    public static class Dog extends Animal_Impl {

        public Dog(String dogName) {
            super(dogName);
        }

        @Override
        public String toString() {
            return "Dog" + super.toString();
        }
    }

    public static class Cat extends Animal_Impl {

        public Cat(String catName) {
            super(catName);
        }

        @Override
        public String toString() {
            return "Cat" + super.toString();
        }
    }

    public static abstract class Animal_Impl implements Animal {

        protected final String name;
        protected final Date recieved;

        public Animal_Impl(String name) {
            this.name = name;
            this.recieved = new Date(System.currentTimeMillis());
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Date getRegisrationDate() {
            return recieved;
        }

        @Override
        public Animal getOlder(Animal other) {
            int rd = getRegisrationDate().compareTo(other.getRegisrationDate());
            if (rd != 0) {
                return rd < 0 ? this : other;
            }
            //instead of using random just use time
            boolean sysRand = System.currentTimeMillis() % 2 == 0;
            return sysRand ? this : other;
        }

        @Override
        public String toString() {
            return "{" + "name=" + name + ", recieved=" + sdf.format(recieved) + '}';
        }
    }

    public static interface Animal {

        public final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");

        /**
         * Returns the Animals name
         *
         * @return
         */
        public String getName();

        /**
         * Returns when this animal was recieved at the shelter
         *
         * @return
         */
        public Date getRegisrationDate();

        /**
         * Returns whichever animal was recieved first
         *
         * @return
         */
        public Animal getOlder(Animal other);
    }
}
