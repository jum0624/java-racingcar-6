package racingcar.domain;

import racingcar.util.RandomUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getSize() {
        return cars.size();
    }

    public void moveCars() {
        List<Car> carList = this.getCars();
        carList.forEach(car -> {
            int random = RandomUtil.createRandom();
            if (isMove(random)) {
                car.accountDistance();
            }
        });
    }

    public void printMoves() {
        for (Car car : cars) {
            car.printMove();
            System.out.println();
        }
        System.out.println();
    }

    public List<String> findWinner() {
        return cars.stream()
                .filter(car -> car.getDistance() == getMaxDistance())
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public String showWinner() {
        List<String> winner = findWinner();
        return String.join(", ", winner);
    }

    public int getMaxDistance() {
        int maxDistance = 0;
        for (int i = 0; i < cars.size(); i++) {
            maxDistance = Math.max(maxDistance, cars.get(i).getDistance());
        }
        return maxDistance;
    }

    public boolean isMove(int random) {
        if (random < 4) {
            return false;
        }
        return true;
    }
}
