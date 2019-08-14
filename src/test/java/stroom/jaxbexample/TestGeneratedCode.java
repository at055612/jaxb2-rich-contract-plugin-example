package stroom.jaxbexample;

import org.junit.Assert;
import org.junit.Test;
import stroom.jaxbexample.gen.BikeComplexType;
import stroom.jaxbexample.gen.CarComplexType;
import stroom.jaxbexample.gen.Transport;

public class TestGeneratedCode {

    @Test
    public void testAddBikeAndCar() {

        // This shouldn't be possible as it is a choice so would expect an exception
        // on build() or for it to take the last vehicle set.
        Transport transport = Transport.builder()
                .withBike(BikeComplexType.builder()
                        .withFrameSize("58")
                        .build())
                .withCar(CarComplexType.builder()
                        .withEngineSize("2.0")
                        .build())
                .build();

        Vehicle vehicle = transport.getVehicle();

        Assert.assertTrue(vehicle instanceof CarComplexType);
    }

    @Test
    public void testAddBike() {

        Transport transport = Transport.builder()
                .withBike(BikeComplexType.builder()
                        .withFrameSize("58")
                        .build())
                .build();

        Vehicle vehicle = transport.getVehicle();

        Assert.assertTrue(vehicle instanceof BikeComplexType);
    }

    @Test
    public void testAddVehicle() {

        Transport transport = Transport.builder()
                .withVehicle(BikeComplexType.builder()
                        .withFrameSize("58")
                        .build())
                .build();

        Vehicle vehicle = transport.getVehicle();

        Assert.assertTrue(vehicle instanceof BikeComplexType);
    }
}
