import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เป็นฟังก์ชันสำหรับคำนวณราคาสินค้า โดย
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     * - จะทำอย่างไรถ้า CartItem มี price หรือ quantity ติดลบ?
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1)
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%)
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        if(items==null) return 0.0; //Test1 ตกกรณีนี้
        if(items.isEmpty()==true) return 0.0; //Test2 ตกกรณีนี้
        
        for(int i=0;i<items.size();i++){ //Test3 ตกกรณีนี้
            if((items.get(i).sku()=="NORMAL")&&(items.get(i+1).sku()=="NORMAL")){
             return (items.get(i).price()*items.get(i).quantity())+(items.get(i+1).price()*items.get(i+1).quantity());
            }
        }
        //Test4 ตกloopนี้
        for(int i=0;i<items.size();i++){ 
            if((items.get(i).sku()=="BOGO")&&(items.get(i+1).sku()=="BOGO")){
            if(items.get(i).quantity()%2==0){
                if(items.get(i+1).quantity()%2==0)
            return (items.get(i).price()*items.get(i).quantity())/2+(items.get(i+1).price()*items.get(i+1).quantity())/2; //เช็คว่าจำนวนสินค้าเป็นเลขคู่ทุกค่ามั้ย ถ้าเป็นนำราคา*จำนวนสินค้า หารด้วย2 ตามโปรซื้อ1เเถม1
            else return (items.get(i).price()*items.get(i).quantity())/2+(items.get(i+1).price()*(items.get(i+1).quantity()+1))/2; //ถ้าไม่เป็นนำราคา*จำนวนสินค้า+1(ค่าที่จำนวนเป็นเลขคี่) หารด้วย2 ex.ซื้อ3จ่าย2นับเป็นกรณีเดียวกับ4จ่าย2
            }else if(items.get(i+1).quantity()%2==0) return (items.get(i).price()*items.get(i).quantity()+1)/2+(items.get(i+1).price()*items.get(i+1).quantity())/2;
            else return (items.get(i).price()*items.get(i).quantity()+1)/2+(items.get(i+1).price()*(items.get(i+1).quantity()+1))/2;
            }
        }
        //Test5,6,9 อาจตกloopนี้ ส่วนหนูอาจสอบตกค่ะ
        for(int i=0;i<items.size()-1;i++){ 
           if((items.get(i).sku()=="BULK")&&(items.get(i+1).sku()=="BULK")){
           if(items.get(i).quantity()>=6){
           if(items.get(i+1).quantity()>=6) return (items.get(i).price()*items.get(i).quantity())*0.9+(items.get(i+1).price()*items.get(i+1).quantity())*0.9;
           else return (items.get(i).price()*items.get(i).quantity())*0.9+(items.get(i+1).price()*items.get(i+1).quantity());
           }else if(items.get(i+1).quantity()>=6) return (items.get(i).price()*items.get(i).quantity())+(items.get(i+1).price()*items.get(i+1).quantity())*0.9;
            else return (items.get(i).price()*items.get(i).quantity())+(items.get(i+1).price()*items.get(i+1).quantity());
           }
        }
    return 1.0;
    }
}