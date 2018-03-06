package com.data.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import Jama.Matrix;

public class LogProcess {
    /**
     * ��ȡ��������
     */
    public Matrix getDataMat(){
        try {
            ArrayList<double[]> list = new ArrayList<double[]>(); 
            String pathname = "C:\\testSet.txt";
            File filename = new File(pathname);

            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);

            String line = "";
            line = br.readLine();            
            
            while (line != null) {                    
                String [] tmp=line.split("\t");
                double [] value=new double[3];
                value[0]=1.0;
                value[1]=Double.parseDouble(tmp[0]);
                value[2]=Double.parseDouble(tmp[1]);
                list.add(value);
            
                line = br.readLine(); // һ�ζ���һ������    
            }
            
            
            //����ת��Ϊ��ά����
            Iterator<double[]> datalIt = list.iterator();
            double [][] data = new double[list.size()][3];
            int i=0;
            while (datalIt.hasNext()) {
                double [] tmp = datalIt.next();
                data[i][0]=tmp[0];
                data[i][1]=tmp[1];
                data[i][2]=tmp[2];
                i++;
            }        
            
            Matrix dataMatrix = new Matrix(data);
                        
            return dataMatrix;
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;
        }          
    }
    
    /**
     * ��ȡ��ǩ����
     */
    public Matrix getLabelMat(){
        try {
            ArrayList<int[]> list = new ArrayList<int[]>(); 
            String pathname = "C:\\testSet.txt";
            File filename = new File(pathname);

            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);

            String line = "";
            line = br.readLine();            
            
            while (line != null) {                    
                String [] tmp=line.split("\t");
                int [] value=new int[1];
                value[0]=Integer.parseInt(tmp[2]);
                list.add(value);
                
                line = br.readLine(); // һ�ζ���һ������    
            }
                        
            //����ǩת��Ϊ��ά����
            Iterator<int[]> labelIt = list.iterator();
            double [][] label = new double[list.size()][1];
            int j=0;
            while (labelIt.hasNext()) {
                label[j][0]=labelIt.next()[0];
                j++;
            }        
            
            Matrix labelMatrix = new Matrix(label);
            
            return labelMatrix;
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;
        }          
    }
        
    /**
     * sigmoid����
     */
    public Matrix sigmoid(Matrix intX){
        double [][] tmp = new double[intX.getRowDimension()][intX.getColumnDimension()];
        for(int i=0;i<intX.getRowDimension();i++){
            tmp[i][0]=1.0/(1+Math.exp(intX.get(i, 0)));
        }
        
        Matrix tmpMat = new Matrix(tmp);
        return tmpMat;
    }
    
    public static void main(String [] args){
        LogProcess lr = new LogProcess();
        //��������mXn
        Matrix dataMat= lr.getDataMat();    
        //��ǩ����m
        Matrix labelMat= lr.getLabelMat();
        //��ȡά��
        int m=dataMat.getRowDimension();
        int n=dataMat.getColumnDimension();
        
        //����
        double alpha=0.001;
        //��������
        int maxCycles=500;
        //weightsȨ��������
        double [][] weight=new double[3][1];
        weight[0][0]=1;
        weight[1][0]=1;
        weight[2][0]=1;
        Matrix weightMat = new Matrix(weight);
        
        //X*weight
        Matrix mm ;
        Matrix h;    
        Matrix e;
        
        //
        for(int i=1;i<maxCycles;i++){
            mm = dataMat.times(weightMat).times(-1);
            h = lr.sigmoid(mm);
            e = labelMat.minus(h);            
            weightMat = weightMat.plus(dataMat.transpose().times(e).times(alpha));        
            
            System.out.println("-------------------"+i+"------------------");
            for(int j=0;j<weightMat.getRowDimension();j++){
                System.out.println(weightMat.get(j, 0));
            }
        }    
        
        //Ԥ��
//        weight[0][0]=4.1207114;
//        weight[1][0]=0.4797796;
//        weight[2][0]=-0.6164160;
//        weightMat = new Matrix(weight);
//        
//        mm=dataMat.times(weightMat).times(-1);
//        h = lr.sigmoid(mm);
//        for(int j=0;j<h.getRowDimension();j++){
//            System.out.println(h.get(j, 0));
//        }
    }
}
