import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        //Test 4: คำนวณในกรณีที่ใช้ส่วนลด BOGO
        ArrayList<CartItem> BOGOCart = new ArrayList<>();
        BOGOCart.add(new CartItem("BOGO", "Bread", 25.0, 4)); // จาก100ลดเหลือ50
        BOGOCart.add(new CartItem("BOGO", "Milk", 15.0, 3));      // 45ลดเหลือ30
        double total4 = ShoppingCartCalculator.calculateTotalPrice(BOGOCart);
        if (total4 == 80) {
            System.out.println("PASSED: BOGO cart total is correct (80.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 80.0 but got " + total4);
            failedCount++;
        }
        
        //Test 5: คำนวณในกรณีที่ใช้ส่วนลด BULK เเละตรงเงื่อไข
        ArrayList<CartItem> doBULKCart = new ArrayList<>();
        doBULKCart.add(new CartItem("BULK", "Bread", 25.0, 6)); // 150ลด10%เหลือ135
        doBULKCart.add(new CartItem("BULK", "Milk", 15.0, 7));      // 105 ลด10%เหลือ94.5
        double total5 = ShoppingCartCalculator.calculateTotalPrice(doBULKCart);
        if (total5 == 229.5) {
            System.out.println("PASSED: BULK cart total is correct (229.5)");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart total expected 229.5 but got " + total5);
            failedCount++;
        }
        //Test 9: คำนวณในกรณีที่ใช้ส่วนลด BULK เเละไม่ตรงเงื่อนไข (หนูเขียนเพราะ พอดีตอนรันเทส6เเล้วมันติดexceptionมันบอกว่าindexเกิน) หนูเลยต้อง.size()-1เเต่หนูก็ยังไม่ได้เช็คมากกว่านี้กว่ามันจะได้มั้ยถ้าข้อมูลมากกว่านี้
        ArrayList<CartItem> don2BULKCart = new ArrayList<>();
        don2BULKCart.add(new CartItem("BULK", "Bread", 25.0, 3)); // 75
        don2BULKCart.add(new CartItem("BULK", "Milk", 15.0, 2));      // 30
        double total9 = ShoppingCartCalculator.calculateTotalPrice(don2BULKCart);
        if (total9 == 105.0) {
            System.out.println("PASSED: Simple cart total is correct (105.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 105.0 but got " + total9);
            failedCount++;
        }
        //Test 6: คำนวณในกรณีที่ใช้ส่วนลด BULK เเละไม่ตรงเงื่อนไข
        ArrayList<CartItem> donBULKCart = new ArrayList<>();
        donBULKCart.add(new CartItem("BULK", "Bread", 25.0, 6)); // 150ลด10%เหลือ135
        donBULKCart.add(new CartItem("BULK", "Milk", 15.0, 2));      // 30
        double total6 = ShoppingCartCalculator.calculateTotalPrice(donBULKCart);
        if (total6 == 165.0) {
            System.out.println("PASSED: BULK and Simple cart total is correct (165.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 165.0 but got " + total6);
            failedCount++;
        }

         //Test 7: คำนวณในกรณีที่ใช้ส่วนลด BULK เเละBOGO โดยตรงเงื่อนไขทั้งคู่
        ArrayList<CartItem> doBUGOCart = new ArrayList<>();
        simpleCart.add(new CartItem("BOGO", "Bread", 25.0, 4)); // 100 เหลือ50
        simpleCart.add(new CartItem("BULK", "Milk", 15.0, 6));      // 90 ลด10% เหลือ81
        double total7 = ShoppingCartCalculator.calculateTotalPrice(doBUGOCart);
        if (total7 == 131.0) {
            System.out.println("PASSED: BOGO and BULK cart total is correct (131.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO and BULK cart total expected 131.0 but got " + total7);
            failedCount++;
        }
        
         //Test 8: คำนวณในกรณีที่ใช้ส่วนลด BULK เเละBOGO โดยที่BULK ไม่ตรงเงื่อไข
        ArrayList<CartItem> donBUGOCart = new ArrayList<>();
        simpleCart.add(new CartItem("BOGO", "Bread", 25.0, 4)); // 100 เหลือ50
        simpleCart.add(new CartItem("BULK", "Milk", 15.0, 4));      // 90 
        double total8 = ShoppingCartCalculator.calculateTotalPrice(donBUGOCart);
        if (total8 == 140.0) {
            System.out.println("PASSED: BOGO and BULK cart total is correct (140.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO and BULK cart total expected 140.0 but got " + total8);
            failedCount++;
        }
        
        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}