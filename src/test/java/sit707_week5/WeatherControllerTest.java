package sit707_week5;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static double[] retrievedTemperatures;

    @BeforeClass
    public static void setUp() {
        wController = WeatherController.getInstance();
        int nHours = wController.getTotalHours();
        retrievedTemperatures = new double[nHours];
        for (int i = 0; i < nHours; i++) {
            retrievedTemperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDown() {
        wController.close();
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "s223148345"; 
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Bharat Bhatt"; 
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTemperatureMin() {
        double minTemperature = Double.MAX_VALUE;
        for (double temperature : retrievedTemperatures) {
            if (minTemperature > temperature) {
                minTemperature = temperature;
            }
        }
        Assert.assertEquals("Cached min temperature does not match calculated min temperature",
                            wController.getTemperatureMinFromCache(), minTemperature, 0.0);
    }

    @Test
    public void testTemperatureMax() {
        double maxTemperature = Double.MIN_VALUE;
        for (double temperature : retrievedTemperatures) {
            if (maxTemperature < temperature) {
                maxTemperature = temperature;
            }
        }
        Assert.assertEquals("Cached max temperature does not match calculated max temperature",
                            wController.getTemperatureMaxFromCache(), maxTemperature, 0.0);
    }

    @Test
    public void testTemperatureAverage() {
        double sum = 0;
        for (double temperature : retrievedTemperatures) {
            sum += temperature;
        }
        double calculatedAverage = sum / retrievedTemperatures.length;
        Assert.assertEquals("Cached average temperature does not match calculated average temperature",
                            wController.getTemperatureAverageFromCache(), calculatedAverage, 0.01);
    }
}
