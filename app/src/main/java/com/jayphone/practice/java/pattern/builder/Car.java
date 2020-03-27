package com.jayphone.practice.java.pattern.builder;

/**
 * 具体产品
 * Created by JayPhone on 2020/3/27
 */
public class Car {
    private String engine;
    private String tyre;
    private String color;

    private Car(CarBuilder carBuilder) {
        this.engine = carBuilder.engine;
        this.tyre = carBuilder.tyre;
        this.color = carBuilder.tyre;
    }

    /**
     * 构造者类
     */
    public static class CarBuilder {
        private String engine;
        private String tyre;
        private String color;

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setTyre(String tyre) {
            this.tyre = tyre;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
