package com.springboot.chapter10.view;


import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/3
 **/
public interface PdfExportService {
    public void make(Map<String,Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response);
}
