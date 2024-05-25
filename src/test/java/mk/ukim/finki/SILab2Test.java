package mk.ukim.finki;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    @Test
    void testEveryBranch(){
        RuntimeException ex;

        //testCase1: allItems = null, payment = any
        //Passed edges: 1-2, 2-19
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(null, 20));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        //testCase2: allItems = [(name = null, barcode = "123g45", price = any, discount = any)], payment = any
        //Passed edges: 1-3.1, 3.1-3.2, 3.2-4, 4-5, 5-6, 6-7.1, 7.1-7.2, 7.2-8, 8-7.3, 7.3-7.2, 8-9, 9-19
        Item item = new Item(null, "123g45", 10, 0);
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(new ArrayList<>(List.of(item)), 20));
        assertEquals("unknown", item.getName());
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        //testCase3: allItems = [(name = any, barcode = "0123", price = 350, discount = 0.5)], payment = 200
        //Passed edges: 1-3.1, 3.1-3.2, 3.2-4, 3.2-16, 4-6, 6-7.1, 7.1-7.2, 7.2-8, 7.2-10, 8-7.3, 7.3-7.2, 10-11, 11-14, 14-15, 15-3.3, 3.3-3.2, 16-17, 17-19
        assertTrue(SILab2.checkCart(new ArrayList<>(List.of(new Item("Desk", "0123", 350, 0.5F))), 200));

        //testCase4: allItems = [(name = any, barcode = null, price = any, discount = any)], payment = any
        //Passed edges: 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 13-19
        ex = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(new ArrayList<>(List.of(new Item("Book", null, 35, 0))), 40));
        assertTrue(ex.getMessage().contains("No barcode!"));

        //testCase5: allItems = [(name = any, barcode = any, price = 150, discount = 0)], payment = 100
        //Passed edges: 1-3.1, 3.1-3.2, 3.2-4, 3.2-16, 4-6, 6-7.1, 7.1-7.2, 7.2-8, 7.2-10, 8-7.3, 7.3-7.2, 10-12, 12-14, 14-3.3, 3.3-3.2, 16-18, 18-19
        assertFalse(SILab2.checkCart(new ArrayList<>(List.of(new Item("Pen", "54321", 150, 0))), 100));
    }

    @Test
    void testMultipleCondition(){
        //if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')

        //T && T && T
        Item item_1 = new Item("Sneakers", "0123", 350, 0.1F);
        assertTrue(SILab2.checkCart(new ArrayList<>(List.of(item_1)), 30)); // pretpostavuvame deka payment >= (350 * 0.1) - 30

        //F && X && X
        Item item_2 = new Item("Backpack", "01234", 300, 0.1F);
        assertTrue(SILab2.checkCart(new ArrayList<>(List.of(item_2)), 30)); //pretpostavuvame deka payment >= (300 * 0.1)

        //X && F && X
        Item item_3 = new Item("Lamp", "012345", 350, 0);
        assertFalse(SILab2.checkCart(new ArrayList<>(List.of(item_3)), 30)); //pretpostavuvame deka payment < 350

        //X && X && F
        Item item_4 = new Item("Bed", "123456", 350, 0.1F);
        assertFalse(SILab2.checkCart(new ArrayList<>(List.of(item_4)), 30)); //pretpostavuvame deka payment e < (350 * 0.1)
    }
}