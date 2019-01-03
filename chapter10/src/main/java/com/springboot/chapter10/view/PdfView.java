package com.springboot.chapter10.view;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/3
 **/
public class PdfView extends AbstractPdfView {
    //导出服务接口
    private PdfExportService pdfExportService = null;

    public PdfView(PdfExportService pdfExportService){
        this.pdfExportService = pdfExportService;
    }

    //调用接口实现
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        pdfExportService.make(model, document, writer, request, response);
    }
}
