package com.vanseed.rhea.api.test;

public class BaseTest {  
    public static void main(String[] args){  
        B b=new B(0);       
        int y=b.getY();  
    }  
 
}  
 
class A {  
    public static int x=2; //1.      
    private int y=2;      //2.  
    protected int z;      //5.  
    A(){                      //3.  
        x=x+1;  
        showX();        //4.  
    }  
    public void showX(){  
        System.out.println("A.x="+x);  
    }  
    public int getY(){  
     return y;  
    }  
 
}  
 
class B extends A {  
      
    B(int x){  
        x=x+2;                 //只对局部x操作  
        showX();  
    }  
    public void showX(){  
        System.out.println("B.x="+x);  
    }  
    public int getY(){ //6.  
 
     System.out.println("B.y="+(super.getY()+x));  
     return super.getY()+x;  
    }  
 
}  
