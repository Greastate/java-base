/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */

import cn.lgw.learn.exception.CommonException;
import com.csvreader.CsvReader;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

/**
 * <p></p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: Test.java, v 0.1 2018-11-12 11:55 AM @leiguowei $$
 */
public class Test {
    private static final long YUAN_TO_CENT_MULTIPLE = 100L;

    public static void main(String[] args) throws IOException {
//        checkExcel();
//        csv();
//        csv2Excel("csv3.csv", "/Users/leiguowei/test.xlsx");
//        tabTodot();


//           List<Integer> ints = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ints.add(i);
//        }
//        System.out.println(ints.subList(ints.size(), ints.size()));
//        System.out.println(Integer.MIN_VALUE);

//        Gson GSON = new Gson();
//
//        System.out.println(JSON.toJSONString(new Data1()));
//
//        System.out.println(JSON.parseObject("{\"a\":2}", Data1.class));
//
//        System.out.println(GSON.toJson(new Data1()));
//
//        System.out.println(GSON.fromJson("{\"a\":2}", Data1.class));


//        ApplicationContext context = new ClassPathXmlApplicationContext("")
//        boxTest();
//        tempTest();
        boxTest();
    }

    static void tempTest() {
            try {
                throw new Exception("123");
            } catch (CommonException e) {
                System.out.println("ce");
            } catch (Exception e) {
                System.out.println("ex");
            }
    }

    static void boxTest() {
//        Integer a = 1;
//        Integer b = 2;
//        Integer c = 3;
//        Integer d = 3;
//        Integer e = 321;
//        Integer f = 321;
//        Long g = 3L;
//
//        System.out.println(c == d);
//        System.out.println(e == f);
//        System.out.println(c == (a + b));
//        System.out.println(c.equals(a + b));
//        System.out.println(g == (a + b));
//        System.out.println(g.equals (a + b));

        Integer int1 = Integer.valueOf(1);
        Integer int2 = Integer.valueOf(1);
        Integer int3 = Integer.valueOf(300);

        System.out.println(300 == new Integer(300).longValue());

    }

    static void tabTodot() throws IOException {
        Scanner scanner = new Scanner(getFileInputStream("tab.txt"));
        FileWriter writer = new FileWriter("/Users/leiguowei/csv2.csv");

        while (scanner.hasNext()) {
            writer.append(scanner.nextLine().replaceAll("\t", ","));
            writer.append("\n");
        }
        writer.close();
    }

    static void csv2Excel(String csvFileName, String targetFilePath) throws IOException {
        CsvReader csvReader = new CsvReader(getFileInputStream(csvFileName), Charset.forName("ISO-8859-1"));
        FileOutputStream fo = new FileOutputStream(targetFilePath);
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        Sheet sheet = workbook.createSheet();
        for (int i = 0; csvReader.readRecord(); i++) {
            createExcelRow(sheet, csvReader.getValues(), i);
        }
        workbook.write(fo);
        fo.close();
    }

    private static void createExcelRow(Sheet sheet, String[] values, int rowIndex) throws IOException {
        Row row = sheet.createRow(rowIndex);
        for (int i = 0; i < values.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(values[i]);
        }
    }

    private static InputStream getFileInputStream(String fileName) {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(fileName);
    }

    static void checkExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook(getFileInputStream("full_refund_last.xlsx"));
        List<RefundOfflineImport> datas = new Test().getExcelData(workbook, 0);

        System.out.println(datas.size());
        System.out.println(datas);
    }

    static void checkRepeat() throws IOException {
        InputStream excelStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("refund_full.xlsx");
        Workbook workbook = new XSSFWorkbook(excelStream);
        Set<Long> allOrderIds = new HashSet<>();
        Set<Long> orderIdSet = getOrderIds(workbook, 0);
        for (Long orderId : orderIdSet) {
            if (allOrderIds.contains(orderId)) {
                System.out.println(orderId + "重复");
                continue;
            }
            allOrderIds.add(orderId);
        }
        System.out.println("第一张表完成");
        orderIdSet = getOrderIds(workbook, 1);
        for (Long orderId : orderIdSet) {
            if (allOrderIds.contains(orderId)) {
                System.out.println(orderId + "重复");
                continue;
            }
            allOrderIds.add(orderId);
        }
        System.out.println("第二张表完成");
        orderIdSet = getOrderIds(workbook, 2);
        for (Long orderId : orderIdSet) {
            if (allOrderIds.contains(orderId)) {
                System.out.println(orderId + "重复");
                continue;
            }
            allOrderIds.add(orderId);
        }
        System.out.println("第三张表完成");
        orderIdSet = getOrderIds(workbook, 3);
        for (Long orderId : orderIdSet) {
            if (allOrderIds.contains(orderId)) {
                System.out.println(orderId + "重复");
                continue;
            }
            allOrderIds.add(orderId);
        }
        System.out.println("第四张表完成");
    }

    static Set<Long> getOrderIds(Workbook workbook, int sheetNum) {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        Set<Long> results = new HashSet<>();
        for (Row row : sheet) {
            results.add(getCellLong(row.getCell(0)));
        }
        return results;
    }


    private List<RefundOfflineImport> getExcelData(Workbook workbook, int sheetNum) {
        List<RefundOfflineImport> results = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(sheetNum);
        if (Objects.isNull(sheet)) {
            return results;
        }
        for (Row row : sheet) {
            results.add(getRowData(row));
        }
        return results;
    }

    private RefundOfflineImport getRowData(Row row) {
        return new RefundOfflineImport()
                .setCreatedTime(row.getCell(0).getDateCellValue())
                .setOrderId(getCellLong(row.getCell(1)))
                .setReason1(getCellString(row.getCell(2)))
                .setReason2(getCellString(row.getCell(3)))
                .setRefundRemark(getCellString(row.getCell(4)))
                .setCallback(getCellString(row.getCell(5)))
                .setFinalRefundFee(getCellFee(row.getCell(6)));
    }

    private static long getCellLong(Cell cell) {
        String cellString = Optional.ofNullable(cell).map(Object::toString).orElse("0");
        return Long.parseLong(cellString);
    }

    private String getCellString(Cell cell) {
        return Optional.ofNullable(cell).map(Object::toString).orElse("");
    }

    private long getCellFee(Cell cell) {
        String cellString = Optional.ofNullable(cell).map(Object::toString).orElse("0");
        return (long) (Double.parseDouble(cellString) * YUAN_TO_CENT_MULTIPLE);
    }

    @lombok.Data
    @Accessors(chain = true)
    private class RefundOfflineImport {
        /**
         * 申请退款的时间
         */
        private Date createdTime;
        /**
         * 订单号
         **/
        private Long orderId;
        /**
         * 一级退款原因
         */
        private String reason1;
        /**
         * 二级退款原因
         */
        private String reason2;
        /**
         * 退款原因详情
         */
        private String refundRemark;
        /**
         * 电话回访备注
         */
        private String callback;
        /**
         * 退款金额
         */
        private long finalRefundFee;
    }

    @Data
    private static class Data1 {
        private int a = 1;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
}