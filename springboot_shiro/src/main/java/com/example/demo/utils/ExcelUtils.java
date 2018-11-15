/*
 * Copyright (C), 2013-2018, 天津大海云科技有限公司
 */
package com.example.demo.utils;

import com.example.demo.annotation.Excel;
import com.example.demo.entity.Result;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lidai
 * @date 2018/11/7 9:34
 */
public class ExcelUtils<T>{

    Class<T> clazz;

    public ExcelUtils(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 手动输入表头，导出全部字段
     * @param data
     * @param sheetName
     * @param title
     * @return
     * @throws IllegalAccessException
     * @throws FileNotFoundException
     */
    public Result exportUtil(List<T> data, String sheetName, List<String> title) throws IllegalAccessException, FileNotFoundException {
        XSSFWorkbook workbook =new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet(sheetName);
        XSSFRow row;
        XSSFCell cell;
        row=sheet.createRow(0);
        //填入表头
        for (int i=0;i<title.size();i++){
            cell=row.createCell(i);
            cell.setCellValue(title.get(i));
        }
        //填入主体
        //注意这个i代表行
        for(int i=0;i<data.size();i++){
            row=sheet.createRow(i+1);
            T vo=data.get(i);
            //根据反射获取类中所有字段
            Field[] fields=vo.getClass().getDeclaredFields();
            for(int j=0;j<fields.length;j++){
                Field field=fields[j];
                //私有字段可以操作
                field.setAccessible(true);
                Object value =field.get(vo);
                cell = row.createCell(j);
                setFieldValueAndName(field,value,cell);
            }
        }
        String fileName=UUIDUtils.getUUID()+sheetName+".xlsx";
        File dir=new File(getfile());
        if(!dir.exists()){
            dir.mkdirs();
        }
        System.out.println("path:"+dir+"---------------------------------");
        try(OutputStream outputStream=new FileOutputStream(dir+File.separator+fileName)){
            workbook.write(outputStream);
            return Result.build().success();
        }catch (IOException e) {
            e.printStackTrace();
            return Result.build().fail("导出失败",e);
        }
    }

    /**
     * 导出excel,表头通过@Excel注解获取
     * @param data
     * @param sheetName
     * @return
     * @throws IllegalAccessException
     * @throws FileNotFoundException
     */
    public Result exportExcel(List<T> data,String sheetName) throws IllegalAccessException, FileNotFoundException {
        List<Field> fields=Stream.of(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Excel.class))
                .collect(Collectors.toList());
        XSSFWorkbook workbook =new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet(sheetName);
        XSSFRow row;
        XSSFCell cell;
        row=sheet.createRow(0);
        //填入表头
        for (int i=0;i<fields.size();i++){
            Excel attr = fields.get(i).getAnnotation(Excel.class);
            cell=row.createCell(i);
            cell.setCellValue(attr.name());
        }
        for(int i=0;i<data.size();i++){
            row=sheet.createRow(i+1);
            T vo=data.get(i);
            for(int j=0;j<fields.size();j++){
                //获取字段名
                Field field=fields.get(j);
                field.setAccessible(true);
                cell = row.createCell(j);
                try
                {
                    if (String.valueOf(field.get(vo)).length() > 10)
                    {
                        throw new Exception("长度超过10位就不用转数字了");
                    }
                    // 如果可以转成数字则导出为数字类型
                    BigDecimal bc = new BigDecimal(String.valueOf(field.get(vo)));
                    cell.setCellValue(bc.doubleValue());
                }
                catch (Exception e)
                {
                    // 如果数据存在就填入,不存在填入空格.
                    cell.setCellValue(field.get(vo) == null ? "" : String.valueOf(field.get(vo)));
                }
            }
        }
        String fileName=UUIDUtils.getUUID()+sheetName+".xlsx";
        File dir=new File(getfile());
        if(!dir.exists()){
            dir.mkdirs();
        }
        System.out.println("path:"+dir+"---------------------------------");
        try(OutputStream outputStream=new FileOutputStream(dir+File.separator+fileName)){
            workbook.write(outputStream);
            return Result.build().success();
        }catch (IOException e) {
            e.printStackTrace();
            return Result.build().fail("导出失败",e);
        }
    }

    public void setFieldValueAndName(Field field,Object fieldValue,XSSFCell cell) {

        Class<?> fieldType=field.getType();
        if (String.class == fieldType) {
            cell.setCellValue(String.valueOf(fieldValue)==null?"":String.valueOf(fieldValue));
        } else if ((Integer.TYPE == fieldType)
                || (Integer.class == fieldType)) {
            cell.setCellValue(Integer.parseInt(fieldValue.toString()));
        } else if ((Long.TYPE == fieldType)
                || (Long.class == fieldType)) {
            cell.setCellValue( Long.valueOf(fieldValue.toString()));
        } else if ((Float.TYPE == fieldType)
                || (Float.class == fieldType)) {
            cell.setCellValue( Float.valueOf(fieldValue.toString()));
        } else if ((Short.TYPE == fieldType)
                || (Short.class == fieldType)) {
            cell.setCellValue( Short.valueOf(fieldValue.toString()));
        } else if ((Double.TYPE == fieldType)
                || (Double.class == fieldType)) {
            cell.setCellValue( Double.valueOf(fieldValue.toString()));
        } else if (Character.TYPE == fieldType) {
            if ((fieldValue!= null) && (fieldValue.toString().length() > 0)) {
                cell.setCellValue( Character
                        .valueOf(fieldValue.toString().charAt(0)));
            }
        }else if(Date.class==fieldType) {
            try {
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            cell.setCellValue(String.valueOf(fieldValue)==null?"":String.valueOf(fieldValue));
        }
    }

    public String getfile() throws FileNotFoundException
    {
        return ResourceUtils.getURL("classpath:").getPath() + "static/file";
    }
}

