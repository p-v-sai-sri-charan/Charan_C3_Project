import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    private TimeProvider timeProvider;

    @BeforeEach
    public void setUpRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = LocalTime.parse("13:00:00");
        // LocalTime currentTime = timeProvider.getCurrentTime();
        LocalTime openingTime = LocalTime.parse("13:00:00");
        LocalTime closingTime= LocalTime.parse("13:00:00");
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
    }

    // OPEN/CLOSED TESTS
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        // WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Test Restaurant", "Test City", openingTime, closingTime);
        boolean isOpen = restaurant.isRestaurantOpen();
        assertTrue(isOpen);

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        // WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Test Restaurant", "Test City", openingTime, closingTime);
        LocalTime currentTime = LocalTime.parse("23:00:00");
        boolean isOpen = restaurant.isRestaurantOpen();
        assertFalse(isOpen);
        

    }

    // MENU TESTS
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        setUpRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        setUpRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        setUpRestaurant();
        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
}
