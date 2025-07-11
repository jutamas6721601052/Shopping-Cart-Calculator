import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เป็นฟังก์ชันสำหรับคำนวณราคาสินค้า โดย
     * - จะreturn 0 หาก items เป็น null เเละ empty
     * - จะreturn 0 หาก CartItem มี quantity ติดลบ
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1) ex.ถ้าซื้อสินค้า2ชิ้นจะฟรี1ชิ้น ซื้อ3ชิ้นจะฟรี1ชิ้น เเละถ้าซื้อ4ชิ้นจะฟรี2ชิ้น ดังนั้น การซื้อสินค้าจำนวน3หรือ4ชิ้นจะจ่ายในราคาเท่ากัน
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%) หากจำนวนสินค้าน้อยกว่า6ชิ้นจะคิดราคาสินค้าตามปกติโดยไม่มีการใช้ส่วนลด
     * @param ต้องใส่ข้อมูล CartItem (String sku, String name, double price, int quantity)
     * @return ค่าที่ได้กลับมาจากฟังก์ชันคือราคาสินค้าทั้งหมดในตะกร้า
     */
    
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
       int j=0;
       double total=0;
        if(items==null) return 0.0; //Test1 ตกกรณีนี้
        if(items.isEmpty()==true) return 0.0; //Test2 ตกกรณีนี้
        
        for(CartItem c : items){
           j++;
           if(c.quantity()>0){
            if(c.sku()=="NORMAL") total+=c.price()*c.quantity();
               if(c.sku()=="BOGO"){
                  if(c.quantity()%2==0) total+=c.price()*c.quantity()/2;
                else total+=c.price()*(c.quantity()+1)/2;
               }
               if(c.sku()=="BULK"){
                  if(c.quantity()<6) total+=c.price()*c.quantity();
                  else total+=c.price()*c.quantity()*0.9;
               }
           }  
            else return 0.0;
        if(j==items.size()) return total;
        }
        return 0.0;
   
    }
}