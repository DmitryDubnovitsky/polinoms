package com.bsu;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import java.util.Scanner;
import java.util.Vector;

public class Calculation {
	static Vector<String> v=new Vector<String>();
	static Vector<String>v1=new Vector<String>();
	static String[] values;
	
	public String IntToByte(int n,int razm){	

		
		
		String str=Integer.toBinaryString(n).toString();
		
		
		//System.out.println("str"+str);
		if(str.length()<razm){			
			String s="";
			for(int i=0;i<razm-str.length();i++){
				s+="0";
			}
			str=s.concat(str);
			
		}
		//System.out.println(razm +"    "+ str.length());
		if(razm<str.length()){
			str=str.substring(str.length()-razm, str.length());
		}
		
		
		
	       return str; 
	 }
	
	public void getSumm(String value,int razm,int i,int moduleValue,String answer,int minBit,String answer2,PrintWriter out1,PrintWriter out2,String answer3){
		int summa[]=new int[(int)Math.pow(2, 2*razm)];
		int mult[]=new int[(int)Math.pow(2, 2*razm)];
		int sRazmer=0;
		int mRazmer=0;
		String one,two;
		one=value.substring(0, value.length()/2);
		two=value.substring(value.length()/2,value.length());
		if(answer.equals("yes")){
		summa[i]=(Integer.parseInt(one.trim(), 2)+Integer.parseInt(two.trim(), 2))% moduleValue;
		mult[i]=(Integer.parseInt(one.trim(), 2)*Integer.parseInt(two.trim(), 2))% moduleValue;
		sRazmer=razm+1;		
		mRazmer=razm*2;
		}
		if(answer2.equals("yes")){
			summa[i]=(Integer.parseInt(one.trim(), 2)+Integer.parseInt(two.trim(), 2));		
			mult[i]=(Integer.parseInt(one.trim(), 2)*Integer.parseInt(two.trim(), 2));
			sRazmer=minBit;
			mRazmer=minBit;
					
		}
		else if(!answer.equals("yes")&& (!answer2.equals("yes"))){
			summa[i]=(Integer.parseInt(one.trim(), 2)+Integer.parseInt(two.trim(), 2));
			mult[i]=(Integer.parseInt(one.trim(), 2)*Integer.parseInt(two.trim(), 2));
			sRazmer=razm+1;
			mRazmer=razm*2;
		}
		//System.out.println(IntToByte(summa[i],sRazmer));
		/*if(answer3.equals("a")){
		out.print(IntToByte(summa[i],sRazmer)+"\r\n");
		}
		if(answer3.equals("m")){
			out.print(IntToByte(mult[i],mRazmer)+"\r\n");
			}*/
		//System.out.println(IntToByte(summa[i],mRazmer));
		//System.out.println("*********");
		//System.out.println(IntToByte(mult[i],mRazmer));
		//System.out.println("==========");
		//System.out.println("*********");
		v.add(IntToByte(summa[i],sRazmer).trim());	
		v1.add(IntToByte(mult[i],mRazmer).trim());
		
		if((i==Math.pow(2, razm*2)-1)){		
			//getPDNF(v,razm,value);
			if(answer3.equals("a")){
			getSumPNF(v,sRazmer,razm,value,out1,out2);
			}
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
			if(answer3.equals("m")){
			getMulPNF(v1,mRazmer,razm,out1,out2);
			}
		}
		//System.out.println(v);
		//System.out.println(v1);
		   }
	
	public void getMulPNF(Vector<String> v1,int mRazm,int r,PrintWriter out1,PrintWriter out2){
		Vector<Long>left=new Vector<Long>();
		Vector<Long>right=new Vector<Long>();
		long array[][]=new long[ mRazm][(int)Math.pow(2, r*2)];
		 for(int j=0;j< mRazm;j++){
		  for(int i=0;i<Math.pow(2, r*2);i++){
			array[j][i]+=v1.get(i).charAt(j)-48;
		  }
		 }	
		 for (int j=0;j<mRazm;j++){
			 left.add(array[j][0]);
			 right.add(array[j][(int)Math.pow(2, r*2)-1]);
			 for(int k=1;k<Math.pow(2, r*2);k++){
			  for(int i=0;i<Math.pow(2, r*2)-k;i++){
					 array[j][i]=array[j][i]+array[j][i+1];
					 if(array[j][i]==2){
						 array[j][i]=0; 
					 }
					 if(i==0){
						  left.add(array[j][0]);
					 }
					 if(i==Math.pow(2, r*2)-k-1){
						 right.add(array[j][(int) ((long)Math.pow(2, r*2)-k-1)]);
					 }  
				System.out.print(array[j][i]+ " ");			  									
			   }
			  
			 System.out.println();
			  }	
			 if(left.size()==Math.pow(2, r*2)*mRazm){
			for(int l=0;l<Math.pow(2, r*2);l++){
				out1.print(values[l]+" ");
				out2.print(values[l]+" ");
			 for(int i=l;i<left.size();i+=Math.pow(2, r*2)){
			   out1.print(left.get(i));
			  out2.print(right.get(i));
			 }
			 out1.print("\r\n");
			 out2.print("\r\n");
			}
			 }
			 //System.out.println(left); 
			// System.out.println(right);
			// left.clear();
			// right.clear();
			
		 }			 
	}
	
