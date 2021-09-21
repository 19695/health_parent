<center>
    <h1>
        Apache POI
    </h1>
</center>



## 1 POI介绍

Apache POI是用Java编写的免费开源的跨平台的Java API，Apache POI提供API给Java程序对Microsoft Office格式档案读和写的功能，其中使用最多的就是使用POI操作Excel文件。

jxl：专门操作Excel

maven坐标：

~~~xml
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi</artifactId>
  <version>3.14</version>
</dependency>
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
  <version>3.14</version>
</dependency>
~~~

POI结构：

~~~xml
HSSF － 提供读写Microsoft Excel XLS格式档案的功能
XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能
HWPF － 提供读写Microsoft Word DOC格式档案的功能
HSLF － 提供读写Microsoft PowerPoint格式档案的功能
HDGF － 提供读Microsoft Visio格式档案的功能
HPBF － 提供读Microsoft Publisher格式档案的功能
HSMF － 提供读Microsoft Outlook格式档案的功能
~~~

## 2 入门案例

### 从Excel文件读取数据

使用POI可以从一个已经存在的Excel文件中读取数据

~~~java
//创建工作簿
XSSFWorkbook workbook = new XSSFWorkbook("D:\\hello.xlsx");
//获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
XSSFSheet sheet = workbook.getSheetAt(0);
//遍历工作表获得行对象
for (Row row : sheet) {
  //遍历行对象获取单元格对象
  for (Cell cell : row) {
    //获得单元格中的值
    String value = cell.getStringCellValue();
    System.out.println(value);
  }
}
workbook.close();
~~~

通过上面的入门案例可以看到，POI操作Excel表格封装了几个核心对象：

~~~p
XSSFWorkbook：工作簿
XSSFSheet：工作表
Row：行
Cell：单元格
~~~

上面案例是通过遍历工作表获得行，遍历行获得单元格，最终获取单元格中的值。

还有一种方式就是获取工作表最后一个行号，从而根据行号获得行对象，通过行获取最后一个单元格索引，从而根据单元格索引获取每行的一个单元格对象，代码如下：

~~~java
//创建工作簿
XSSFWorkbook workbook = new XSSFWorkbook("D:\\hello.xlsx");
//获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
XSSFSheet sheet = workbook.getSheetAt(0);
//获取当前工作表最后一行的行号，行号从0开始
int lastRowNum = sheet.getLastRowNum();
for(int i=0;i<=lastRowNum;i++){
  //根据行号获取行对象
  XSSFRow row = sheet.getRow(i);
  short lastCellNum = row.getLastCellNum();
  for(short j=0;j<lastCellNum;j++){
    String value = row.getCell(j).getStringCellValue();
    System.out.println(value);
  }
}
workbook.close();
~~~

### 向Excel文件写入数据

使用POI可以在内存中创建一个Excel文件并将数据写入到这个文件，最后通过输出流将内存中的Excel文件下载到磁盘

~~~java
//在内存中创建一个Excel文件
XSSFWorkbook workbook = new XSSFWorkbook();
//创建工作表，指定工作表名称
XSSFSheet sheet = workbook.createSheet("传智播客");

//创建行，0表示第一行
XSSFRow row = sheet.createRow(0);
//创建单元格，0表示第一个单元格
row.createCell(0).setCellValue("编号");
row.createCell(1).setCellValue("名称");
row.createCell(2).setCellValue("年龄");

XSSFRow row1 = sheet.createRow(1);
row1.createCell(0).setCellValue("1");
row1.createCell(1).setCellValue("小明");
row1.createCell(2).setCellValue("10");

XSSFRow row2 = sheet.createRow(2);
row2.createCell(0).setCellValue("2");
row2.createCell(1).setCellValue("小王");
row2.createCell(2).setCellValue("20");

//通过输出流将workbook对象下载到磁盘
FileOutputStream out = new FileOutputStream("D:\\itcast.xlsx");
workbook.write(out);
out.flush();
out.close();
workbook.close();
~~~



## 3 POI 工具类

### 通过 List<String[]> 方式

```java
package com.colm.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于 String[] 解析 excel 文件
 */
public class POIUtils {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    private static final String DATE_FORMAT = "yyyy/MM/dd";

    /**
     * 读入 excel 文件，解析后返回内容
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String[]> readExcel(MultipartFile file) throws IOException {
        // 检查文件类型
        checkFile(file);
        // 获取 Workbook 对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            // 遍历 sheet
            for(int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                // 获取当前 sheet 工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null) {
                    continue;
                }
                // 获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                // 获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                // 循环除了第一行的所有行
                for(int rowNum = firstRowNum + 1; rowNum < lastRowNum; rowNum++) {
                    // 获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null) {
                        continue;
                    }
                    // 获得当前行的开始列
                    short firstCellNum = row.getFirstCellNum();
                    // 获得当前行的最后列
                    short lastCellNum = row.getLastCellNum();
                    // 获得当前行的列数
                    // 我不知道为什么一开始这么做，可以测试一下在不同情况的 excel 填写下的取值是什么
//                    short lastCellNum = row.getPhysicalNumberOfCells();
                    // 获取不为空的列个数作为数组初始化大小
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    // 循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }

    /**
     * 将所有单元格的数据都按照字符串读出
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //如果当前单元格内容为日期类型，需要特殊处理
        String dataFormatString = cell.getCellStyle().getDataFormatString();
        if(dataFormatString.equals("m/d/yy")) {
            cellValue = new SimpleDateFormat(DATE_FORMAT).format(dataFormatString);
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }

        return cellValue;
    }

    /**
     * 判断是否是 excel
     * @param file
     * @throws IOException
     */
    private static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在
        if(null == file){
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if(!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)){
            throw new IOException(fileName + "不是excel文件");
        }
    }

    /**
     * 依据文件后缀来获取不同版本的 Workbook
     * @param file
     * @return
     */
    private static Workbook getWorkBook(MultipartFile file) {
        // 获取文件的原始名称
        String filename = file.getOriginalFilename();
        Workbook workbook = null;
        try (InputStream in = file.getInputStream();) {
            // 也可以使用这样的方式创建，这种方式 Excel 2003/2007/2010 都是可以处理的， 它会依据流的 first 8 byte 判断是什么类型
//            WorkbookFactory.create(in);
            if(filename.endsWith(XLS)) {
                //2003
                workbook = new HSSFWorkbook(in);
            }
            if(filename.endsWith(XLSX)) {
                //2007
                workbook = new XSSFWorkbook(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

}
```



### 通过注解方式

