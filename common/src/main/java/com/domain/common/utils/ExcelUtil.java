package com.domain.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * excel工具集合类
 *
 * @author leone
 **/
@Slf4j
public class ExcelUtil {

    public static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);


    private ExcelUtil() {
    }

    /**
     * 导出excel文件到浏览器
     *
     * @param response
     * @param data
     * @param fileName
     * @param title
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, List<String[]> data, String fileName, String[] title) throws Exception {
        Workbook workbook = new HSSFWorkbook();
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", new String(fileName.getBytes(), StandardCharsets.ISO_8859_1) + ".xls"));

        if (data != null && data.size() > 0) {
            if (title.length != data.get(0).length) {
                throw new RuntimeException("标题和主题的字段长度不同!");
            }
            Sheet sheet1 = workbook.createSheet("sheet1");
            Sheet sheet2 = workbook.createSheet("sheet2");
            Row row0 = sheet1.createRow(0);
            for (int i = 0; i < title.length; i++) {
                row0.createCell(i).setCellValue(title[i]);
            }
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet1.createRow(i + 1);
                for (int j = 0; j < title.length; j++) {
                    row.createCell(j).setCellValue(data.get(i)[j]);
                }
            }
            try {
                log.info("导出excel成功" + fileName);
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                log.info("导出excel失败");
            }
        }
    }


    /**
     * 导出模板
     *
     * @param response
     * @param title
     * @param fileName
     */
    public static void exportTemplate(HttpServletResponse response, String[] title, String fileName) {
        Workbook workbook = new HSSFWorkbook();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", new String(fileName.getBytes(), StandardCharsets.ISO_8859_1) + ".xls"));
        Sheet sheet1 = workbook.createSheet("sheet1");
        Row row0 = sheet1.createRow(0);
        if (title != null) {
            for (int i = 0; i < title.length; i++) {
                row0.createCell(i).setCellValue(title[i]);
            }
        }
        try {
            log.info("导出excel成功" + fileName);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.info("导出excel失败");
        }
    }

    /**
     * 导出文件流到客户端
     *
     * @param response
     * @param contentType
     * @param fileName
     */
    public static void exportToClient(HttpServletResponse response, String contentType, InputStream in, String fileName) {
        response.setCharacterEncoding("utf-8");
        response.setContentType(contentType + ";charset=UTF-8");
        OutputStream os = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = in.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
        } catch (IOException e) {
            logger.error(" ExportUtil exportToClient() error", e);
        } finally {
            try {
                if (Objects.nonNull(os)) {
                    os.close();
                }
                in.close();
            } catch (IOException e) {
                logger.error(" ExportUtil exportToClient() error", e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(StandardCharsets.UTF_8.displayName());
    }

}