	public void getSumPNF(Vector<String> v,int sRazm,int r,String value,PrintWriter out1,PrintWriter out2){
		Vector<Long>left=new Vector<Long>();
		Vector<Long>right=new Vector<Long>();
		long array[][]=new long[sRazm][(int)Math.pow(2, r*2)];
		 for(int j=0;j<sRazm;j++){
		  for(int i=0;i<Math.pow(2, r*2);i++){
			array[j][i]+=v.get(i).charAt(j)-48;
		  }
		 }	
		 for (int j=0;j<sRazm;j++){
			 left.add(array[j][0]);
			 right.add(array[j][(int)Math.pow(2, r*2)-1]);
			 for(int k=1;k<Math.pow(2, r*2);k++){
			  for(int i=0;i<Math.pow(2, r*2)-k;i++){
					 array[j][i]=array[j][i]+array[j][i+1];
					 if(array[j][i]==2){
						 array[j][i]=0; 
					 }
					 if(i==0){
						  left.add(array[j][0]);
					 }
					 if(i==Math.pow(2, r*2)-k-1){
						 right.add(array[j][(int) ((long)Math.pow(2, r*2)-k-1)]);
					 }  
				System.out.print(array[j][i]+ " ");			  									
			   }
			  
			 System.out.println();
			  }	
			 if(left.size()==Math.pow(2, r*2)*(sRazm)){
					for(int l=0;l<Math.pow(2, r*2);l++){
						out1.print(values[l]+" ");
						out2.print(values[l]+" ");
						//System.out.println("hi!");
					 for(int i=l;i<left.size();i+=Math.pow(2, r*2)){
					   out1.print(left.get(i));
					  // System.out.println("hi2!");
					  out2.print(right.get(i));
					 }
					 out1.print("\r\n");
					 out2.print("\r\n");
					}
					 }
			 System.out.println(left);
			 System.out.println(right);
			// left.clear();
			// right.clear();
			 
			 
		
		 }			 
					 
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		Scanner sc1=new Scanner(System.in);
		Scanner sc2=new Scanner(System.in);
		Scanner sc3=new Scanner(System.in);
		  Calculation c=new Calculation();		  
		  File file1 = new File("C:\\Users\\ִלטענטי\\Desktop\\Espresso\\calculate1.txt");
		  File file2 = new File("C:\\Users\\ִלטענטי\\Desktop\\Espresso\\calculate2.txt");
	      if(!file1.exists()){
	                file1.createNewFile();
	            }
	      if(!file2.exists()){
              file2.createNewFile();
          }
	      PrintWriter out1 = new PrintWriter(file1);
	      PrintWriter out2 = new PrintWriter(file2);
	      System.out.println("Input a or m");
		  String answer3=sc3.nextLine();
		  System.out.println("Input razmernost 2 numbers");
		  int razm=sc.nextInt();
		  int moduleValue=0;
		  int minBit=0;
		  System.out.println("Do you use module?");
		 String answer=sc1.nextLine();
		  if(answer.equals("yes")){
			  System.out.println("Input module");
		  moduleValue=sc1.nextInt();
		  }
		  System.out.println("Do you use saturation?");
		  String answer2=sc2.nextLine();
		  if(answer2.equals("yes")){
			  System.out.println("Input amount bit"); 
			  minBit=sc2.nextInt();
			  
			  
		  }
		  values=new String [(int)Math.pow(2, 2*razm)];
		 
		  try {
	            out1.print(".i"+" "+(razm*2)+"\r\n");
	            out2.print(".i"+" "+(razm*2)+"\r\n");
	            if(answer3.equals("m") &&(!answer2.equals("yes")) ){
	            out1.print(".o"+" "+(razm*2)+"\r\n");
	            out2.print(".o"+" "+(razm*2)+"\r\n");
	            }
	            else if(answer3.equals("a") &&(!answer2.equals("yes"))){
		            out1.print(".o"+" "+(razm+1)+"\r\n");
		            out2.print(".o"+" "+(razm+1)+"\r\n");
		            }
	            if(answer2.equals("yes")){
	            	 out1.print(".o"+" "+minBit+"\r\n");
	            	 out2.print(".o"+" "+minBit+"\r\n");
	            	 //razm=minBit;
	            }
	            
	            out1.print(".p"+" "+(int)Math.pow(2, 2*razm)+"\r\n");
	            out2.print(".p"+" "+(int)Math.pow(2, 2*razm)+"\r\n");
	            for(int i=0;i<(int)Math.pow(2, 2*razm);i++){
	  			  values[i]=c.IntToByte(i,razm*2);
	  			//out.print(values[i]+" ");
	  		  //out.println(values[i]+ " ");
	  		  c.getSumm(values[i],razm,i,moduleValue,answer,minBit,answer2,out1,out2,answer3);
	  		  }
	            out1.print(".e");
	            out2.print(".e");
	        } finally {	            
	            out1.close();
	            out2.close();
	        }
		  }
		

}
