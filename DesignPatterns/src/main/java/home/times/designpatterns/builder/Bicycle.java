package home.times.designpatterns.builder;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by engineer on 2017/4/18.
 */

public final class Bicycle {
    public static final int SHARED = 1;
    public static final int PRIVATE = 0;

    @IntDef({SHARED, PRIVATE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface bicycleType {
    }

    protected String color;
    protected String name;
    protected double charge;
    protected int number;
    protected int type;

    protected Bicycle(BicycleBuilder builder) {
        this.color = builder.color;
        this.name = builder.name;
        this.charge = builder.chager;
        this.number = builder.number;
        this.type = builder.type;
    }

    public static class BicycleBuilder {


        private String color;
        private String name;
        private double chager;
        private int number;
        private int type;

        public BicycleBuilder() {
            this.color = "黑色";
            this.name = "永久";
            this.chager = 0;
            this.number = 0;
            this.type = Bicycle.PRIVATE;
        }

        public BicycleBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public BicycleBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BicycleBuilder setCharge(double chager) {
            this.chager = chager;
            return this;
        }

        public BicycleBuilder setNumber(int number) {
            this.number = number;
            return this;
        }

        public BicycleBuilder setType(@bicycleType int type) {
            this.type = type;
            return this;
        }

        public Bicycle build(){
            return new Bicycle(this);
        }
    }

    @Override
    public String toString() {
        String typeStr= type == SHARED ? "共享单车": "私人车辆";

        return "Bicycle{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", charge=每分钟" + charge +"/元"+
                ", number=" + number +
                ", type=" + typeStr +
                '}';
    }
}
