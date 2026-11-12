package org.vidya;

public class Main {
    static void main() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new MotorCycle());
        parkingLot.park(new Van());
        parkingLot.park(new Van());
        parkingLot.park(new Van());
        parkingLot.park(new Van());
        parkingLot.park(new Van());
        parkingLot.park(new Van());

        System.out.println(parkingLot);
    }
}


class ParkingLot {
    Spot[] parkingLot = new Spot[]{
            new SmallSpot(),
            new SmallSpot(),
            new SmallSpot(),
            new MediumSpot(),
            new MediumSpot(),
            new MediumSpot(),
            new MediumSpot(),
            new MediumSpot(),
            new MediumSpot(),
            new MediumSpot(),
            new LargeSpot(),
            new LargeSpot()
    };

    boolean park(Vehicle vehicle) {
        for(Spot spot: parkingLot) {
            if(spot.isEmpty() && spot.fits(vehicle)) {
                spot.available = false;
                return true;
            }
        }

        if(vehicle instanceof Van) {
            int count = 0;
            for(int i =0; i<parkingLot.length; ++i) {
                Spot spot = parkingLot[i];
                if(spot instanceof MediumSpot && spot.available) {
                    count++;
                }
                else{
                    count = 0;
                }
                if(count == 3) {
                    parkingLot[i].available = false;
                    parkingLot[i-1].available = false;
                    parkingLot[i-2].available = false;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        int[] smallSpots = new int[2];
        int[] mediumSpots = new int[2];
        int[] largeSpots = new int[2];

        for(Spot spot: parkingLot) {
            if(spot instanceof SmallSpot) {
                smallSpots[0]++;
                if(spot.available)
                    smallSpots[1]++;
            }
            else if(spot instanceof MediumSpot) {
                mediumSpots[0]++;
                if(spot.available)
                    mediumSpots[1]++;
            }
            else if(spot instanceof LargeSpot) {
                largeSpots[0]++;
                if(spot.available)
                    largeSpots[1]++;
            }
        }

        return String.format("Total Spots: %d \n" +
                "Small Spots: %d/%d\n" +
                "Medium Spots: %d/%d\n" +
                "Large Spots: %d/%d\n",
                parkingLot.length,
                smallSpots[1],smallSpots[0],
                mediumSpots[1],mediumSpots[0],
                largeSpots[1],largeSpots[0]
                );
    }
}

abstract class Spot {
    boolean available = true;

    boolean isEmpty(){
        return this.available;
    }

    abstract boolean fits( Vehicle vehicle );
}

class SmallSpot extends Spot {
    @Override
    boolean fits(Vehicle vehicle) {
        return vehicle instanceof MotorCycle;
    }
}

class MediumSpot extends Spot {
    @Override
    boolean fits(Vehicle vehicle) {
        return vehicle instanceof MotorCycle || vehicle instanceof Car;
    }
}

class LargeSpot extends Spot {
    @Override
    boolean fits(Vehicle vehicle) {
        return vehicle instanceof MotorCycle || vehicle instanceof Car || vehicle instanceof Van;
    }
}

class Vehicle {

}

class MotorCycle extends Vehicle {

}

class Car extends Vehicle {

}

class Van extends Vehicle {

}